package com.revature.pojos.io.menu;

import com.revature.pojos.io.Menu;

public class MakeAnOfferMenu extends Menu {
	{	
		outputLines.add("\nMaking an offer.\n");
		outputLines.add("If you would like to make an offer on a car please enter the offer in the format [Price] [Duration to pay in months]");
		outputLines.add("If you would like to go back to the previous screen, type \"Back\"");
		
		// TODO Uses CarDAO.viewAllCars to get a list then loops through list and adds each element to outputLines.
	}
	
	{
		possibleInputs.add("*");
	}
}
