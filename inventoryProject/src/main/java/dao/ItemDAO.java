package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import classes.Item;
import userInput.logger;

public class ItemDAO implements DAO<Item> {
	public void logMessage(String input) {
		logger.logMessage(input);
	}

	public void logDebug(String input) {
		logger.logDebug(input);
	}

	@Override
	public int countRow() {
		int counter = 0;
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root",
				"stuntflying9"); Statement statement = conn.createStatement()) {
			ResultSet resultSet = statement.executeQuery("Select * from item");
			while (resultSet.next()) {
				counter++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return counter;
	}

	public List<Integer> getOngoing(int ID) {
		List<Integer> orders = new ArrayList<Integer>();
		ResultSet rs1;
		ResultSet rs2;
		List<Integer> ongoingIDs = new ArrayList<Integer>();
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root",
				"stuntflying9"); Statement statement = conn.createStatement()) {
			rs1 = statement.executeQuery("SELECT * from basket WHERE itemID = " + ID);
			while (rs1.next()) {
				if (!orders.contains(rs1.getInt("orderID")))
					orders.add(rs1.getInt("orderID"));
			}
			for (int orderID : orders) {
				rs2 = statement.executeQuery("SElECT * from orders WHERE orderID = " + orderID);
				rs2.next();
				if (rs2.getString("status").equals("ONGOING")) {
					logDebug("ONGOING order found with ID: " + rs2.getInt("ID"));
					ongoingIDs.add(rs2.getInt("ID"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ongoingIDs;

	}

	@Override
	public Item modelFromResultSet(ResultSet resultSet) throws SQLException {
		int ID = resultSet.getInt("ID");
		String productName = resultSet.getString("productName");
		DecimalFormat df = new DecimalFormat("0.00");
		String price = df.format(resultSet.getFloat("price"));
		long stock = resultSet.getLong("stock");
		Item result = new Item(productName, price, stock);
		result.setID(ID);
		return result;
	}

	@Override
	public Item create(Item input) {
		int beforeLength = countRow();
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root",
				"stuntflying9"); Statement statement = conn.createStatement()) {
			statement.executeUpdate("INSERT INTO item(productName, price, stock) values('" + input.getProductName()
					+ "', '" + input.getPrice() + "', '" + input.getStock() + "')");
			ResultSet resultSet = statement.executeQuery("Select * from item ORDER BY ID DESC LIMIT 1");
			while (resultSet.next()) {
				input.setID(resultSet.getInt("ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int afterLength = countRow();
		if (beforeLength == afterLength) {
			return null;
		}
		return input;

	}

	@Override
	public Item read(int id) {
		String url = "jdbc:mysql://localhost:3306/inventory";
		String username = "root";
		String password = "stuntflying9";
		Connection con;
		Statement stmt;
		ResultSet rs = null;
		Item result = null;

		try {
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery("Select * from item WHERE ID=" + id);
			rs.next();
			result = modelFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Item> readAll() {
		List<Item> allItem = new ArrayList<Item>();
		String url = "jdbc:mysql://localhost:3306/inventory";
		String username = "root";
		String password = "stuntflying9";
		Connection con;
		Statement stmt;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery("Select * from item");
			while (rs.next()) {
				allItem.add(modelFromResultSet(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allItem;
	}

	public List<Integer> cancelAll(int ID) {
		OrdersDAO ordDAO = new OrdersDAO();
		List<Integer> ongoingIDs = getOngoing(ID);
		for (int orderID : ongoingIDs) {
			ordDAO.cancel(orderID);

		}
		return ongoingIDs;
	}
	@Override
	public boolean delete(int id) {
		ResultSet rs;
		int beforeLength = countRow();
		List<Integer> ongoingIDs = getOngoing(id);
		List<Integer> orderIDs = new ArrayList<Integer>();
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root",
				"stuntflying9"); Statement statement = conn.createStatement()) {
			rs = statement.executeQuery("SELECT * from basket WHERE itemID = " + id);
			while (rs.next()) {
				if (!orderIDs.contains(rs.getInt("orderID")))
					orderIDs.add(rs.getInt("orderID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (ongoingIDs.size() == 0) {
			try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root",
					"stuntflying9"); Statement statement = conn.createStatement()) {
				statement.executeUpdate("DELETE FROM basket WHERE itemID = " + id);
				for (int orderID: orderIDs) {
					statement.executeUpdate("DELETE FROM orders WHERE ID = " + orderID);
				}
				statement.executeUpdate("DELETE FROM item WHERE ID = " + id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			int afterLength = countRow();
			if (beforeLength == afterLength) {
				return false;
			}
			return true;
		} else
			return false;
	}

	public Item update(int ID, String productName, double price, long stock) {
		ResultSet rs;
		Item found = null;
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root",
				"stuntflying9");
				PreparedStatement statement = conn
						.prepareStatement("UPDATE item SET productName = ?, price = ?, stock = ? where ID = ?")) {
			statement.setString(1, productName);
			statement.setDouble(2, price);
			statement.setLong(3, stock);
			statement.setInt(4, ID);
			statement.executeUpdate();
			rs = conn.createStatement().executeQuery("SELECT * from item where ID = " + ID);
			if (rs.next()) {
				found = modelFromResultSet(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return found;
	}
}
