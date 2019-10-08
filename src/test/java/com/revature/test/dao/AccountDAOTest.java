package com.revature.test.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.revature.dao.AccountDAOPostgres;
import com.revature.pojos.authentication.Account;
import com.revature.util.ConnectionFactory;

@RunWith(MockitoJUnitRunner.class)
public class AccountDAOTest {

	//Connection connection = ConnectionFactory.getConnection();
	//String sql = "insert into test.accounts (accountusername, password, accountstatus) values (?, ?, ?);";
	
	@Mock
	Connection connection;
	
	@Spy
	AccountDAOPostgres accountDAO = new AccountDAOPostgres();
	
	@Spy
	PreparedStatement stmtCreate = ConnectionFactory.getConnection()
		.prepareStatement("insert into test.accounts (accountusername, password, accountstatus) values (?, ?, ?);");
	
	@Spy
	PreparedStatement stmtRead = ConnectionFactory.getConnection()
		.prepareStatement("select * from test.accounts where accountusername = ?;");
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createAccountTest() {
		String sql = "insert into test.accounts (accountusername, password, accountstatus) values (?, ?, ?);";
		Account account = new Account("testUser", "testPass", "Employee");
		boolean result = false;
		try {
			when(connection.prepareStatement(sql)).thenReturn(stmtCreate);
			accountDAO.setConn(connection);
			assertEquals(result, accountDAO.createAccount(account));
			Mockito.verify(stmtCreate).executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void readAccountTest() {
		String sql = "select * from test.accounts where accountusername = ?;";
		String username = "testUser";
		Account account = new Account("testUser", "testPass", "Employee");
		try {
			when(connection.prepareStatement(sql)).thenReturn(stmtRead);
			accountDAO.setConn(connection);
			assertEquals(account, accountDAO.readAccount(username));
			Mockito.verify(stmtRead).executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void readAccountExceptionTest() {
		String sql = "select * from test.accounts where accountusername = ?;";
		String username = "testUser";
		try {
			when(connection.prepareStatement(sql)).thenThrow(SQLException.class);
			accountDAO.setConn(connection);
			assertEquals(null, accountDAO.readAccount(username));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public AccountDAOTest() throws SQLException {
		
	}
}
