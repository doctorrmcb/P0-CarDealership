package com.revature.test.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.revature.pojos.io.Menu;
import com.revature.pojos.io.menu.EmployeeMenu;
import com.revature.pojos.io.menu.RegisterMenu;
import com.revature.service.CarSystemImpl;

@RunWith(MockitoJUnitRunner.class)
public class CarSystemImplTest {

	CarSystemImpl impl = new CarSystemImpl();
	
	@Mock
	CarSystemImpl mockImpl;
	
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
	public void getNextMenuEmptyTest() {
		Menu menu = new Menu();
		Menu retMenu = impl.getNextMenu("test", menu);
		assertEquals(retMenu, null);
	}

	@Test
	public void getNextMenuRealTest() {
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
	public void getNextMenuBadInputTest() {
		String badInput = "bad";
		String[] posInput = {"test1", "test2", "test3"};
		String[] outputs = {"testOut1", "testOut2", "testOut3"};
		Menu menu1 = new Menu();
		Menu menu2 = new Menu();
		Menu menu3 = new Menu();
		Menu[] menus = {menu1, menu2, menu3};
		Menu testMenu = new Menu(posInput, outputs, menus);
		Menu testRetMenu = impl.getNextMenu(badInput, testMenu);
		assertEquals(testRetMenu, null);
	}
	
	@Test
	public void getSpecialMenuFromGetNextMenuTest() {
		Menu employeeMenu = new EmployeeMenu();
		RegisterMenu testMenu = new RegisterMenu();
		//when(impl.getSpecialMenu("user1 pass1 Employee", testMenu)).thenReturn(new EmployeeMenu());
		Menu retTestMenu = impl.getNextMenu("user1 pass1 Employee", testMenu);
		assertEquals(employeeMenu, retTestMenu);
	}
	
	@Test 
	public void getCommandContainsInput() {
		
	}
	
	@Test 
	public void getCommandAsterisk() {
		
	}
	
	@Test 
	public void getCommandBadInput() {
		
	}
	
	@Test
	public void tryLoginTrue() {
		
	}
	
	@Test
	public void tryLoginFalse() {
		
	}
	
	@Test
	public void tryRegisterGoodAccount() {
		
	}
	
	@Test
	public void tryRegisterBadAccount() {
		
	}
	
	@Test 
	public void getSpecialMenuTest() {
		
	}
}
