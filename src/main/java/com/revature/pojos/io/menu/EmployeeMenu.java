package com.revature.pojos.io.menu;

import com.revature.pojos.io.Menu;

public class EmployeeMenu extends Menu {
	
	{	
		outputLines.add("\nThank you for logging in.\n");
		outputLines.add("Please enter a number to choose from the options below:");
		outputLines.add("1: Manage Cars");
		outputLines.add("2: Manage Offers");
		outputLines.add("3: Logout");
		outputLines.add("4: Exit");
	}
	
	{
		possibleInputs.add("1");
		possibleInputs.add("2");
		possibleInputs.add("3");
		possibleInputs.add("4");
	}
	
	{
		Menu manageCarsMenu = new ManageCarsMenu();
		Menu manageOffersMenu = new ManageOffersMenu();
		Menu initialMenu = new InitialMenu();
		Menu exit = new ExitMenu();
		possibleMenus.add(manageCarsMenu);
		possibleMenus.add(manageOffersMenu);
		possibleMenus.add(initialMenu);
		possibleMenus.add(exit);
	}
	// Manage cars
	// Manage offers
	// Exit
	// Logout
}
