package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import classes.orders;

public class OrdersDAO implements DAO<orders>{

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
			while(rs.next()) {
			itemIDs.add(rs.getInt("itemID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		orders result = new orders(ID, customerID, orderDate, itemIDs, status);
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
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root",
				"stuntflying9"); Statement statement = conn.createStatement()) {
			statement.executeUpdate("INSERT INTO orders(customerID, orderDate, status) values("
					+ input.getCustomerID() 
					+ ", " + Date.valueOf(input.getOrderDate()) 
					+ ", '" + input.getStatus() + "')");
			ResultSet resultSet = statement.executeQuery("Select * from customer ORDER BY ID DESC limit 1");
			resultSet.next();
			input.setID(resultSet.getInt("ID"));
			for(int itemID: input.getItemIDs()) {
				try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root",
						"stuntflying9"); Statement stmt = conn.createStatement()) {
					stmt.executeUpdate("INSERT INTO basket(orderID, itemID) values(" + input.getID() + ", " + itemID + ")");
			}catch (SQLException e) {
				e.printStackTrace();
			}
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
			rs = stmt.executeQuery("Select * from item");
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

}

