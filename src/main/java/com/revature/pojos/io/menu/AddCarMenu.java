package com.revature.pojos.io.menu;

import com.revature.pojos.io.Menu;

public class AddCarMenu extends Menu {
	{	
		outputLines.add("\nPlease add a car.\n");
		outputLines.add("Please enter a new car in the form [VIN] [Owner]");
		outputLines.add("If you would like to go back to the previous screen, type \"Back\"");
	}
	
	{
		possibleInputs.add("*");
	}	
}
