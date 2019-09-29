package com.revature.service;

import java.util.Scanner;

import com.revature.pojos.io.Menu;
import com.revature.pojos.menu.LoginMenu;
import com.revature.pojos.menu.RegisterMenu;

public class CarSystemImpl implements CarSystem {
	public String getCommand(Scanner scanner, Menu menu, DisplayImpl display) {
		// TODO Auto-generated method stub
		String input = scanner.nextLine();
		if (menu.possibleInputs.contains(input)) {
			return input;
		} else if (menu.possibleInputs.get(0).equals("*")) {
			// A special process must be run that is menu dependent.
			return "*";
		} else {
			System.out.println("\nI don't understand that command, please try again.");
			display.displayMenu(menu);
			getCommand(scanner, menu, display);
			return null;
		}
	}
	
	public Menu getNextMenu(String input, Menu currentMenu) {
		// If the possibleUserInputs is "*"
		if (currentMenu.possibleInputs.get(0).equals("*")) {
			// A special process must be run that is menu dependent.
			if (currentMenu instanceof LoginMenu) {
				// Build an instance of LoginAttempt from input and try to log in.
				// Display success or failure of attempt and from that decide which menu to run.
			} else if (currentMenu instanceof RegisterMenu) {
				// Build an instance of Account from input and try to register the account.
				// Display success or failure of attempt and decide what to display based on that.
			}
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
}
