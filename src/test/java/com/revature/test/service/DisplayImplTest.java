package com.revature.test.service;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

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

import com.revature.pojos.io.menu.EmployeeMenu;
import com.revature.service.DisplayImpl;

import static com.revature.util.LoggerUtil.*;


@RunWith(MockitoJUnitRunner.class)
public class DisplayImplTest {

	//@Spy
	//DisplayImpl impl = new DisplayImpl();
	
	ByteArrayOutputStream newByteOut = new ByteArrayOutputStream();
	PrintStream originalOut = new PrintStream(System.out);	
	@Spy
	PrintStream newOut = new PrintStream(newByteOut);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		System.setOut(newOut);
	}

	@After
	public void tearDown() throws Exception {
		System.setOut(originalOut);
	}

	@Test
	public void displayMenuTest() {
		EmployeeMenu testMenu = new EmployeeMenu();
		DisplayImpl impl = new DisplayImpl();
		impl.displayMenu(testMenu);
		Mockito.verify(newOut).println("\nThank you for logging in.\n");
	}

}
