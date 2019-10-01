package com.revature.pojos.io.menu;

import java.util.ArrayList;

import com.revature.dao.CarDAOSerialization;
import com.revature.pojos.io.Menu;

public class ViewNewCarsMenu extends Menu {
	{	
		CarDAOSerialization carDAO = new CarDAOSerialization();
		outputLines.add("\nViewing new cars.\n");
		outputLines.add("If you would like to make an offer on a car please enter the VIN");
		outputLines.add("If you would like to go back to the previous screen, type \"Back\"");
		
		outputLines.add("\nAvailable new cars:\n");
		outputLines.add("Vin\t\tOwner");
		outputLines.add("======================================================================");
		ArrayList<String> listCars = new ArrayList<>();
		listCars = carDAO.getAllCars();
		ArrayList<String> duplicateListCars = new ArrayList<>();
		for (String s : listCars) {
			if (s.contains("Revature")) {
				duplicateListCars.add(s);
			}
		}
		for (String s : duplicateListCars) {
			outputLines.add(s);
		}
		// TODO Uses CarDAO.viewAllCars to get a list then loops through list and adds each element to outputLines.
	}
	
	{
		possibleInputs.add("*");
	}
}
