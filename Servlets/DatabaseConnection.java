package com.higradius;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	protected static Connection initializeDatabase() throws SQLException, ClassNotFoundException {
		
		// Add database info
		String dbDriver = "com.mysql.jdbc.Driver";
		String dbURL = "jdbc:mysql://localhost:3306/";
		String dbName = "predictions";
		String dbUsername = "root";
		String dbPassword = "2580";
		
		// Database Connection
		Class.forName(dbDriver);
		Connection con = DriverManager.getConnection(dbURL + dbName, dbUsername, dbPassword);
		
		return con;
	}
}
