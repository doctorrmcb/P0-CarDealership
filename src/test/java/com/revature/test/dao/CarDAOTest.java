package com.revature.test.dao;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.CarDAOSerialization;
import com.revature.pojos.Car;
import com.revature.pojos.authentication.Account;

public class CarDAOTest {

	CarDAOSerialization dao = new CarDAOSerialization();
	
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
	public void testCreateCarSuccess() {
		// Check if file that is supposed to exist after method runs actually exists.
		File testFile = new File(".//src//main//resources//cars//TestVin.dat");
		Car car = new Car("TestVin", "TestOwner");
		dao.createCar(car);
		Boolean fileExists = testFile.exists();
		assertTrue(fileExists);
	}

}
