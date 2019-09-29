package com.revature.pojos.io.menu;

import com.revature.pojos.io.Menu;

public class ManageOffersMenu extends Menu {
	{	
		outputLines.add("\nManaging offers.\n");
		outputLines.add("Please reject or accept an offer by entering a command in the form [OfferID] [Decision]");
		outputLines.add("For example, \"1 Accept\"");
		outputLines.add("If you would like to go back to the previous screen, type \"Back\"");
	}
	
	{
		possibleInputs.add("*");
	}
}
