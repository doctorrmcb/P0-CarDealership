package com.revature.pojos.io.menu;

import com.revature.pojos.io.Menu;

public class ViewPaymentsMenu extends Menu {
	{	
		outputLines.add("\nPlease enter a car you would like to view the payments on in the form [VIN]");
		outputLines.add("If you would like to go back to the previous screen, type \"Back\"");
	}
	
	{
		possibleInputs.add("*");
	}
}
