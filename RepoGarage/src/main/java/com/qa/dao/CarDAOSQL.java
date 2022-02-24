package com.qa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.qa.vehicles.Car;

public class CarDAOSQL implements Dao<Car> {
	public int countRow() {
		int counter = 0;
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/garage", "root",
				"stuntflying9"); Statement statement = conn.createStatement()) {
			ResultSet resultSet = statement.executeQuery("Select * from car");
			while (resultSet.next()) {
				counter++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return counter;
	}
	@Override
	public Car modelFromResultSet(ResultSet resultSet) throws SQLException {
		int ID = resultSet.getInt("ID");
		int wheels = resultSet.getInt("wheels");
		String colour = resultSet.getString("colour");
		String maker = resultSet.getString("maker");
		int horsePower = resultSet.getInt("horsePower");
		Car result = new Car(wheels, colour, maker, horsePower);
		result.setId(ID);
		return result;
	}
	@Override
	public Car create(Car vehicle) {
		int beforeLength = countRow();
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/garage", "root",
				"stuntflying9"); Statement statement = conn.createStatement()) {
			statement.executeUpdate("INSERT INTO car(wheels, colour, maker, horsePower) values("
					+ ((Car) vehicle).getWheels() + ", '" + ((Car) vehicle).getColour() + "', '"
					+ ((Car) vehicle).getMaker() + "', " + ((Car) vehicle).getHorsePower() + ")");
			ResultSet resultSet = statement.executeQuery("Select * from car");
			while (resultSet.next()) {
				((Car) vehicle).setId(resultSet.getInt("ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int afterLength = countRow();
		if (beforeLength == afterLength) {
			return null;
		}
		return vehicle;
	}

	@Override
	public Car read(int id) {
		String url = "jdbc:mysql://localhost:3306/garage";
		String username = "root";
		String password = "stuntflying9";
		Connection con;
		Statement stmt;
		ResultSet rs = null;
		Car result = null;

		try {
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery("Select * from car WHERE ID=" + id);
			rs.next();
			modelFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<Car> readAll() {
		List<Car> allCar = new ArrayList<Car>();
		String url = "jdbc:mysql://localhost:3306/garage";
		String username = "root";
		String password = "stuntflying9";
		Connection con;
		Statement stmt;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery("Select * from car");
			while (rs.next()) {
				allCar.add(modelFromResultSet(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allCar;
	}

	@Override
	public boolean delete(int id) {
		int beforeLength = countRow();
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/garage", "root",
				"stuntflying9"); Statement statement = conn.createStatement()) {
			statement.executeUpdate("DELETE FROM car WHERE ID = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int afterLength = countRow();
		if (beforeLength == afterLength) {
			return false;
		}
		return true;
	}
	public Car update(int ID, int wheels, String colour, String maker, int horsePower) {
		ResultSet rs;
		Car found = null;
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/garage", "root",
				"stuntflying9");
				PreparedStatement statement = conn
						.prepareStatement("UPDATE car Set wheels = ?, colour = ?, maker = ?, horsePower = ? where ID = ?")) {
			statement.setInt(1, wheels);
			statement.setString(2, colour);
			statement.setString(3, maker);
			statement.setInt(4, horsePower);
			statement.setInt(5, ID);
			statement.executeUpdate();
			rs = conn.createStatement().executeQuery("SELECT * from car where ID = " + ID);
			if (rs.next()) {
				found = modelFromResultSet(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return found;
	}
}
