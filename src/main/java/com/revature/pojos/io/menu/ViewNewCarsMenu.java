package com.revature.pojos.io.menu;

import com.revature.pojos.io.Menu;

public class ViewNewCarsMenu extends Menu {
	{	
		outputLines.add("\nViewing new cars.\n");
		outputLines.add("If you would like to make an offer on a car please enter the VIN");
		outputLines.add("If you would like to go back to the previous screen, type \"Back\"");
		
		outputLines.add("\nAvailable new cars:\n");
		outputLines.add("!!! LIST OF CARS GOES HERE !!!");
		// TODO Uses CarDAO.viewAllCars to get a list then loops through list and adds each element to outputLines.
	}
	
	{
		possibleInputs.add("*");
	}
}
