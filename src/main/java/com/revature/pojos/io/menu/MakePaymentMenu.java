package com.revature.pojos.io.menu;

import java.util.ArrayList;

import com.revature.dao.CarDAOSerialization;
import com.revature.pojos.io.Menu;

public class MakePaymentMenu extends Menu {
	{
		outputLines.add("\n Make a payment.\n");
		outputLines.add("Please enter a payment you would like to make in the form [Payment Amount] [VIN]");
		outputLines.add("If you would like to go back to the previous screen, type \"Back\"");
		outputLines.add("\nYour cars:\n");
		outputLines.add("Vin\t\tOwner");
		outputLines.add("======================================================================");
		ArrayList<String> listCars = new ArrayList<>();
		CarDAOSerialization carDAO = new CarDAOSerialization();
		listCars = carDAO.getAllCars();
		ArrayList<String> duplicateListCars = new ArrayList<>();
		for (String s : listCars) {
			if (s.contains(Menu.userName)) {
				duplicateListCars.add(s);
			}
		}
		for (String s : duplicateListCars) {
			outputLines.add(s);
		}
	}
	
	{
		possibleInputs.add("*");
	}
}
