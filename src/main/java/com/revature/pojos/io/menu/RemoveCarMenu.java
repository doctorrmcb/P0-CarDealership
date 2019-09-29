package com.revature.pojos.io.menu;

import com.revature.pojos.io.Menu;

public class RemoveCarMenu extends Menu {
	{	
		outputLines.add("\nPlease remove a car.\n");
		outputLines.add("Please enter a new car in the form [VIN]");
		outputLines.add("If you would like to go back to the previous screen, type \"Back\"");
		outputLines.add("\nAvailable cars:\n");
		outputLines.add("!!! LIST OF CARS GOES HERE !!!");
		// TODO Uses CarDAO.viewAllCars to get a list then loops through list and adds each element to outputLines.
	
	}
	
	{
		possibleInputs.add("*");
	}
}
