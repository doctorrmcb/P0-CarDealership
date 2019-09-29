package com.revature.pojos.io.menu;

import com.revature.pojos.io.Menu;

public class CustomerMenu extends Menu {
	// View my cars
	// View new cars
	{	
		outputLines.add("\nThank you for logging in.\n");
		outputLines.add("Please enter a number to choose from the options below:");
		outputLines.add("1: View My Cars");
		outputLines.add("2: View New Cars");
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
		Menu viewMyCarsMenu = new ViewMyCarsMenu();
		Menu viewNewCarsMenu = new ViewNewCarsMenu();
		Menu initialMenu = new InitialMenu();
		Menu exit = new ExitMenu();
		possibleMenus.add(viewMyCarsMenu);
		possibleMenus.add(viewNewCarsMenu);
		possibleMenus.add(initialMenu);
		possibleMenus.add(exit);
	}
}
