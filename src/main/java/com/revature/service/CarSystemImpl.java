package com.revature.service;

import java.util.Scanner;

import com.revature.dao.AccountDAOSerialization;
import com.revature.pojos.authentication.Account;
import com.revature.pojos.authentication.LoginAttempt;
import com.revature.pojos.io.Menu;
import com.revature.pojos.io.menu.CustomerMenu;
import com.revature.pojos.io.menu.EmployeeMenu;
import com.revature.pojos.io.menu.LoginMenu;
import com.revature.pojos.io.menu.RegisterMenu;

public class CarSystemImpl implements CarSystem {
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
			/*// A special process must be run that is menu dependent.
			if (currentMenu instanceof LoginMenu) {
				// Build an instance of LoginAttempt from input and try to log in.
				String[] inputArray = input.split(" ");
				String username = inputArray[0];
				String password = inputArray[1];
				LoginAttempt login = new LoginAttempt(username, password);
				String[] result = tryLogin(login);
				if(result[0].equals("true") && result[1].equals("Employee")) {
					return new EmployeeMenu();
				} else if (result[0].equals("true") && result[1].equals("Customer")) {
					return new CustomerMenu();
				} else {
					System.out.println("Login attempt failed, please try again.");
					//TODO Redo login menu.
				}
				// Display success or failure of attempt and from that decide which menu to run.
			} else if (currentMenu instanceof RegisterMenu) {
				// Build an instance of Account from input and try to register the account.
				String[] inputArray = input.split(" ");
				String username = inputArray[0];
				String password = inputArray[1];
				String accountStatus = inputArray[2];
				Account account = new Account(username, password, accountStatus);
				boolean result = tryRegister(account);
				if (result == false) {
					System.out.println("Registration failed, please try again.");
					//TODO redo registration menu.
				} else {
					System.out.println("Registration success.");
				}
				// Display success or failure of attempt and decide what to display based on that.
			}*/
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
		AccountDAOSerialization accountDAO = new AccountDAOSerialization();
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
		AccountDAOSerialization accountDAO = new AccountDAOSerialization();
		boolean result = accountDAO.createAccount(account);
		return result;
	}
	
	public Menu getSpecialMenu(String input, Menu currentMenu) {
		// A special process must be run that is menu dependent.
		if (currentMenu instanceof LoginMenu) {
			// Build an instance of LoginAttempt from input and try to log in.
			String[] inputArray = input.split(" ");
			String username = inputArray[0];
			String password = inputArray[1];
			LoginAttempt login = new LoginAttempt(username, password);
			String[] result = tryLogin(login);
			if(result[0].equals("true") && result[1].equals("Employee")) {
				return new EmployeeMenu();
			} else if (result[0].equals("true") && result[1].equals("Customer")) {
				return new CustomerMenu();
			} else {
				System.out.println("Login attempt failed, please try again.");
				//TODO Redo login menu.
				return new LoginMenu();
			}
			// Display success or failure of attempt and from that decide which menu to run.
		} else if (currentMenu instanceof RegisterMenu) {
			// Build an instance of Account from input and try to register the account.
			String[] inputArray = input.split(" ");
			String username = inputArray[0];
			String password = inputArray[1];
			String accountStatus = inputArray[2];
			Account account = new Account(username, password, accountStatus);
			boolean result = tryRegister(account);
			if (result == false) {
				System.out.println("Registration failed, please try again.");
				//TODO redo registration menu.
				return new RegisterMenu();
			} else {
				System.out.println("Registration success.");
				if (accountStatus.equals("Employee")) {
					return new EmployeeMenu();
				} else {
					return new CustomerMenu();
				}
			}
			// Display success or failure of attempt and decide what to display based on that.
		} else {
			return null;
		}
	}
}
