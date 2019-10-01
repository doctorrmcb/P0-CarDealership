package com.revature.service;

import java.util.Scanner;

import com.revature.dao.AccountDAOSerialization;
import com.revature.dao.CarDAOSerialization;
import com.revature.dao.OfferDAOSerialization;
import com.revature.dao.PaymentDAOSerialization;
import com.revature.pojos.Car;
import com.revature.pojos.authentication.Account;
import com.revature.pojos.authentication.LoginAttempt;
import com.revature.pojos.io.Menu;
import com.revature.pojos.io.menu.AddCarMenu;
import com.revature.pojos.io.menu.CustomerMenu;
import com.revature.pojos.io.menu.EmployeeMenu;
import com.revature.pojos.io.menu.LoginMenu;
import com.revature.pojos.io.menu.MakeAnOfferMenu;
import com.revature.pojos.io.menu.ManageCarsMenu;
import com.revature.pojos.io.menu.ManageOffersMenu;
import com.revature.pojos.io.menu.RegisterMenu;
import com.revature.pojos.io.menu.RemoveCarMenu;
import com.revature.pojos.io.menu.ViewMyCarsMenu;
import com.revature.pojos.io.menu.ViewMyRemainingPaymentsMenu;
import com.revature.pojos.io.menu.ViewNewCarsMenu;
import com.revature.pojos.io.menu.ViewPaymentsMenu;

public class CarSystemImpl implements CarSystem {
	
	private AccountDAOSerialization accountDAO = new AccountDAOSerialization();
	private CarDAOSerialization carDAO = new CarDAOSerialization();
	private OfferDAOSerialization offerDAO = new OfferDAOSerialization();
	private PaymentDAOSerialization paymentDAO = new PaymentDAOSerialization();
	
	public void setOfferDAO(OfferDAOSerialization offerDAO) {
		this.offerDAO = offerDAO;
	}

	public void setPaymentDAO(PaymentDAOSerialization paymentDAO) {
		this.paymentDAO = paymentDAO;
	}

	public void setCarDAO(CarDAOSerialization carDAO) {
		this.carDAO = carDAO;
	}

	public void setAccountDAO(AccountDAOSerialization accountDAO) {
		this.accountDAO = accountDAO;
	}
	
	public String getCommand(Scanner scanner, Menu menu, DisplayImpl display) {
		// TODO Auto-generated method stub
		String input = scanner.nextLine();
		if (menu.possibleInputs.contains(input)) {
			return input;
		} else if (menu.possibleInputs.get(0).equals("*")) {
			// A special process must be run that is menu dependent.
			return input;
		} else {
			System.out.println("\nI don't understand that command, please try again.");
			display.displayMenu(menu);
			getCommand(scanner, menu, display);
			return input;
		}
	}
	
	public Menu getNextMenu(String input, Menu currentMenu) {
		// Clean up the user input.
		input = input.trim();
		if (currentMenu.possibleInputs.isEmpty()) {
			return null;
		}
		// If the possibleUserInputs is "*"
		if (currentMenu.possibleInputs.get(0).equals("*")) {
			return getSpecialMenu(input, currentMenu);
		} else {
			for (int i = 0; i < currentMenu.possibleInputs.size(); i++) {
				if (input.equals(currentMenu.possibleInputs.get(i))) {
					return currentMenu.possibleMenus.get(i);
				}
			}
		}
		// The program should never get to this point, because the user's input was validated during the getCommand function.
		return null;
	}
	
	public String[] tryLogin(LoginAttempt loginAttempt) {
		Account account = accountDAO.readAccount(loginAttempt.username);
		if (account != null) {
			String[] result = {"true", account.getAccountStatus()};
			return result;
		} else {
			String[] result = {"false", account.getAccountStatus()};
			return result;
		}
	}
	
	public boolean tryRegister(Account account) {
		//AccountDAOSerialization accountDAO = new AccountDAOSerialization();
		boolean result = accountDAO.createAccount(account);
		return result;
	}
	
