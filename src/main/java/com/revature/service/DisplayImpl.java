package com.revature.service;

import com.revature.pojos.io.Menu;
import static com.revature.util.LoggerUtil.*;

public class DisplayImpl implements Display {

	@Override
	public void displayMenu(Menu menu) {
		trace("Displaying menu " + menu);
		for (String s:menu.outputLines) {
			System.out.println(s);
		}
		
	}

	
}
