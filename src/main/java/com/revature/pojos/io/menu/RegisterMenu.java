package com.revature.pojos.io.menu;

import com.revature.pojos.io.Menu;

public class RegisterMenu extends Menu {
	{
		outputLines.add("\nPlease enter your desired username, password, and either \"Employee\" or \"Customer\" in the form \"[Username] [Password] [Account Status]\".\n");
		outputLines.add("For example an employee could enter, \"Admin Password Employee\"");
		outputLines.add("While a customer could enter, \"AwesomeGuy 12345 Customer\"");
	}
	
	{
		possibleInputs.add("*");
	}
}
