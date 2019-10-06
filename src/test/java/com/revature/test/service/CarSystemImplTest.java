package com.revature.test.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

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

import com.revature.dao.AccountDAOPostgres;
import com.revature.dao.CarDAOPostgres;
import com.revature.dao.OfferDAOPostgres;
import com.revature.dao.PaymentDAOPostgres;
import com.revature.pojos.Car;
import com.revature.pojos.authentication.Account;
import com.revature.pojos.authentication.LoginAttempt;
import com.revature.pojos.finance.Offer;
import com.revature.pojos.finance.Payment;
import com.revature.pojos.io.Menu;
import com.revature.pojos.io.menu.AddCarMenu;
import com.revature.pojos.io.menu.CustomerMenu;
import com.revature.pojos.io.menu.EmployeeMenu;
import com.revature.pojos.io.menu.LoginMenu;
import com.revature.pojos.io.menu.MakeAnOfferMenu;
import com.revature.pojos.io.menu.MakePaymentMenu;
import com.revature.pojos.io.menu.ManageCarsMenu;
import com.revature.pojos.io.menu.ManageOffersMenu;
import com.revature.pojos.io.menu.RegisterMenu;
import com.revature.pojos.io.menu.RemoveCarMenu;
import com.revature.pojos.io.menu.ViewMyCarsMenu;
import com.revature.pojos.io.menu.ViewNewCarsMenu;
import com.revature.pojos.io.menu.ViewPaymentsMenu;
import com.revature.service.CarSystemImpl;
import com.revature.service.DisplayImpl;

@RunWith(MockitoJUnitRunner.class)
public class CarSystemImplTest {

	@Spy
	CarSystemImpl impl = new CarSystemImpl();
	
	@Mock
	CarDAOPostgres carDAO;
	
	@Mock
	AccountDAOPostgres accountDAO;
	
	@Mock
	OfferDAOPostgres offerDAO;
	
