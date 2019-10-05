package com.revature.main;

import java.util.Scanner;

import com.revature.pojos.io.Menu;
import com.revature.pojos.io.menu.ExitMenu;
import com.revature.pojos.io.menu.InitialMenu;
import com.revature.service.DisplayImpl;
import com.revature.service.CarSystemImpl;
import static com.revature.util.LoggerUtil.*;

public class Driver {
	//TODO: 3. A custom stored procedure is called to perform some portion of the functionality.
	//TODO: 7. 100% test coverage using JUnit Java testing framework.

	//TODO: * As a customer, I can view my remaining payments for a car.
	
	public static void main (String[] args) {
		// Declaring system objects.
		trace("Performing initial setup for main method.");
		DisplayImpl display = new DisplayImpl();
		Scanner scanner = new Scanner(System.in);
		CarSystemImpl carSystem = new CarSystemImpl();
		// Declaring initial menu object.
		InitialMenu initialMenu = new InitialMenu();
		// Display the initial menu.
		trace("Displaying initialMenu.");
		display.displayMenu(initialMenu);
		// Get the user's input.
		String input;
		trace("Getting initial input.");
		input = carSystem.getCommand(scanner, initialMenu, display);
		trace("Getting 2nd menu.");
		Menu nextMenu = carSystem.getNextMenu(input, initialMenu);
		if (nextMenu instanceof ExitMenu) {
			System.out.println("\nThank you for visiting the Revature car dealership!\n");
			trace("Exiting program.");
			System.exit(0);
		} else {
			while(true) {
				if (nextMenu instanceof ExitMenu) {
					System.out.println("\nThank you for visiting the Revature car dealership!\n");
					trace("Exiting program.");
					System.exit(0);
				}
				display.displayMenu(nextMenu);
				input = carSystem.getCommand(scanner, nextMenu, display);
				nextMenu = carSystem.getNextMenu(input, nextMenu);
				
			}
		}
	}
}
