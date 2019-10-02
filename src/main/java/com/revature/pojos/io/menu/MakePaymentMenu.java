package com.revature.pojos.io.menu;

import com.revature.dao.PaymentDAOSerialization;
import com.revature.pojos.io.Menu;

public class MakePaymentMenu extends Menu {
	{
		PaymentDAOSerialization paymentDAO = new PaymentDAOSerialization();
		outputLines.add("\n Make a payment.\n");
		outputLines.add("Please enter a payment you would like to make in the form [Payment Amount] [VIN]");
		outputLines.add("If you would like to go back to the previous screen, type \"Back\"");
	}
	
	{
		possibleInputs.add("*");
	}
}
