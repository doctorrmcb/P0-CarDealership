package com.revature.pojos.io.menu;

import java.util.ArrayList;

import com.revature.dao.OfferDAOPostgres;
import com.revature.pojos.io.Menu;

public class ManageOffersMenu extends Menu {
	{	
		OfferDAOPostgres offerDAO = new OfferDAOPostgres();
		outputLines.add("\nManaging offers.\n");
		outputLines.add("Please reject or accept an offer by entering a command in the form [OfferID] [Decision]");
		outputLines.add("For example, \"1 Accept\"");
		outputLines.add("If you would like to go back to the previous screen, type \"Back\"");
		outputLines.add("\nAvailable offers:\n");
		outputLines.add("OfferID\t\t\tStatus");
		outputLines.add("======================================================================");
		ArrayList<String> listCars = new ArrayList<>();
		listCars = offerDAO.getAllOffers();
		for (String s : listCars) {
			outputLines.add(s + "\t" + offerDAO.readOffer(s).status);
		}
	}
	
	{
		possibleInputs.add("*");
	}
}
