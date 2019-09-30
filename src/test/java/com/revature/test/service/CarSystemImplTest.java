package com.revature.test.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.pojos.io.Menu;
import com.revature.service.CarSystemImpl;

public class CarSystemImplTest {

	CarSystemImpl impl = new CarSystemImpl();
	
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

	// Testing testing.
	@Test
	public void getNextMenuEmptyMenuTest() {
		Menu menu = new Menu();
		Menu retMenu = impl.getNextMenu("test", menu);
		assertEquals(retMenu, null);
	}

	@Test
	public void getNextRealMenuTest() {
		String[] posInput = {"test1", "test2", "test3"};
		String[] outputs = {"testOut1", "testOut2", "testOut3"};
		Menu menu1 = new Menu();
		Menu menu2 = new Menu();
		Menu menu3 = new Menu();
		Menu[] menus = {menu1, menu2, menu3};
		Menu testMenu = new Menu(posInput, outputs, menus);
		Menu returnMenu = impl.getNextMenu("test1", testMenu);
		Menu testRetMenu = new Menu();
		assertEquals(returnMenu, testRetMenu);	
	}
	
	@Test
	public void getFailureMenuTest() {
		
	}
}
