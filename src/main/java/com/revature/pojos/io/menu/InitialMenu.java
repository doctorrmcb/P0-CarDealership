package com.revature.pojos.io.menu;

import com.revature.pojos.io.Menu;

public class InitialMenu extends Menu {
	{
		
		outputLines.add("\nWelcome to the Revature car dealership!\n");
		outputLines.add("Please enter a number to choose from the options below:");
		outputLines.add("1: Login");
		outputLines.add("2: Register");
		outputLines.add("3: Exit");
	}
	
	{
		possibleInputs.add("1");
		possibleInputs.add("2");
		possibleInputs.add("3");
	}	
	
	{
		Menu loginMenu = new LoginMenu();
		Menu registerMenu = new RegisterMenu();
		Menu exit = new ExitMenu();
		possibleMenus.add(loginMenu);
		possibleMenus.add(registerMenu);
		possibleMenus.add(exit);
	}
}
