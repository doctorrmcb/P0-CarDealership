package com.revature.main;

import java.util.Scanner;

import com.revature.pojos.io.Menu;
import com.revature.pojos.menu.InitialMenu;
import com.revature.service.DisplayImpl;

public class Driver {
	public static void main (String[] args) {
		DisplayImpl display = new DisplayImpl();
		Scanner scanner = new Scanner(System.in);
		Menu menu = new Menu(InitialMenu.inputArray, InitialMenu.displayArray);
		
		display.displayMenu(menu);
		getCommand(scanner, menu, display);
	}

	private static void getCommand(Scanner scanner, Menu menu, DisplayImpl display) {
		// TODO Auto-generated method stub
		String input = scanner.nextLine();
		if (menu.possibleInputs.contains(input)) {
			// Open the next menu.
		} else {
			System.out.println("\nI don't understand that command, please try again.");
			display.displayMenu(menu);
			getCommand(scanner, menu, display);
		}
	}
	
	/*public static void getCommand (Scanner scanner, String[] possibleInputs) {
		display.initializeDisplay();
		String input = scanner.nextLine();
		if (input.equals("1")) {
			display.displayLoginMenu();
		} else if (input.equals("2")) {
			display.displayUsernameLine();
			input = scanner.nextLine();
			String username = input;
			display.displayPasswordLine();
			input = scanner.nextLine();
			String password = input;
		} else if (input.equals("3")) {
			System.exit(0);
		} else {
			display.displayPleaseRepeatCommand();
			getCommand(scanner, display);
		}
	}*/
}
