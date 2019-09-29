package com.revature.main;

import java.util.Scanner;

import com.revature.pojos.io.Menu;
import com.revature.pojos.io.menu.ExitMenu;
import com.revature.pojos.io.menu.InitialMenu;
import com.revature.service.DisplayImpl;
import com.revature.service.CarSystemImpl;

public class Driver {
	public static void main (String[] args) {
		// Declaring system objects.
		DisplayImpl display = new DisplayImpl();
		Scanner scanner = new Scanner(System.in);
		CarSystemImpl carSystem = new CarSystemImpl();
		// Declaring initial menu object.
		InitialMenu initialMenu = new InitialMenu();
		// Display the initial menu.
		display.displayMenu(initialMenu);
		// Get the user's input.
		String input;
		input = carSystem.getCommand(scanner, initialMenu, display);
		Menu nextMenu = carSystem.getNextMenu(input, initialMenu);
		if (nextMenu instanceof ExitMenu) {
			System.out.println("\nThank you for visiting the Revature car dealership!\n");
			System.exit(0);
		} else {
			display.displayMenu(nextMenu);
			input = carSystem.getCommand(scanner, nextMenu, display);
			nextMenu = carSystem.getNextMenu(input, nextMenu);
			while(true) {
				display.displayMenu(nextMenu);
				input = carSystem.getCommand(scanner, nextMenu, display);
				nextMenu = carSystem.getNextMenu(input, nextMenu);
			}
		}
	}
}
