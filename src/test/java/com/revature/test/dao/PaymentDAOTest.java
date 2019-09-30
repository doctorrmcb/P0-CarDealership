package com.revature.test.dao;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.PaymentDAOSerialization;
import com.revature.pojos.finance.Payment;

public class PaymentDAOTest {

	PaymentDAOSerialization dao = new PaymentDAOSerialization();
	
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
	public void testCreatePaymentFileSuccess() {
		// Check if file that is supposed to exist after method runs actually exists.
		File testFile = new File(".//src//main//resources//payments//TestUserTestVin.dat");
		Payment payment = new Payment(200.0, "testCreateUser", "testCreateVin");
		dao.createPayment(payment);
		Boolean fileExists = testFile.exists();
		assertTrue(fileExists);
	}
	
	@Test
	public void testDirectoryCreation() {
		// Check if appropriate directory is created.
		File testDir = new File(".//src//main//resources//payments");
		Payment payment = new Payment(200.0, "testDirUser", "testDirVin");
		dao.createPayment(payment);
		Boolean dirExists = testDir.exists();
		assertTrue(dirExists);
	}
	
	@Test
	public void testReadFile() {
		// Check if contents of test file makes correct account object.
		Payment payment = new Payment(200.0, "testReadUser", "testReadVin");
		Payment paymentTest = dao.readPayment("TestReadUserTestReadVin");
		assertEquals(payment, paymentTest);
	}
	
	@Test
	public void testVoidVin() {
		Payment payment = new Payment();
		boolean result = dao.createPayment(payment);
		assertFalse(result);
	}
	
	@Test
	public void testFileNotFound() {
		Payment payment = dao.readPayment("NotFound");
		assertEquals(payment, null);
	}
	/* This code is unnecessary, but I'm leaving it in just in case I need it down the road."
	@Test
	public void testUpdate() {
		// Changing from initial constructor declaration.
		Payment updatedPayment = new Payment(200.0, "testUpdateUser", "testUpdateVin");
		updatedPayment.setStatus("Accepted");
		// Reading the file to get the initial state.
		Payment testPayment2 = dao.readPayment("TestUserTestVin");
		String paymentIdUpdate = new String("TestUserTestVin");
		boolean result1 = dao.updatePayment(paymentIdUpdate, updatedPayment);
		Payment testPayment = dao.readPayment("TestUserTestVin");
		boolean result2 = testPayment.equals(updatedPayment);
		boolean result;
		if (result1 == true && result2 == true && !testPayment2.equals(testPayment)) {
			result = true;
		} else {
			result = false;
		}
		updatedPayment.setStatus("Pending");
		dao.updatePayment(paymentIdUpdate, updatedPayment);
		assertEquals(true, result);
	}
	*/
	
	@Test
	public void testDelete() {
		String paymentIdDelete = new String("TestUserTestVin");
		String fileDeleteTest = ".//src//main//resources//payments//TestUserTestVin.dat";
		File fileDelete = new File(fileDeleteTest);
		boolean result2 = dao.deletePayment(paymentIdDelete);
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
