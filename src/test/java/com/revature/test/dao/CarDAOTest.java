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

import com.revature.dao.CarDAOPostgres;
import com.revature.pojos.Car;
import com.revature.pojos.authentication.Account;
import com.revature.util.ConnectionFactory;

@RunWith(MockitoJUnitRunner.class)
public class CarDAOTest {

	@Mock
	Connection connection;
	
	@Spy
	CarDAOPostgres carDAO = new CarDAOPostgres();
	
	@Spy
	PreparedStatement stmtCreate = ConnectionFactory.getConnection()
		.prepareStatement("insert into test.cars (ownerusername, vin) values (?, ?);");
	
	@Spy
	PreparedStatement stmtRead = ConnectionFactory.getConnection()
		.prepareStatement("select * from test.cars where vin = ?;");

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
	public void createCarTest() {
		String sql = "insert into test.cars (ownerusername, vin) values (?, ?);";
		Car car = new Car("testVin", "testUser");
		boolean result = true;
		try {
			when(connection.prepareStatement(sql)).thenReturn(stmtCreate);
			carDAO.setConn(connection);
			assertEquals(result, carDAO.createCar(car));
			Mockito.verify(stmtCreate).executeUpdate();
			//carDAO.deleteCar("testVin");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void readCarTest() {
		String sql = "select * from test.cars where vin = ?;";
		String vin = "testVin";
		Car car = new Car("testVin", "testUser");
		try {
			when(connection.prepareStatement(sql)).thenReturn(stmtRead);
			carDAO.setConn(connection);
			assertEquals(car, carDAO.readCar(vin));
			Mockito.verify(stmtRead).executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void updateCarTest() {
		
	}

	public CarDAOTest() throws SQLException {
		
	}
}
