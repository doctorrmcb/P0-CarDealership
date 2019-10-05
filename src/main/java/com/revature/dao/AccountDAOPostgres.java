package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
		
		// To change which accounts table this statement is put into, change test.accounts into *.accounts where * is the location.
		String sql = "insert into test.accounts (accountusername, password, accountstatus) values (?, ?, ?)";
		PreparedStatement stmt;
		
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, account.getUsername());
			stmt.setString(2, account.getPassword());
			stmt.setString(3, account.getAccountStatus());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public Account readAccount(String username) {
		return null;
	}
	
}
