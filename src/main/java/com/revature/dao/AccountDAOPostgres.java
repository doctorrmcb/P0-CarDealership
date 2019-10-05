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
		
		// To change which accounts table this statement is put into, change test.accounts into *.accounts where * is the location.
		String sql = "insert into test.accounts (accountsusername, password, accountstatus) values ('" + account.getUsername() + "', '" + account.getPassword() + "', '" + account.getAccountStatus() + "')";
		
		try {
			connection.createStatement().executeUpdate(sql);
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
