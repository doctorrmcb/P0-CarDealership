package com.revature.service;

public class DisplayImpl implements Display {

	@Override
	public void initializeDisplay() {
		// TODO Auto-generated method stub
		System.out.println("Welcome to the Revature car dealership!");
		System.out.println("");
		System.out.println("Please enter a number to choose from the options below:");
		System.out.println("1: Login");
		System.out.println("2: Register");
		System.out.println("3: Exit");
	}

	@Override
	public void clearDisplay() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayLoginMenu() {
		// TODO Auto-generated method stub
		System.out.println("");
		System.out.println("Please enter your login info.");
		System.out.println("\tLogin info should be in the form \"[Username] [Password]\"");
		System.out.println("\t For example: guest password");
	}

	@Override
	public void displayUsernameLine() {
		// TODO Auto-generated method stub
		System.out.println("");
		System.out.println("Please enter your desired username.");
	}

	@Override
	public void displayPasswordLine() {
		// TODO Auto-generated method stub
		System.out.println("");
		System.out.println("Please enter your desired password.");
	}

	@Override
	public void displayPleaseRepeatCommand() {
		// TODO Auto-generated method stub
		System.out.println("");
		System.out.println("I don't understand that command. Please try again.");
	}

	
}
