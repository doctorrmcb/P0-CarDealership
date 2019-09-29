package com.revature.pojos.io.menu;

import com.revature.pojos.io.Menu;

public class LoginMenu extends Menu {
	
	{
		outputLines.add("\nPlease enter your username and password in the form \"[Username] [Password]\".\n");
		outputLines.add("For example, \"Admin Password\"");
	}
	
	{
		possibleInputs.add("*");
	}
}
