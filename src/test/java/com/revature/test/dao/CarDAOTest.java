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
	public void testCreateCarFileSuccess() {
		// Check if file that is supposed to exist after method runs actually exists.
		File testFile = new File(".//src//main//resources//cars//TestVin.dat");
		Car car = new Car("TestVin", "TestOwner");
		dao.createCar(car);
		Boolean fileExists = testFile.exists();
		assertTrue(fileExists);
	}
	
	@Test
	public void testDirectoryCreation() {
		// Check if appropriate directory is created.
		File testDir = new File(".//src//main//resources//cars");
		Car car = new Car("TestVin", "TestOwner");
		dao.createCar(car);
		Boolean dirExists = testDir.exists();
		assertTrue(dirExists);
	}
	
	@Test
	public void testReadFile() {
		// Check if contents of test file makes correct account object.
		Car car = new Car("TestVin", "TestOwner");
		//AccountDAOSerialization dao = new AccountDAOSerialization();
		Car testCar = dao.readCar("TestVin");
		assertEquals(car, testCar);
	}
	
	@Test
	public void testVoidVin() {
		Car car = new Car(null, null);
		
		boolean result = dao.createCar(car);
		assertFalse(result);
	}
	
	@Test
	public void testFileNotFound() {
		Car testCar = dao.readCar("NotFound");
		assertEquals(testCar, null);
	}

	@Test
	public void testUpdate() {
		Car updatedCar = new Car("UpdatedVin", "UpdatedOwner");
		String vinUpdate = new String("TestVin");
		boolean result = dao.updateCar(vinUpdate, updatedCar);
		assertEquals(true, result);
	}
	
	@Test
	public void testDelete() {
		String vinDelete = new String("UpdatedVin");
		String fileDeleteTest = ".//src//main//resources//cars//UpdatedVin.dat";
		File fileDelete = new File(fileDeleteTest);
		boolean result2 = dao.deleteCar(vinDelete);
		boolean result1 = !fileDelete.exists();
		boolean result;
		if (result1 == true && result2 == true) {
			result = true;
		} else {
			result = false;
		}
		assertEquals(true, result);
	}
}
