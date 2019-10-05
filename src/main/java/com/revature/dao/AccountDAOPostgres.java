package com.revature.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.pojos.authentication.Account;
import com.revature.util.ConnectionFactory;

public class AccountDAOPostgres implements AccountDAO {

	private Connection connection = ConnectionFactory.getConnection();
	
	public void setConn(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public boolean createAccount(Account account) {
		//String sql = "insert into cookie (flavor, delciousness) values('" + cookie.getFlavor() + "', " + cookie.getDeliciousness() + ")";
		
		String sql = "insert into accounts (username, password) values ('" + account.getUsername() + "', " + account.getPassword() + ")";
		
		try {
			connection.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public Account readAccount(String username) {
		return null;
	}
	
}
