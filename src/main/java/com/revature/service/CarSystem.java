package com.revature.service;

import java.util.Scanner;

import com.revature.pojos.io.Menu;

public interface CarSystem {
	public String getCommand(Scanner scanner, Menu menu, DisplayImpl display);
	public Menu getNextMenu(String input, Menu currentMenu);
}
