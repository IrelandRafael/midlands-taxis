package com.ait.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionUtils {

	private static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/midlands_taxis";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "administrator";
	
	static Connection getDBConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(DB_CONNECTION_URL, DB_USER, DB_PASSWORD);
	}

}
