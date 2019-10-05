package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		// To change which accounts table this statement is put into, change test.accounts into *.accounts where * is the location.
		String sql = "insert into test.accounts (accountusername, password, accountstatus) values (?, ?, ?);";
		PreparedStatement stmt;
		
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, account.getUsername());
			stmt.setString(2, account.getPassword());
			stmt.setString(3, account.getAccountStatus());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Put logging here.
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public Account readAccount(String username) {
		// To change which accounts table this statement is put into, change test.accounts into *.accounts where * is the location.
		//String sql = "insert into test.accounts (accountusername, password, accountstatus) values (?, ?, ?)";
		String sql = "select * from test.accounts where accountusername = ?;";
		PreparedStatement stmt;
		
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			Account account = new Account(rs.getString(1), rs.getString(2), rs.getString(3));
			return account;
		} catch (SQLException e) {
			// TODO Implement logging.
			e.printStackTrace();
			return null;
		}
	}
	
}
