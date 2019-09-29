package com.revature.pojos.io.menu;

import com.revature.pojos.io.Menu;

public class ManageCarsMenu extends Menu {
	
	{	
		outputLines.add("\nManaging Cars\n");
		outputLines.add("Please enter a number to choose from the options below:");
		outputLines.add("1: Add Car");
		outputLines.add("2: Remove Car");
		outputLines.add("3: View Car Payments");
		outputLines.add("4: Previous Menu");
	}
	
	{
		possibleInputs.add("1");
		possibleInputs.add("2");
		possibleInputs.add("3");
		possibleInputs.add("4");
	}
	
	{
		Menu addCarMenu = new AddCarMenu();
		Menu removeCarMenu = new RemoveCarMenu();
		Menu viewPaymentsMenu = new ViewPaymentsMenu();
		Menu employeeMenu = new EmployeeMenu();
		possibleMenus.add(addCarMenu);
		possibleMenus.add(removeCarMenu);
		possibleMenus.add(viewPaymentsMenu);
		possibleMenus.add(employeeMenu);
	}
	// Add Car
	// Remove Car
	// View payments
	// go back
}
