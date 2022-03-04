package dao;

import userInput.logger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import classes.status;
import classes.orders;

public class OrdersDAO implements DAO<orders> {
	public void logMessage(String input) {
		logger.logMessage(input);
	}

	public void logDebug(String input) {
		logger.logDebug(input);
	}

	public int checkStock(int itemID) {
		int Stock = 0;
		ResultSet rs;
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root",
				"stuntflying9"); Statement statement = conn.createStatement()) {
			rs = statement.executeQuery("SELECT stock FROM item WHERE ID =" + itemID);
			rs.next();
			Stock = rs.getInt("stock");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Stock;
	}

	@Override
	public orders modelFromResultSet(ResultSet resultSet) throws SQLException {
		int ID = resultSet.getInt("ID");
		ResultSet rs;
		int customerID = resultSet.getInt("customerID");
		LocalDate orderDate = resultSet.getDate("orderDate").toLocalDate();
		String status = resultSet.getString("status");
		List<Integer> itemIDs = new ArrayList<Integer>();
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root",
				"stuntflying9"); Statement statement = conn.createStatement()) {
			rs = statement.executeQuery("SELECT * from basket WHERE orderID = " + ID);
			while (rs.next()) {
				itemIDs.add(rs.getInt("itemID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		orders result = new orders(customerID, orderDate, itemIDs);
		if (status.equals("ONGOING")) {
			result.setStatus(classes.status.ONGOING);
		} else if (status.equals("COMPLETED")) {
			result.setStatus(classes.status.COMPLETED);
		} else if (status.equals("CANCELLED")) {
			result.setStatus(classes.status.CANCELLED);
		} else {
			logDebug("Unrecognised status: " + status);
		}
		result.setID(ID);
		return result;
	}

	@Override
	public int countRow() {
		int counter = 0;
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root",
				"stuntflying9"); Statement statement = conn.createStatement()) {
			ResultSet resultSet = statement.executeQuery("Select * from customer");
			while (resultSet.next()) {
				counter++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return counter;
	}

	@Override
	public orders create(orders input) {
		int beforeLength = countRow();
		int tempStock = 0;
		int priorID = 99999;
		Date date = Date.valueOf(input.getOrderDate());
		logDebug("sql date: " + date);
		Boolean goodStock = true;
		Collections.sort(input.getItemIDs());
		for (int id : input.getItemIDs()) {
			if (id == priorID) {
				tempStock = tempStock - 1;
			} else {
				tempStock = checkStock(id);
			}
			priorID = id;
			if (tempStock < 1) {
				goodStock = false;
			}
		}
		if (goodStock) {
			try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root",
					"stuntflying9"); Statement statement = conn.createStatement()) {
				statement.executeUpdate(
						"INSERT INTO orders(customerID, orderDate, status) values(" + input.getCustomerID() + ", "
								+ date + ", '" + input.getStatus() + "')");
				ResultSet resultSet = statement.executeQuery("Select * from orders ORDER BY ID DESC limit 1");
				resultSet.next();
				input.setID(resultSet.getInt("ID"));
				for (int itemID : input.getItemIDs()) {
					try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root",
							"stuntflying9"); Statement stmt = conn.createStatement()) {
						stmt.executeUpdate(
								"INSERT INTO basket(orderID, itemID) values(" + input.getID() + ", " + itemID + ")");
						resultSet = stmt.executeQuery("SELECT * from item where ID = " + itemID);
						resultSet.next();
						int newStock = resultSet.getInt("stock") - 1;
						stmt.executeUpdate("UPDATE item SET STOCK = " + newStock + "where ID = " + itemID);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root",
						"stuntflying9"); Statement stmt = conn.createStatement()) {
					stmt.executeUpdate("UPDATE orders SET status = 'ONGOING' where ID =" + input.getID());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			int afterLength = countRow();
			if (beforeLength == afterLength) {
				return null;
			}
			return input;
		} else
			return null;
	}

	public boolean cancel(int id) {
		ResultSet rs;
		orders order = null;
		int newStock = 0;
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root",
				"stuntflying9"); Statement statement = conn.createStatement()) {
			rs = statement.executeQuery("SELECT * FROM orders WHERE ID =" + id);
			rs.next();
			order = modelFromResultSet(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (order.getStatus() == status.CANCELLED) {
			logDebug("Order ID: " + id + " is already CANCELLED.");
			return false;
		} else if (order.getStatus() == status.COMPLETED) {
			logDebug("Order ID: " + id + " is COMPLETED and cannot cancel completed order.");
			return false;
		} else {
			try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root",
					"stuntflying9"); Statement statement = conn.createStatement()) {
				statement.executeUpdate("UPDATE orders SET status = 'CANCELLED' where ID = " + id);
				for (int itemID : order.getItemIDs()) {
					newStock = checkStock(itemID) + 1;
					statement.executeUpdate("UPDATE item SET stock = " + newStock + " where ID = " + itemID);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			order.setStatus(status.CANCELLED);
		}
		return true;
	}

	public boolean complete(int id) {
		ResultSet rs;
		orders order = null;
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root",
				"stuntflying9"); Statement statement = conn.createStatement()) {
			rs = statement.executeQuery("SELECT * FROM orders WHERE ID =" + id);
			rs.next();
			order = modelFromResultSet(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (order.getStatus() == status.CANCELLED) {
			logDebug("Order ID: " + id + " is CANCELLED and cannot complet cancelled orders.");
			return false;
		} else if (order.getStatus() == status.COMPLETED) {
			logDebug("Order ID: " + id + " is already COMPLETED.");
			return false;
		} else {
			try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root",
					"stuntflying9"); Statement statement = conn.createStatement()) {
				statement.executeUpdate("UPDATE orders SET status = 'CANCELLED' where ID = " + id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			order.setStatus(status.CANCELLED);
		}
		return true;
	}

	@Override
	public orders read(int id) {
		String url = "jdbc:mysql://localhost:3306/inventory";
		String username = "root";
		String password = "stuntflying9";
		Connection con;
		Statement stmt;
		ResultSet rs = null;
		orders result = null;

		try {
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery("Select * from item WHERE ID=" + id);
			rs.next();
			modelFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<orders> readAll() {
		List<orders> allOrders = new ArrayList<orders>();
		String url = "jdbc:mysql://localhost:3306/inventory";
		String username = "root";
		String password = "stuntflying9";
		Connection con;
		Statement stmt;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery("Select * from orders");
			while (rs.next()) {
				allOrders.add(modelFromResultSet(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allOrders;
	}

	@Override
	public boolean delete(int id) {
		int beforeLength = countRow();
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root",
				"stuntflying9"); Statement statement = conn.createStatement()) {
			statement.executeQuery("DELETE FROM basket WHERE orderID = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root",
				"stuntflying9"); Statement statement = conn.createStatement()) {
			statement.executeUpdate("DELETE FROM orders WHERE ID = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int afterLength = countRow();
		if (beforeLength == afterLength) {
			return false;
		}
		return true;
	}

	public List<Integer> removeItem(int OrderId, int itemId) {
		orders order = read(OrderId);
		String url = "jdbc:mysql://localhost:3306/inventory";
		String username = "root";
		String password = "stuntflying9";
		Connection con;
		Statement stmt;
		ResultSet rs = null;
		orders out = null;
		int newStock = 0;
		if (order.getItemIDs().contains(itemId)) {
			try {
				con = DriverManager.getConnection(url, username, password);
				stmt = con.createStatement();
				newStock = checkStock(itemId) + 1;
				stmt.executeUpdate("UPDATE item SET stock = " + newStock + " WHERE ID = " + itemId);
				rs = stmt.executeQuery("Select * from basket WHERE orderID = " + OrderId + " and itemID = " + itemId);
				rs.next();
				stmt.executeUpdate("DELETE from basket WHERE ID = " + rs.getInt("ID"));
				rs = stmt.executeQuery("Select * from orders WHERE ID = " + OrderId);
				rs.next();
				out = modelFromResultSet(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return out.getItemIDs();
		} else {
			logDebug("item with ID: " + itemId + " is not found within order with ID: " + OrderId);
			return read(OrderId).getItemIDs();
		}
	}

	public List<Integer> addItem(int OrderId, int itemId) {
		ResultSet rs;
		orders out = null;
		int itemStock = checkStock(itemId) - 1;
		if (itemStock < 0) {
			logDebug("Not enough stock");
			return read(OrderId).getItemIDs();
		} 
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root",
				"stuntflying9"); Statement statement = conn.createStatement()) {
			statement.executeUpdate("INSERT INTO basket(orderID, itemID) values(" + OrderId + ", " + itemId + ")");
			statement.executeUpdate("UPDATE item SET stock = " + itemStock + " WHERE ID = " + itemId);
			rs = statement.executeQuery("SELECT * from orders where ID = " + OrderId);
			rs.next();
			out = modelFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return out.getItemIDs();
	}
}