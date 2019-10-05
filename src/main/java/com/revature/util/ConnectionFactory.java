package com.revature.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	private static String url;
	
	private static String user;
	
	private static String password;
	
	private static final String PROPERTIES_FILE = "src/main/resources/database.properties";
	
	private static ConnectionFactory connectionFactory;
	
	public static Connection getConnection() {
		
		if (connectionFactory == null) {
			connectionFactory = new ConnectionFactory();
		}
		
		return connectionFactory.createConnection();
		
	}
	
	
	private ConnectionFactory() {
		
		Properties properties = new Properties();
		
		try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
			
			properties.load(fis);
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private Connection createConnection() {
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("Failed to create connection");
			e.printStackTrace();
		}
		
		return connection;
		
	}
	
}
