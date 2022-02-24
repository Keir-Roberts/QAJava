package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class read {
public static List<String> readAll() {
	List<String> allCat = new ArrayList<String>();
	String url = "jdbc:mysql://localhost:3306/animal";
	String username = "root";
	String password = "stuntflying9";
	Connection con;
	Statement stmt;
	ResultSet rs = null;

	try {
		con = DriverManager.getConnection(url, username, password);
		stmt = con.createStatement();
		rs = stmt.executeQuery("Select * from cat");
	} catch (SQLException e) {
		e.printStackTrace();
	}
	int ID = 0;
	String name = null;
	int age = 0;
	try {
		while(rs.next()) {
		
		ID = rs.getInt("ID");
		name = rs.getString("catName");
		age = rs.getInt("catAge");
		allCat.add("ID: " +ID+ " Name: " +name+ " Age: " +age);
	}
	}catch (SQLException e) {
		e.printStackTrace();
}
	return allCat;
}
public static String readID(int ID) {
	String url = "jdbc:mysql://localhost:3306/animal";
	String username = "root";
	String password = "stuntflying9";
	Connection con;
	Statement stmt;
	ResultSet rs = null;
	int id = 0;
	String name = null;
	int age = 0;
	try {
		con = DriverManager.getConnection(url, username, password);
		stmt = con.createStatement();
		rs = stmt.executeQuery("Select * from cat WHERE ID=" + ID);
		rs.next();
		id = rs.getInt("ID");
		name = rs.getString("catName");
		age = rs.getInt("catAge");
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return ("ID: " +id+ " Name: " +name+ " Age: " +age);
}
public static String readCat(cat Cat) {
	int id = Cat.getId();
	return readID(id);
}
}

