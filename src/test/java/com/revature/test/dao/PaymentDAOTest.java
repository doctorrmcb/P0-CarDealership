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

import com.revature.dao.PaymentDAOPostgres;
import com.revature.pojos.finance.Payment;
import com.revature.util.ConnectionFactory;

@RunWith(MockitoJUnitRunner.class)
public class PaymentDAOTest {
	
	@Mock
	Connection connection;
	
	@Spy
	PaymentDAOPostgres paymentDAO = new PaymentDAOPostgres();
	
	@Spy
	PreparedStatement stmtCreate = ConnectionFactory.getConnection()
		.prepareStatement("insert into test.payments (paymentusername, vin, paymenttime, amount) values (?, ?, ?, ?);");
	
	@Spy
	PreparedStatement stmtRead = ConnectionFactory.getConnection()
		.prepareStatement("select * from test.payments where vin = ? and paymenttime = ?;");
	
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
	public void createPaymentTest() {
		String sql = "insert into test.payments (paymentusername, vin, paymenttime, amount) values (?, ?, ?, ?);";
		Payment payment = new Payment(20.00, "testUser", "testVin");
		boolean result = true;
		try {
			when(connection.prepareStatement(sql)).thenReturn(stmtCreate);
			paymentDAO.setConn(connection);
			assertEquals(result, paymentDAO.createPayment(payment));
			Mockito.verify(stmtCreate).executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void readPaymentTest() {
		String sql = "select * from test.payments where vin = ? and paymenttime = ?;";
		String paymentID = "testPaymentID_Blah";
		try {
			when(connection.prepareStatement(sql)).thenReturn(stmtRead);
			paymentDAO.setConn(connection);
			assertEquals(null, paymentDAO.readPayment(paymentID));
			Mockito.verify(stmtRead).executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getTotalPaidTest() {
		
	}
	
	public PaymentDAOTest() throws SQLException {
		
	}
}
