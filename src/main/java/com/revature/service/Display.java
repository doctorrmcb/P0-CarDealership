package com.revature.service;

import com.revature.pojos.io.Menu;

public interface Display {
	
	public void displayMenu(Menu menu);
	
	public void initializeDisplay();
	public void displayLoginMenu();
	public void displayUsernameLine();
	public void displayPasswordLine();
	public void clearDisplay();
	public void displayPleaseRepeatCommand();
}
