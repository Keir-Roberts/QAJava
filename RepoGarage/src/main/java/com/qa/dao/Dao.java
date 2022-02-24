package com.qa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.qa.vehicles.Vehicle;

public interface Dao<T extends Vehicle> {
	
	T modelFromResultSet(ResultSet resultSet) throws SQLException;
	
	T create(T vehicle);
	
	T read(int id);
	
	List<T> readAll();
	
	boolean delete(int id);
}
