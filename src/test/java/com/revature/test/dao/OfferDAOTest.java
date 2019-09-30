package com.revature.test.dao;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.OfferDAOSerialization;
import com.revature.pojos.Car;
import com.revature.pojos.finance.Offer;

public class OfferDAOTest {

	OfferDAOSerialization dao = new OfferDAOSerialization();
	
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
	public void testCreateOfferFileSuccess() {
		// Check if file that is supposed to exist after method runs actually exists.
		File testFile = new File(".//src//main//resources//offers//TestOffer.dat");
		Offer offer = new Offer("TestUser", 30000.00, "TestVin", 36);
		dao.createOffer(offer);
		Boolean fileExists = testFile.exists();
		assertTrue(fileExists);
	}
	
	@Test
	public void testDirectoryCreation() {
		// Check if appropriate directory is created.
		File testDir = new File(".//src//main//resources//offers");
		Offer offer = new Offer("TestUser", 30000.00, "TestVin", 36);
		dao.createOffer(offer);
		Boolean dirExists = testDir.exists();
		assertTrue(dirExists);
	}
	
	@Test
	public void testReadFile() {
		// Check if contents of test file makes correct account object.
		Offer offer = new Offer("TestUser", 30000.00, "TestVin", 36);
		Offer offerTest = dao.readOffer("TestUserTestVin");
		assertEquals(offer, offerTest);
	}
	
	@Test
	public void testVoidVin() {
		Offer offer = new Offer();
		boolean result = dao.createOffer(offer);
		assertFalse(result);
	}
	
	@Test
	public void testFileNotFound() {
		Offer offer = dao.readOffer("NotFound");
		assertEquals(offer, null);
	}

	@Test
	public void testUpdate() {
		Offer updatedOffer = new Offer("TestUser", 30000.00, "TestVin", 36);
		updatedOffer.setStatus("Accepted");
		String offerIdUpdate = new String("TestUserTestVin");
		boolean result = dao.updateOffer(offerIdUpdate, updatedOffer);
		assertEquals(true, result);
	}
	
	@Test
	public void testDelete() {
		String offerIdDelete = new String("TestUserTestVin");
		String fileDeleteTest = ".//src//main//resources//cars//TestUserTestVin.dat";
		File fileDelete = new File(fileDeleteTest);
		boolean result2 = dao.deleteOffer(offerIdDelete);
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
