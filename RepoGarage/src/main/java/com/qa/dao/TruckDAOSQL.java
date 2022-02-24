package com.qa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.qa.vehicles.Truck;

public class TruckDAOSQL implements Dao<Truck> {
	public int countRow() {
		int counter = 0;
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/garage", "root",
				"stuntflying9"); Statement statement = conn.createStatement()) {
			ResultSet resultSet = statement.executeQuery("Select * from Truck");
			while (resultSet.next()) {
				counter++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return counter;
	}

	@Override
	public Truck modelFromResultSet(ResultSet resultSet) throws SQLException {
		int ID = resultSet.getInt("ID");
		int wheels = resultSet.getInt("wheels");
		String colour = resultSet.getString("colour");
		int capacity = resultSet.getInt("capacity");
		Truck result = new Truck(wheels, colour, capacity);
		result.setId(ID);
		return result;
	}

	@Override
	public Truck create(Truck vehicle) {
		int beforeLength = countRow();
		ResultSet rs;
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/garage", "root",
				"stuntflying9");
				PreparedStatement statement = conn
						.prepareStatement("INSERT INTO Truck(wheels, colour, capacity) values(?, ?, ?")) {
			statement.setInt(1, vehicle.getWheels());
			statement.setString(2, vehicle.getColour());
			statement.setInt(3, ((Truck) vehicle).getCapacity());
			rs = statement.executeQuery("Select * from Truck");
			while (rs.next()) {
				((Truck) vehicle).setId(rs.getInt("ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int afterLength = countRow();
		if (beforeLength == afterLength) {
			return null;
		}
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/garage", "root",
				"stuntflying9"); Statement statement = conn.createStatement()) {
			rs = statement.executeQuery("Select * from Truck WHERE ID = " + vehicle.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ((Truck) vehicle);
	}

	public Truck create(int wheels, String colour, int capacity) {
		int beforeLength = countRow();
		ResultSet rs;
		int id = 0;
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/garage", "root",
				"stuntflying9");
				PreparedStatement statement = conn
						.prepareStatement("INSERT INTO Truck(wheels, colour, capacity) values(?, ?, ?")) {
			statement.setInt(1, wheels);
			statement.setString(2, colour);
			statement.setInt(3, capacity);
			rs = statement.executeQuery("Select * from Truck");
			while (rs.next()) {
				id = rs.getInt("ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int afterLength = countRow();
		if (beforeLength == afterLength) {
			return null;
		}
		Truck vehicle = new Truck(wheels, colour, capacity);
		vehicle.setId(id);
		return vehicle;
	}

	@Override
	public Truck read(int id) {
		String url = "jdbc:mysql://localhost:3306/garage";
		String username = "root";
		String password = "stuntflying9";
		Connection con;
		Statement stmt;
		ResultSet rs = null;
		Truck result = null;

		try {
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery("Select * from Truck WHERE ID=" + id);
			if (rs.next()) {
				modelFromResultSet(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<Truck> readAll() {
		List<Truck> allTruck = new ArrayList<Truck>();
		String url = "jdbc:mysql://localhost:3306/garage";
		String username = "root";
		String password = "stuntflying9";
		Connection con;
		Statement stmt;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery("Select * from Truck");
			while (rs.next()) {
				allTruck.add(modelFromResultSet(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allTruck;
	}

	@Override
	public boolean delete(int id) {
		int beforeLength = countRow();
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/garage", "root",
				"stuntflying9"); Statement statement = conn.createStatement()) {
			statement.executeUpdate("DELETE FROM truck WHERE ID = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int afterLength = countRow();
		if (beforeLength == afterLength) {
			return false;
		}
		return true;
	}

	public Truck update(int ID, int wheels, String colour, int capacity) {
		ResultSet rs;
		Truck found = null;
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/garage", "root",
				"stuntflying9");
				PreparedStatement statement = conn
						.prepareStatement("UPDATE truck Set wheels = ?, colour = ?, capacity = ? where ID = ?")) {
			statement.setInt(1, wheels);
			statement.setString(2, colour);
			statement.setInt(3, capacity);
			statement.setInt(4, ID);
			statement.executeUpdate();
			rs = conn.createStatement().executeQuery("SELECT * from truck where ID = " + ID);
			if (rs.next()) {
				found = modelFromResultSet(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return found;
	}
}
