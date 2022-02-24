package com.qa.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.qa.dao.TruckDAOSQL;
import com.qa.utils.UserInput;
import com.qa.vehicles.Truck;

public class TruckController implements GarageController<Truck> {
	
	private TruckDAOSQL truckDAOSQL;
	private UserInput input;
	
	public TruckController(TruckDAOSQL truckDAOSQL) {
		super();
		this.input = UserInput.getInstance();
		this.truckDAOSQL = truckDAOSQL;
	}
	
	public TruckController(TruckDAOSQL truckDAOSQL, UserInput input) {
		super();
		this.input = input;
		this.truckDAOSQL = truckDAOSQL;
	}

	public Truck create() {
		int wheels, capacity;
		String colour;
		
		System.out.println("How many wheels does the truck have?");
		wheels = input.getInt();
		System.out.println("What colour is it?");
		colour = input.getString();
		System.out.println("Capacity? Kg");
		capacity = input.getInt();
		Truck newTruck = new Truck(wheels, colour, capacity);
		return truckDAOSQL.create(newTruck);
	}

	public boolean delete() {
		readAll();
		System.out.println("ID of the truck you wish to delete?");
		int id = input.getInt();
		return truckDAOSQL.delete(id);
	}

	public List<Truck> readAll() {
		List<Truck> all = truckDAOSQL.readAll();
		for(Truck truck:all) {
			System.out.println(truck);
		}
		return all;
	}

	public Truck update() {
		readAll();
		ResultSet rs;
		Truck result = null;
		System.out.println("ID of the truck you wish to Update?");
		int id = input.getInt();
		System.out.println("How many wheels does the truck have?");
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/garage", "root",
				"stuntflying9"); Statement statement = conn.createStatement()) {
			statement.executeUpdate("UPDATE truck SET wheels = " + input.getInt() + " WHERE ID = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("What colour is it?");
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/garage", "root",
				"stuntflying9"); Statement statement = conn.createStatement()) {
			statement.executeUpdate("UPDATE truck SET wheels = " + input.getString() + " WHERE ID = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Capacity? Kg");
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/garage", "root",
				"stuntflying9"); Statement statement = conn.createStatement()) {
			statement.executeUpdate("UPDATE truck SET capacity = " + input.getInt() + " WHERE ID = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/garage", "root",
				"stuntflying9"); Statement statement = conn.createStatement()) {
			rs = statement.executeQuery("Select * from truck where ID = " + id);
			rs.next();
			result = truckDAOSQL.modelFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
}
