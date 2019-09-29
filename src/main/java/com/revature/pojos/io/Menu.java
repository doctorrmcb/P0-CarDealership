package com.revature.pojos.io;

import java.util.ArrayList;
import java.util.Arrays;

public class Menu {
	public ArrayList<String> possibleInputs = new ArrayList<String>();
	public ArrayList<String> outputLines = new ArrayList<String>();
	public ArrayList<Menu> possibleMenus = new ArrayList<Menu>();
	public Menu() {
		super();
	}
	public Menu(String[] possibleInputsIn, String[] outputLinesIn) {
		possibleInputs = new ArrayList<String>(Arrays.asList(possibleInputsIn));
		outputLines = new ArrayList<String>(Arrays.asList(outputLinesIn));
	}
}