	@Mock
	PaymentDAOPostgres paymentDAO;
	
	
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
		Menu currentMenu = new AddCarMenu();
		String input = "Anything goes.";
		impl.getNextMenu(input, currentMenu);
		Mockito.verify(impl).getSpecialMenu(input, currentMenu);
	}
	
	@Test 
	public void getCommandContainsInput() {
		String input = "1";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Menu menu = new Menu();
		DisplayImpl display = new DisplayImpl();
		menu.possibleInputs.add("1");
		Scanner scanner = new Scanner(System.in);
		assertEquals(input, impl.getCommand(scanner, menu, display));
	}
	
	@Test 
	public void getCommandAsterisk() {
		String input = "Anything goes.";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Menu menu = new Menu();
		DisplayImpl display = new DisplayImpl();
		menu.possibleInputs.add("*");
		Scanner scanner = new Scanner(System.in);
		assertEquals(input, impl.getCommand(scanner, menu, display));
	}
	
	@Test 
	public void getCommandBadInput() {
		String input = "Bad\nGood";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		//String input2 = "Bad";
		//InputStream in2 = new ByteArrayInputStream(input2.getBytes());
		//System.setIn(in2);
		Menu menu = new Menu();
		menu.outputLines.add("Test line please ignore.");
		menu.possibleInputs.add("Good");
		Menu newMenu = new Menu();
		newMenu.outputLines.add("Test line 2 please ignore.");
		newMenu.possibleInputs.add("Bad");
		menu.possibleMenus.add(newMenu);
		DisplayImpl display = new DisplayImpl();
		Scanner scanner = new Scanner(System.in);
		//when(impl.getCommand(scanner, menu, display)).thenReturn("Good");
		assertEquals("Good", impl.getCommand(scanner, menu, display));
	}
	
	@Test
	public void tryLoginTrue() {
		LoginAttempt login = new LoginAttempt("testuser", "testpass");
		impl.setAccountDAO(accountDAO);
		Account account = new Account("testuser", "testpass", "Employee");
		when(accountDAO.readAccount(login.username)).thenReturn(account);
		String[] result = {"true", "Employee"};
		assertArrayEquals(result, impl.tryLogin(login));
	}
	
	@Test
	public void tryLoginFalse() {
		LoginAttempt login = new LoginAttempt("", "pass");
		impl.setAccountDAO(accountDAO);
		Account account = null;
		when(accountDAO.readAccount(login.username)).thenReturn(account);
		String[] result = {"false", null};
		assertArrayEquals(result, impl.tryLogin(login));
	}
	
	@Test 
	public void getSpecialMenuLoginTest() {
		String input = "";
		LoginMenu currentMenu = new LoginMenu();
		impl.getSpecialMenu(input, currentMenu);
		Mockito.verify(impl).getNextMenuLogin(input);
		//impl.setAccountDAO(accountDAO);
		//when(impl.getSpecialMenu(input, currentMenu)).thenReturn(currentMenu);
		//assertEquals(currentMenu, currentMenu);
	}
	
	@Test
	public void getSpecialMenuAddCarTest() {
		String input = "testvin testowner";
		AddCarMenu currentMenu = new AddCarMenu();
		impl.getSpecialMenu(input, currentMenu);
		Mockito.verify(impl).getNextMenuAddCar(input);
	}
	
	@Test
	public void getSpecialMenuMakeAnOfferTest() {
		String input = "testvin testowner";
		MakeAnOfferMenu currentMenu = new MakeAnOfferMenu();
		impl.getSpecialMenu(input, currentMenu);
		Mockito.verify(impl).getNextMenuMakeOffer(input);
	}
	
	@Test
	public void getSpecialMenuManageOfferTest() {
		String input = "testvin testowner";
		ManageOffersMenu currentMenu = new ManageOffersMenu();
		impl.getSpecialMenu(input, currentMenu);
		Mockito.verify(impl).getNextMenuManageOffers(input);
	}
	
	@Test
	public void getSpecialMenuRemoveCarTest() {
		String input = "testvin";
		RemoveCarMenu currentMenu = new RemoveCarMenu();
		impl.getSpecialMenu(input, currentMenu);
		Mockito.verify(impl).getNextMenuRemoveCar(input);
	}
	
	@Test
	public void getSpecialMenuMyCarsTest() {
		String input = "testvin";
		ViewMyCarsMenu currentMenu = new ViewMyCarsMenu();
		impl.getSpecialMenu(input, currentMenu);
		Mockito.verify(impl).getNextMenuMyCars(input);
	}
	
	@Test
	public void getSpecialMenuNewCarsTest() {
		String input = "testvin";
		ViewNewCarsMenu currentMenu = new ViewNewCarsMenu();
		impl.getSpecialMenu(input, currentMenu);
		Mockito.verify(impl).getNextMenuNewCars(input);
	}
	
	@Test
	public void getSpecialMenuViewPaymentsTest() {
		String input = "testvin";
		ViewPaymentsMenu currentMenu = new ViewPaymentsMenu();
		impl.getSpecialMenu(input, currentMenu);
		Mockito.verify(impl).getNextMenuAllPayments(input);
	}
	
	@Test
	public void getSpecialMenuMakePaymentsTest() {
		String input = "testvin";
		MakePaymentMenu currentMenu = new MakePaymentMenu();
		impl.getSpecialMenu(input, currentMenu);
		Mockito.verify(impl).getNextMenuMakePayment(input);
	}
	
	@Test
	public void getSpecialMenuNullTest() {
		String input = "testvin";
		MakePaymentMenu currentMenu = null;
		assertEquals(null, impl.getSpecialMenu(input, currentMenu));
	}
	
	@Test
	public void getNextMenuAddCar() {
		String input = "Back";
		ManageCarsMenu testMenu = new ManageCarsMenu();
		assertEquals(testMenu, impl.getNextMenuAddCar(input));
	}
	
	@Test
	public void getNextMenuAddCarSuccessTest() {
		String input = "TestVIN TestOwner";
		ManageCarsMenu testMenu = new ManageCarsMenu();
		String vin = "TestVIN";
		String owner = "TestOwner";
		Car car = new Car(vin, owner);
		when(carDAO.createCar(car)).thenReturn(true);
		impl.setCarDAO(carDAO);
		Menu retMenu = impl.getNextMenuAddCar(input);
		assertEquals(testMenu, retMenu);
	}
	
	@Test
	public void getNextMenuAddCarFailTest() {
		String input = "TestVIN TestOwner";
		AddCarMenu testMenu = new AddCarMenu();
		Car car = new Car();
		when(carDAO.createCar(car)).thenReturn(false);
		impl.setCarDAO(carDAO);
		Menu retMenu = impl.getNextMenuAddCar(input);
		assertEquals(testMenu, retMenu);
	}

	@Test
	public void tryRegisterBadAccount() {
		String input = "TestUser TestPass Employee";
		Account account = new Account("TestUser", "TestPass", "Employee");
		RegisterMenu testMenu = new RegisterMenu();
		when(impl.tryRegister(account)).thenReturn(false);
		assertEquals(testMenu, impl.getNextMenuRegister(input));
	}
	
	@Test
	public void tryRegisterGoodEmployeeAccount() {
		String input = "TestUser TestPass Employee";
		Account account = new Account("TestUser", "TestPass", "Employee");
		EmployeeMenu testMenu = new EmployeeMenu();
		when(impl.tryRegister(account)).thenReturn(true);
		assertEquals(testMenu, impl.getNextMenuRegister(input));
		//TODO: Write accountDAO.deleteAccount then run it with the created account to return database back to original state.
	}
	
	@Test
	public void tryRegisterGoodCustomerAccount() {
		String input = "TestCust TestPass Customer";
		Account account = new Account("TestCust", "TestPass", "Customer");
		CustomerMenu testMenu = new CustomerMenu();
		when(impl.tryRegister(account)).thenReturn(true);
		assertEquals(testMenu, impl.getNextMenuRegister(input));
		//TODO: Write accountDAO.deleteAccount then run it with the created account to return database back to original state.
	}
	
	@Test
	public void getNextMenuLoginFailTest() {
		String input = "userNotExist passwordNotExist";
		LoginAttempt login = new LoginAttempt("userNotExist", "passwordNotExist");
		LoginMenu testMenu = new LoginMenu();
		when(impl.tryLogin(login)).thenReturn(null);
		assertEquals(testMenu, impl.getNextMenuLogin(input));
	}
	
	@Test
	public void getNextMenuLoginSuccessEmployeeTest() {
		String input = "userExists passwordExists";
		LoginAttempt login = new LoginAttempt("userExists", "passwordExists");
		EmployeeMenu testMenu = new EmployeeMenu();
		String[] arrReturn = {"true", "Employee"};
		when(impl.tryLogin(login)).thenReturn(arrReturn);
		assertEquals(testMenu, impl.getNextMenuLogin(input));
	}
	
	@Test
	public void getNextMenuLoginSuccessCustomerTest() {
		String input = "userExists passwordExists";
		LoginAttempt login = new LoginAttempt("userExists", "passwordExists");
		CustomerMenu testMenu = new CustomerMenu();
		String[] arrReturn = {"true", "Customer"};
		when(impl.tryLogin(login)).thenReturn(arrReturn);
		assertEquals(testMenu, impl.getNextMenuLogin(input));
	}
	
	@Test
	public void getNextMenuMakeOfferBackTest() {
		String input = "Back";
		ViewNewCarsMenu testMenu = new ViewNewCarsMenu();
		assertEquals(testMenu, impl.getNextMenuMakeOffer(input));
	}
	
	@Test
	public void getNextMenuMakeOfferSuccessTest() {
		String input = "20.00 20";
		impl.setOfferDAO(offerDAO);
		ViewNewCarsMenu testMenu = new ViewNewCarsMenu();
		Offer offer = new Offer("", 20.00, "", 20);
		when(offerDAO.createOffer(offer)).thenReturn(true);
		assertEquals(testMenu, impl.getNextMenuMakeOffer(input));
	}
	
	@Test
	public void getNextMenuMakeOfferFailTest() {
		String input = "20.00 20";
		impl.setOfferDAO(offerDAO);
		MakeAnOfferMenu testMenu = new MakeAnOfferMenu();
		Offer offer = new Offer("", 20.00, "", 20);
		when(offerDAO.createOffer(offer)).thenReturn(false);
		assertEquals(testMenu, impl.getNextMenuMakeOffer(input));
	}
	
	@Test
	public void getNextMenuMakeOfferExceptionTest() {
		// Figure out how to return an exception.
	}
	
	@Test
	public void getNextMenuRemoveCarBackTest() {
		String input = "Back";
		ManageCarsMenu testMenu = new ManageCarsMenu();
		assertEquals(testMenu, impl.getNextMenuRemoveCar(input));
	}
	
	@Test
	public void getNextMenuRemoveCarFailTest() {
		String input = "Fail";
		impl.setCarDAO(carDAO);
		RemoveCarMenu testMenu = new RemoveCarMenu();
		when(carDAO.deleteCar(input)).thenReturn(false);
		assertEquals(testMenu, impl.getNextMenuRemoveCar(input));
	}
	
	@Test
	public void getNextMenuMyCarsBackTest() {
		String input = "Back";
		CustomerMenu testMenu = new CustomerMenu();
		assertEquals(testMenu, impl.getNextMenuMyCars(input));
	}
	
	@Test
	public void getNextMenuNewCarsBackTest() {
		String input = "Back";
		CustomerMenu testMenu = new CustomerMenu();
		assertEquals(testMenu, impl.getNextMenuNewCars(input));
	}
	
	@Test
	public void getNextMenuNewCarsValidTest() {
		String input = "test";
		MakeAnOfferMenu testMenu = new MakeAnOfferMenu();
		impl.setCarDAO(carDAO);
		Car car = new Car("testVin", "testOwner");
		when(carDAO.readCar(input)).thenReturn(car);
		assertEquals(testMenu, impl.getNextMenuNewCars(input));
	}
	
	@Test
	public void getNextMenuAllPaymentsBackTest() {
		String input = "Back";
		ManageCarsMenu testMenu = new ManageCarsMenu();
		assertEquals(testMenu, impl.getNextMenuAllPayments(input));
	}
	
	@Test
	public void getNextMenuMakePaymentBackTest() {
		String input = "Back";
		CustomerMenu testMenu = new CustomerMenu();
		assertEquals(testMenu, impl.getNextMenuMakePayment(input));
	}
	
	@Test
	public void getNextMenuMakePaymentSuccessTest() {
		String input = "20.00 testVin";
		Payment payment = new Payment(20.00, "", "testVin");
		impl.setPaymentDAO(paymentDAO);
		when(paymentDAO.createPayment(payment)).thenReturn(true);
		CustomerMenu testMenu = new CustomerMenu();
		assertEquals(testMenu, impl.getNextMenuMakePayment(input));
	}
}
