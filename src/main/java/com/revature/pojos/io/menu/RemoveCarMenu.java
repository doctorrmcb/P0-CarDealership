package com.revature.pojos.io.menu;

import java.util.ArrayList;

import com.revature.dao.CarDAOPostgres;
import com.revature.pojos.io.Menu;

public class RemoveCarMenu extends Menu {
	
	{	
		CarDAOPostgres carDAO = new CarDAOPostgres();
		outputLines.add("\nPlease remove a car.\n");
		outputLines.add("Please enter a car you would like to remove in the form [VIN]");
		outputLines.add("If you would like to go back to the previous screen, type \"Back\"");
		outputLines.add("\nAvailable cars:\n");
		outputLines.add("Vin\t\tOwner");
		outputLines.add("======================================================================");
		ArrayList<String> listCars = new ArrayList<>();
		listCars = carDAO.getAllCars();
		for (String s : listCars) {
			outputLines.add(s);
		}
	}
	
	{
		possibleInputs.add("*");
	}
}
