package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class create {
public static void add(cat Cat) {
	
	try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/animal", "root", "stuntflying9");
			Statement statement = conn.createStatement()) {
		statement.executeUpdate("INSERT INTO cat(catName, catAge) values('" + Cat.getName() + "', '" + Cat.getAge() + "')");
		ResultSet resultSet = statement.executeQuery("Select * from cat");
		while (resultSet.next()) {
			System.out.println(Cat.getId());
			Cat.setID(resultSet.getInt("ID"));
			System.out.println(Cat.getId());
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
}
