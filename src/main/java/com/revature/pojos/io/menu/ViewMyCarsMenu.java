package com.revature.pojos.io.menu;

import com.revature.pojos.io.Menu;

public class ViewMyCarsMenu extends Menu {
	
	{	
		outputLines.add("\nViewing your cars.\n");
		outputLines.add("\nPlease add a car.\n");
		outputLines.add("If you would like to view the remaining payments on a car please enter the VIN");
		outputLines.add("If you would like to go back to the previous screen, type \"Back\"");
		
		outputLines.add("\nYour cars:\n");
		outputLines.add("!!! LIST OF CARS GOES HERE !!!");
		// TODO Uses CarDAO.viewAllCars to get a list then loops through list and adds each element to outputLines.
	}
	
	{
		possibleInputs.add("*");
	}	
}
