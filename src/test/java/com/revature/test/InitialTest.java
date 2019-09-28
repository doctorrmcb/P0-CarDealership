package com.revature.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class InitialTest {

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
	public void test() {
		fail("Not yet implemented");
	}
	
	// When program starts, display initial menu.
	
	// User/employee login with correct password and username.
	// User/employee can't login with correct username and incorrect password.
	// Exception is raised when user/employee tries to login with username that doesn't exist.
	// If exception is raised for logging in with nonexistant username, then system asks if user wants to create a new account.
	
	
	// Employee can create car object if passing in correct info.
	// User can't create car object if passing in correct info.
	
	// Upon login user sees correct menu output.
	// Upon login employee sees correct menu output.
	
	// If customer enters the menu input in the main menu, should get output of cars.
	// If customer enters a specific set of values into the search object builder it returns the correct search object.
	// If search function has appropriate search object inserted it looks for cars matching all of those values and returns any cars that do.
	// If customer enters the appropriate data into the make offer function it returns a correctly created offer object to the system.
	// If an offer object is entered to the system it adds it to the list of offers the employee can see.
	// If an employee chooses the read offers in the menu, it calls the readOfffers method and displays all the offers.
	// If an employee accepts an offer, it gets communicated to the user
}
