package com.revature.main;

import java.util.Scanner;

import com.revature.service.DisplayImpl;

public class Driver {
	public static void main (String[] args) {
		DisplayImpl display = new DisplayImpl();
		Scanner scanner = new Scanner(System.in);
		getCommand(scanner, display);
	}
	
	public static void getCommand (Scanner scanner, DisplayImpl display) {
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
	}
}
