package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import classes.Customer;

public class CustomerDAO implements DAO<Customer>{
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
	public Customer modelFromResultSet(ResultSet resultSet) throws SQLException {
		int ID = resultSet.getInt("ID");
		String firstName = resultSet.getString("firstName");
		String lastName = resultSet.getString("lastName");
		String email = resultSet.getString("email");
		String postcode = resultSet.getString("postcode");
		String house = resultSet.getString("house");
		Customer result = new Customer(ID, firstName, lastName, email, postcode, house);
		return result;
	}
	@Override
	public Customer create(Customer input) {
		int beforeLength = countRow();
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root",
				"stuntflying9"); Statement statement = conn.createStatement()) {
			statement.executeUpdate("INSERT INTO customer(firstName, lastName, email, postcode, house) values('"
					+ input.getFirstName() + "', '" + input.getLastName() + "', '" + input.getEmail() + "', '"
					+ input.getPostcode() + "', '" + input.getHouse() + "')");
			ResultSet resultSet = statement.executeQuery("Select * from customer");
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
	public Customer read(int id) {
		String url = "jdbc:mysql://localhost:3306/inventory";
		String username = "root";
		String password = "stuntflying9";
		Connection con;
		Statement stmt;
		ResultSet rs = null;
		Customer result = null;

		try {
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery("Select * from customer WHERE ID=" + id);
			rs.next();
			modelFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	@Override
	public List<Customer> readAll() {
		List<Customer> allCustomer = new ArrayList<Customer>();
		String url = "jdbc:mysql://localhost:3306/inventory";
		String username = "root";
		String password = "stuntflying9";
		Connection con;
		Statement stmt;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery("Select * from customer");
			while (rs.next()) {
				allCustomer.add(modelFromResultSet(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allCustomer;
	}
	@Override
	public boolean delete(int id) {
		// need to make sure this deletes all orders and baskets
		ResultSet rs;
		List<Integer> orderIDs = new ArrayList<Integer>();
		int beforeLength = countRow();
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root",
				"stuntflying9"); Statement statement = conn.createStatement()) {
			rs = statement.executeQuery("SELECT * from orders WHERE customerID = " + id);
			while(rs.next()) {
			orderIDs.add(rs.getInt("ID"));
			}
			for (int orderID: orderIDs) {
				statement.executeUpdate("DELETE * from basket where orderID = " + orderID);
				statement.executeUpdate("DELETE * from orders where ID = " + orderID);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root",
				"stuntflying9"); Statement statement = conn.createStatement()) {
			statement.executeUpdate("DELETE FROM customer WHERE ID = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int afterLength = countRow();
		if (beforeLength == afterLength) {
			return false;
		}
		return true;
	}
	
	public Customer update(int ID, String firstName, String lastName, String email, String postcode, String house) {
		ResultSet rs;
		Customer found = null;
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root",
				"stuntflying9");
				PreparedStatement statement = conn
						.prepareStatement("UPDATE customer Set firstName = ?, lastName = ?, email = ?, "
								+ "postcode = ?, house = ? where ID = ?")) {
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setString(3, email);
			statement.setString(4, postcode);
			statement.setString(5, house);
			statement.executeUpdate();
			rs = conn.createStatement().executeQuery("SELECT * from customer where ID = " + ID);
			if (rs.next()) {
				found = modelFromResultSet(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return found;
	}
	}