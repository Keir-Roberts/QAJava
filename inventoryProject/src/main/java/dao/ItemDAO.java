package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import classes.Item;

public class ItemDAO implements DAO<Item> {
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

	@Override
	public Item modelFromResultSet(ResultSet resultSet) throws SQLException {
		int ID = resultSet.getInt("ID");
		String productName = resultSet.getString("productName");
		float price = resultSet.getFloat("price");
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
			modelFromResultSet(rs);
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

	@Override
	public boolean delete(int id) {
		int beforeLength = countRow();
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root",
				"stuntflying9"); Statement statement = conn.createStatement()) {
			statement.executeQuery("DELETE FROM basket WHERE itemID = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root",
				"stuntflying9"); Statement statement = conn.createStatement()) {
			statement.executeUpdate("DELETE FROM item WHERE ID = " + id);
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
