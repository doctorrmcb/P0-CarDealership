package com.revature.pojos.io.menu;

import com.revature.pojos.io.Menu;

public class ViewMyRemainingPaymentsMenu extends Menu {
	{	
		outputLines.add("\nViewing your remaining payments.\n");
		outputLines.add("If you would like to go back to the previous screen, type \"Back\"");
		
		outputLines.add("\nYour car:\n");
		outputLines.add("!!! CAR GOES HERE !!!");
		// TODO Uses CarDAO.viewAllCars to get a list then loops through list and adds each element to outputLines.
		
		outputLines.add("\nRemaining payments:\n");
		outputLines.add("!!! TOTAL REMAINING GOES HERE !!!");
		outputLines.add("!!! MONTHLY PAYMENT GOES HERE !!!");
	}
	
	{
		possibleInputs.add("Back");
	}
}