	public Menu getSpecialMenu(String input, Menu currentMenu) {
		// A special process must be run that is menu dependent.
		// Run the special process that returns the next menu that should be displayed.
		if (currentMenu instanceof LoginMenu) {
			return getNextMenuLogin(input);
		} else if (currentMenu instanceof RegisterMenu) {
			return getNextMenuRegister(input);
		} else if (currentMenu instanceof AddCarMenu) {
			return getNextMenuAddCar(input);
		} else if (currentMenu instanceof MakeAnOfferMenu) {
			return getNextMenuMakeOffer(input);
		} else if (currentMenu instanceof ManageOffersMenu) {
			return getNextMenuManageOffers(input);
		} else if (currentMenu instanceof RemoveCarMenu) {
			return getNextMenuRemoveCar(input);
		} else if (currentMenu instanceof ViewMyCarsMenu) {
			return getNextMenuMyCars(input);
		} else if (currentMenu instanceof ViewNewCarsMenu) {
			return getNextMenuNewCars(input);
		} else if (currentMenu instanceof ViewPaymentsMenu) {
			return getNextMenuAllPayments(input);
		} else {
			return null;
		}
	}
	
	public Menu getNextMenuRegister(String input) {
		// Build an instance of Account from input and try to register the account.
		String[] inputArray = input.split(" ");
		String username = inputArray[0];
		String password = inputArray[1];
		String accountStatus = inputArray[2];
		Account account = new Account(username, password, accountStatus);
		boolean result = tryRegister(account);
		// Display success or failure of attempt and decide what to display based on that.
		if (result == false) {
			System.out.println("Registration failed, please try again.");
			return new RegisterMenu();
		} else {
			System.out.println("Registration success.");
			if (accountStatus.equals("Employee")) {
				return new EmployeeMenu();
			} else {
				return new CustomerMenu();
			}
		}
	}
	
	public Menu getNextMenuLogin(String input) {
		// Build an instance of LoginAttempt from input and try to log in.
		String[] inputArray = input.split(" ");
		String username = inputArray[0];
		String password = inputArray[1];
		LoginAttempt login = new LoginAttempt(username, password);
		String[] result = tryLogin(login);
		Menu.userName = username;
		// Display success or failure of attempt and from that decide which menu to run.
		if(result[0].equals("true") && result[1].equals("Employee")) {
			return new EmployeeMenu();
		} else if (result[0].equals("true") && result[1].equals("Customer")) {
			return new CustomerMenu();
		} else {
			System.out.println("Login attempt failed, please try again.");
			return new LoginMenu();
		}
	}
	
	public Menu getNextMenuAddCar(String input) {
		String[] inputArray = input.split(" ");
		String vin = inputArray[0];
		String owner = inputArray[1];
		Car car = new Car(vin, owner);
		//CarDAOSerialization carDAO = new CarDAOSerialization();
		boolean daoBool = carDAO.createCar(car);
		if (daoBool == true) {
			System.out.println("\nSuccessfully added car.");
			return new ManageCarsMenu();
		} else {
			System.out.println("\nFailed to add car, please try again.");
			return new AddCarMenu();
		}
		
	}
	
	public Menu getNextMenuMakeOffer(String input) {
		return null;
	}
	
	public Menu getNextMenuManageOffers(String input) {
		return null;
	}
	
	public Menu getNextMenuRemoveCar(String input) {
		String vin = input;
		if (input.contentEquals("Back")) {
			return new ManageCarsMenu();
		}
		boolean daoBool = carDAO.deleteCar(vin);
		if (daoBool == true) {
			System.out.println("Successfully deleted car.");
			return new ManageCarsMenu();
		} else {
			System.out.println("Failed to delete car, please try again.");
			return new RemoveCarMenu();
		}
	}
	
	public Menu getNextMenuMyCars(String input) {
		String username = Menu.userName;
		return null;
	}
	
	public Menu getNextMenuNewCars(String input) {
		return null;
	}
	
	public Menu getNextMenuAllPayments(String input) {
		return null;
	}
}
