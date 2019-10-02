package com.revature.pojos.io.menu;

import java.util.ArrayList;

import com.revature.dao.CarDAOSerialization;
import com.revature.pojos.io.Menu;

public class ViewMyCarsMenu extends Menu {
	
	{	
		CarDAOSerialization carDAO = new CarDAOSerialization();
		outputLines.add("\nViewing your cars.\n");
		outputLines.add("If you would like to view the remaining payments on a car please enter the VIN");
		outputLines.add("If you would like to go back to the previous screen, type \"Back\"");
		
		outputLines.add("\nYour cars:\n");
		outputLines.add("Vin\t\tOwner");
		outputLines.add("======================================================================");
		ArrayList<String> listCars = new ArrayList<>();
		listCars = carDAO.getAllCars();
		ArrayList<String> duplicateListCars = new ArrayList<>();
		for (String s : listCars) {
			if (s.contains(Menu.userName)) {
				duplicateListCars.add(s);
			}
		}
		for (String s : duplicateListCars) {
			outputLines.add(s);
		}// TODO Uses CarDAO.viewAllCars to get a list then loops through list and adds each element to outputLines.
	}
	
	{
		possibleInputs.add("*");
	}	
}
