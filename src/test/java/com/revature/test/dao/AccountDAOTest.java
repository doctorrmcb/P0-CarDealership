package com.revature.test.dao;

import static org.junit.Assert.*;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.AccountDAOSerialization;
import com.revature.pojos.authentication.Account;

public class AccountDAOTest {

	AccountDAOSerialization dao = new AccountDAOSerialization();
	
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
	public void testFileCreation() {
		// Check if file that is supposed to exist after method runs actually exists.
		File testFile = new File(".//src//main//resources//accounts//Testuser.dat");
		Account account = new Account("Testuser", "Testpass", "Employee");
		//AccountDAOSerialization dao = new AccountDAOSerialization();
		dao.createAccount(account);
		Boolean fileExists = testFile.exists();
		assertTrue(fileExists);
	}
	
	@Test
	public void testDirectoryCreation() {
		// Check if appropriate directory is created.
		File testDir = new File(".//src//main//resources//accounts");
		Account account = new Account("Testuser", "Testpass", "Employee");
		//AccountDAOSerialization dao = new AccountDAOSerialization();
		dao.createAccount(account);
		Boolean dirExists = testDir.exists();
		assertTrue(dirExists);
	}
	
	@Test
	public void testReadFile() {
		// Check if contents of test file makes correct account object.
		Account account = new Account("Testuser", "Testpass", "Employee");
		//AccountDAOSerialization dao = new AccountDAOSerialization();
		Account testAccount = dao.readAccount("Testuser");
		assertEquals(account, testAccount);
	}
	
	@Test
	public void testVoidUsername() {
		Account account = new Account(null, "Testpass", "Employee");
		
		boolean result = dao.createAccount(account);
		assertFalse(result);
	}
	
	@Test
	public void testFileNotFound() {
		Account testAccount = dao.readAccount("NotFound");
		assertEquals(testAccount, null);
	}
}
