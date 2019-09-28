package com.revature.main;

import java.util.Scanner;

import com.revature.service.DisplayImpl;

public class Driver {
	public static void main (String[] args) {
		DisplayImpl display = new DisplayImpl();
		display.initializeDisplay();
		Scanner scanner = new Scanner(System.in);
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
		}
	}
	
	
}
