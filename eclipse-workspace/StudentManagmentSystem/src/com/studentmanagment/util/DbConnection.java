package com.studentmanagment.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	private static Connection connection;
	private static String url = "jdbc:mysql://localhost:3306/studentmanagment";
	private static String username = "root";
	private static String password = "";

	private DbConnection() {
	}

	public static Connection getConnection() throws SQLException {
		 connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
//				System.out.println("Connected to database successfully");
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Failed to connect to database: " + e.getMessage());
		}

		return connection;
	}
}
