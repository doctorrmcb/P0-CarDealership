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

import com.revature.dao.OfferDAOPostgres;
import com.revature.pojos.finance.Offer;
import com.revature.util.ConnectionFactory;

@RunWith(MockitoJUnitRunner.class)
public class OfferDAOTest {

	@Mock
	Connection connection;
	
	@Spy
	OfferDAOPostgres offerDAO = new OfferDAOPostgres();
	
	@Spy
	PreparedStatement stmtCreate = ConnectionFactory.getConnection()
		.prepareStatement("insert into test.offers (offerusername, vin, price, durationmonths, offerstatus) values (?, ?, ?, ?, ?);");
	
	@Spy
	PreparedStatement stmtRead = ConnectionFactory.getConnection()
		.prepareStatement("select * from test.offers where offerusername = ? and vin = ?;");
	
	
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
	public void createOfferTest() {
		String sql = "insert into test.offers (offerusername, vin, price, durationmonths, offerstatus) values (?, ?, ?, ?, ?);";
		Offer offer = new Offer("testUser", 20.00, "testVin", 20);
		boolean result = false;
		try {
			when(connection.prepareStatement(sql)).thenReturn(stmtCreate);
			offerDAO.setConn(connection);
			assertEquals(result, offerDAO.createOffer(offer));
			Mockito.verify(stmtCreate).executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void readOfferTest() {
		String sql = "select * from test.offers where offerusername = ? and vin = ?;";
		String offerID = "testOfferID_Blah";
		try {
			when(connection.prepareStatement(sql)).thenReturn(stmtRead);
			offerDAO.setConn(connection);
			assertEquals(null, offerDAO.readOffer(offerID));
			Mockito.verify(stmtRead).executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public OfferDAOTest() throws SQLException {
		
	}
}
