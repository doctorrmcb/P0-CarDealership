package com.revature.pojos.io;

import java.util.ArrayList;
import java.util.Arrays;

import com.revature.pojos.io.menu.AddCarMenu;
import com.revature.pojos.io.menu.EmployeeMenu;
import com.revature.pojos.io.menu.ExitMenu;
import com.revature.pojos.io.menu.InitialMenu;
import com.revature.pojos.io.menu.LoginMenu;
import com.revature.pojos.io.menu.ManageCarsMenu;
import com.revature.pojos.io.menu.ManageOffersMenu;
import com.revature.pojos.io.menu.RegisterMenu;
import com.revature.pojos.io.menu.RemoveCarMenu;
import com.revature.pojos.io.menu.ViewMyCarsMenu;
import com.revature.pojos.io.menu.ViewNewCarsMenu;
import com.revature.pojos.io.menu.ViewPaymentsMenu;

public class Menu {
	public ArrayList<String> possibleInputs = new ArrayList<String>();
	public ArrayList<String> outputLines = new ArrayList<String>();
	public ArrayList<Menu> possibleMenus = new ArrayList<Menu>();
	public ArrayList<String> prevInfo = new ArrayList<String>();
	
	public Menu() {
		super();
	}
	public Menu(String[] possibleInputsIn, String[] outputLinesIn, Menu[] possibleMenusIn) {
		possibleInputs = new ArrayList<String>(Arrays.asList(possibleInputsIn));
		outputLines = new ArrayList<String>(Arrays.asList(outputLinesIn));
		possibleMenus = new ArrayList<Menu>(Arrays.asList(possibleMenusIn));
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((outputLines == null) ? 0 : outputLines.hashCode());
		result = prime * result + ((possibleInputs == null) ? 0 : possibleInputs.hashCode());
		result = prime * result + ((possibleMenus == null) ? 0 : possibleMenus.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Menu other = (Menu) obj;
		if (outputLines == null) {
			if (other.outputLines != null)
				return false;
		} else if (!outputLines.equals(other.outputLines))
			return false;
		if (possibleInputs == null) {
			if (other.possibleInputs != null)
				return false;
		} else if (!possibleInputs.equals(other.possibleInputs))
			return false;
		if (possibleMenus == null) {
			if (other.possibleMenus != null)
				return false;
		} else if (!possibleMenus.equals(other.possibleMenus))
			return false;
		return true;
	}
	public ArrayList<String> getPossibleInputs() {
		return possibleInputs;
	}
	public void setPossibleInputs(ArrayList<String> possibleInputs) {
		this.possibleInputs = possibleInputs;
	}
	public ArrayList<String> getOutputLines() {
		return outputLines;
	}
	public void setOutputLines(ArrayList<String> outputLines) {
		this.outputLines = outputLines;
	}
	public ArrayList<Menu> getPossibleMenus() {
		return possibleMenus;
	}
	public void setPossibleMenus(ArrayList<Menu> possibleMenus) {
		this.possibleMenus = possibleMenus;
	}
}

