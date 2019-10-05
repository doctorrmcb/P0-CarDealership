package com.revature.service;

import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;
import com.revature.dao.AccountDAOPostgres;
import com.revature.dao.CarDAOPostgres;
import com.revature.dao.OfferDAOPostgres;
import com.revature.dao.PaymentDAOPostgres;
import com.revature.pojos.Car;
import com.revature.pojos.authentication.Account;
import com.revature.pojos.authentication.LoginAttempt;
import com.revature.pojos.finance.Offer;
import com.revature.pojos.finance.Payment;
import com.revature.pojos.io.Menu;
import com.revature.pojos.io.menu.AddCarMenu;
import com.revature.pojos.io.menu.CustomerMenu;
import com.revature.pojos.io.menu.EmployeeMenu;
import com.revature.pojos.io.menu.LoginMenu;
import com.revature.pojos.io.menu.MakeAnOfferMenu;
import com.revature.pojos.io.menu.MakePaymentMenu;
import com.revature.pojos.io.menu.ManageCarsMenu;
import com.revature.pojos.io.menu.ManageOffersMenu;
import com.revature.pojos.io.menu.RegisterMenu;
import com.revature.pojos.io.menu.RemoveCarMenu;
import com.revature.pojos.io.menu.ViewMyCarsMenu;
import com.revature.pojos.io.menu.ViewMyRemainingPaymentsMenu;
import com.revature.pojos.io.menu.ViewNewCarsMenu;
import com.revature.pojos.io.menu.ViewPaymentsMenu;
import static com.revature.util.LoggerUtil.*;

public class CarSystemImpl implements CarSystem {
	
	private AccountDAOPostgres accountDAO = new AccountDAOPostgres();
	private CarDAOPostgres carDAO = new CarDAOPostgres();
	private OfferDAOPostgres offerDAO = new OfferDAOPostgres();
	private PaymentDAOPostgres paymentDAO = new PaymentDAOPostgres();
	
	public void setOfferDAO(OfferDAOPostgres offerDAO) {
		this.offerDAO = offerDAO;
	}

	public void setPaymentDAO(PaymentDAOPostgres paymentDAO) {
		this.paymentDAO = paymentDAO;
	}

	public void setCarDAO(CarDAOPostgres carDAO) {
		this.carDAO = carDAO;
	}

	public void setAccountDAO(AccountDAOPostgres accountDAO) {
		this.accountDAO = accountDAO;
	}
	
	public String getCommand(Scanner scanner, Menu menu, DisplayImpl display) {
		String input = scanner.nextLine();
		if (menu.possibleInputs.contains(input)) {
			trace("Got user input, user input was contained within possible inputs. User input: " + input);
			return input;
		} else if (menu.possibleInputs.get(0).equals("*")) {
			// A special process must be run that is menu dependent.
			trace("Got user input, user input could be any value. User input: " + input);
			return input;
		} else {
			debug("Got user input, user input was invalid. User input: " + input + " Menu: " + menu);
			System.out.println("\nI don't understand that command, please try again.");
			display.displayMenu(menu);
			input = getCommand(scanner, menu, display);
			return input;
		}
	}
	
	public Menu getNextMenu(String input, Menu currentMenu) {
		// Clean up the user input.
		input = input.trim();
		if (currentMenu.possibleInputs.isEmpty()) {
			debug("Possible inputs was empty. Menu: " + currentMenu);
			return null;
		}
		// If the possibleUserInputs is "*"
		if (currentMenu.possibleInputs.get(0).equals("*")) {
			trace("Calling special menu getter. Menu: " + currentMenu);
			return getSpecialMenu(input, currentMenu);
		} else {
			for (int i = 0; i < currentMenu.possibleInputs.size(); i++) {
				if (input.equals(currentMenu.possibleInputs.get(i))) {
					trace("Returning next menu. Current Menu: " + currentMenu + " Next Menu: " + currentMenu.possibleMenus.get(i));
					return currentMenu.possibleMenus.get(i);
				}
			}
		}
		// The program should never get to this point, because the user's input was validated during the getCommand function.
		debug("Program should never have reached this point. Current Menu: " + currentMenu + " Input: " + input);
		return null;
	}
	
	public String[] tryLogin(LoginAttempt loginAttempt) {
		trace("Reading the account using loginAttempt. LoginAttempt: " + loginAttempt);
		Account account = accountDAO.readAccount(loginAttempt.username);
		if (account != null) {
			String[] result = {"true", account.getAccountStatus()};
			trace("Login succeeded. Account Status: " + account.getAccountStatus());
			return result;
		} else {
			String[] result = {"false", null};
			debug("Login failed. LoginAttempt: " + loginAttempt);
			return result;
		}
	}
	
	public boolean tryRegister(Account account) {
		trace("Trying to register user. Account: " + account);
		boolean result = accountDAO.createAccount(account);
		return result;
	}
	
	public Menu getSpecialMenu(String input, Menu currentMenu) {
		// A special process must be run that is menu dependent.
		// Run the special process that returns the next menu that should be displayed.
		if (currentMenu instanceof LoginMenu) {
			trace("Getting next menu after LoginMenu.");
			return getNextMenuLogin(input);
		} else if (currentMenu instanceof RegisterMenu) {
			trace("Getting next menu after RegisterMenu.");
			return getNextMenuRegister(input);
		} else if (currentMenu instanceof AddCarMenu) {
			trace("Getting next menu after AddCarMenu.");
			return getNextMenuAddCar(input);
		} else if (currentMenu instanceof MakeAnOfferMenu) {
			trace("Getting next menu after MakeOfferMenu.");
			return getNextMenuMakeOffer(input);
		} else if (currentMenu instanceof ManageOffersMenu) {
			trace("Getting next menu after ManageOfferMenu.");
			return getNextMenuManageOffers(input);
		} else if (currentMenu instanceof RemoveCarMenu) {
			trace("Getting next menu after RemoveCarMenu.");
			return getNextMenuRemoveCar(input);
		} else if (currentMenu instanceof ViewMyCarsMenu) {
			trace("Getting next menu after MyCarMenu.");
			return getNextMenuMyCars(input);
		} else if (currentMenu instanceof ViewNewCarsMenu) {
			trace("Getting next menu after NewCarsMenu.");
			return getNextMenuNewCars(input);
		} else if (currentMenu instanceof ViewPaymentsMenu) {
			trace("Getting next menu after ViewAllPaymentsMenu.");
			return getNextMenuAllPayments(input);
		} else if (currentMenu instanceof MakePaymentMenu) {
			trace("Getting next menu after MakePaymentMenu.");
			return getNextMenuMakePayment(input);
		} else {
			warn("GetSpecialInstance failed. Current Menu: " + currentMenu + " Input: " + input);
			return null;
		}
	}
	
	public Menu getNextMenuRegister(String input) {
		// Build an instance of Account from input and try to register the account.
		String[] inputArray = input.split(" ");
		String username = inputArray[0];
		String password = inputArray[1];
		String accountStatus = inputArray[2];
		Account account = new Account(username, password, accountStatus);
		boolean result = tryRegister(account);
		// Display success or failure of attempt and decide what to display based on that.
		if (result == false) {
			System.out.println("Registration failed, please try again.");
			debug("The registration failed. Input: " + input);
			return new RegisterMenu();
		} else {
			System.out.println("Registration success.");
			if (accountStatus.equals("Employee")) {
				trace("Registration succeeded. Employee added. Input: " + input);
				return new EmployeeMenu();
			} else {
				trace("Registration succeeded. Customer added. Input: " + input);
				return new CustomerMenu();
			}
		}
	}
	
	public Menu getNextMenuLogin(String input) {
		// Build an instance of LoginAttempt from input and try to log in.
		try {
			String[] inputArray = input.split(" ");
			String username = inputArray[0];
			String password = inputArray[1];
			LoginAttempt login = new LoginAttempt(username, password);
			String[] result = tryLogin(login);
			Menu.userName = username;
			// Display success or failure of attempt and from that decide which menu to run.
			if(result[0].equals("true") && result[1].equals("Employee")) {
				trace("Login succeeded. Logging in as Employee. Input: " + input);
				return new EmployeeMenu();
			} else if (result[0].equals("true") && result[1].equals("Customer")) {
				trace("Login succeeded. Logging in as Customer. Input: " + input);
				return new CustomerMenu();
			} else {
				System.out.println("Login attempt failed, please try again.");
				debug("Login failed during tryLogin. Input: " + input);
				return new LoginMenu();
			}
		} catch (Exception e) {
			System.out.println("Login attempt failed, please try again.");
			debug("Login failed during getNextMenuLogin. Input: " + input);
			return new LoginMenu();
		}
	}
	
	public Menu getNextMenuAddCar(String input) {
		if ("Back".contentEquals(input)) {
			return new ManageCarsMenu();
		}
		String[] inputArray = input.split(" ");
		String vin = inputArray[0];
		String owner = inputArray[1];
		Car car = new Car(vin, owner);
		boolean daoBool = carDAO.createCar(car);
		if (daoBool == true) {
			System.out.println("\nSuccessfully added car.");
			trace("Succeeded adding car. Input: " + input);
			return new ManageCarsMenu();
		} else {
			System.out.println("\nFailed to add car, please try again.");
			debug("Failed to add car. Failed in the createCar function. Input: " + input);
			return new AddCarMenu();
		}
		
	}
	
	public Menu getNextMenuMakeOffer(String input) {
		// Should return view new cars after making an offer object.
		if (input.contentEquals("Back")) {
			trace("Going back to ViewNewCarsMenu. Input: " + input);
			return new ViewNewCarsMenu();
		}
		
		try {
			String username = Menu.userName;
			String vin = Menu.prevInput;
			String[] arrInput = input.split(" ");
			String price = arrInput[0];
			Double dblPrice = Double.parseDouble(price);
			String duration = arrInput[1];
			int intDuration = Integer.parseInt(duration);
			Offer offer = new Offer(username, dblPrice, vin, intDuration);
			boolean result = offerDAO.createOffer(offer);
			if (result) {
				System.out.println("Succeeded in making offer.");
				trace("Succeeded in making an offer. Input: " + input);
				return new ViewNewCarsMenu();
			} else {
				System.out.println("Failed to make offer, please try again.");
				debug("Failed to make offer. Failed in the createOffer function. Input: " + input);
				return new MakeAnOfferMenu();
			}
		} catch (Exception e) {
			System.out.println("Failed to make offer, please try again.");
			debug("Failed to make offer. Failed in the getNextMenuMakeOffer function. Input: " + input);
			return new MakeAnOfferMenu();
		}
	}
	
	public Menu getNextMenuManageOffers(String input) {
		// Should do some stuff before returning manage offers or employee menu.
		if (input.contentEquals("Back")) {
			trace("Going back to EmployeeMenu. Input: " + input);
			return new EmployeeMenu();
		} else {
			try {
				String[] arrInput = input.split(" ");
				Offer outputOffer = offerDAO.readOffer(arrInput[0]);
				if (arrInput[1].contentEquals("Accept")) {
					outputOffer.setStatus("Accepted");
					trace("outputOffer set to \"Accepted\" successfully. Decision: " + arrInput[1]);
					boolean result2 = rejectAllOtherOffers(outputOffer.vin, outputOffer.username);
					if (result2) {
						System.out.println("Rejected all other offers.");
						trace("Rejected offers successfully. Vin: " + outputOffer.vin + " Result2: " + result2);
						boolean result = offerDAO.updateOffer(outputOffer.offerId, outputOffer);
						if (result) {
							System.out.println("Offer update succeeded.");
							Car car = carDAO.readCar(outputOffer.vin);
							car.owner = outputOffer.username;
							trace("Offer updated successfully. Offer: " + outputOffer.toString());
							boolean result3 = carDAO.updateCar(car.vin, car);
							if (result3) {
								System.out.println("Car update succeeded.");
								trace("Car updated successfully. Vin: " + car.vin + " Car: " + car);
								return new EmployeeMenu();
							} else {
								System.out.println("Failed to update offer, please try again");
								debug("Failed to update offer. Failed in updateCar. Result3: " + result3);
								return new ManageOffersMenu();
							}
						} else {
							System.out.println("Failed to update offer, please try again");
							debug("Failed to update offer. Failed in createOffer. Result: " + result);
							return new ManageOffersMenu();
						}
					} else {
						System.out.println("Failed to reject other offers. Contact the administrator.");
						debug("Failed to update offer. Failed in rejectAllOtherOffers. Result2: " + result2);
						return new ManageOffersMenu();
					}
				} else if (arrInput[1].contentEquals("Reject")) {
					outputOffer.setStatus("Rejected");
					offerDAO.updateOffer(outputOffer.offerId, outputOffer);
					System.out.println("Offer update succeeded.");
					trace("Offer rejected successfully. Input[1]: " + arrInput[1]);
					return new EmployeeMenu();
				} else {
					System.out.println("Failed to update offer, please try again.");
					debug("Failed to reject offer. Failed in input. Input: " + input);
					return new ManageOffersMenu();
				}
			} catch (Exception e) {
				System.out.println("Failed to update offer, please try again");
				debug("Failed to update offer. Failed in try/catch. Input: " + input);
				return new ManageOffersMenu();
			}
		}
	}
	
	public Menu getNextMenuRemoveCar(String input) {
		String vin = input;
		if (input.contentEquals("Back")) {
			trace("Going back to ManageCarsMenu. Input: " + input);
			return new ManageCarsMenu();
		}
		boolean daoBool = carDAO.deleteCar(vin);
		if (daoBool == true) {
			System.out.println("Successfully deleted car.");
			trace("Successfully deleted car. Vin: " + vin);
			return new ManageCarsMenu();
		} else {
			System.out.println("Failed to delete car, please try again.");
			debug("Failed to delete the car. Failed in deleteCar. Vin: " + vin);
			return new RemoveCarMenu();
		}
	}
	
	public Menu getNextMenuMyCars(String input) {
		// View remaining payments is forward or customer menu is back. Entering the vin takes to specific car.
		if (input.contentEquals("Back")) {
			trace("Going back to CustomerMenu. Input: " + input) ;
			return new CustomerMenu();
		} else {
			Car car = carDAO.readCar(input);
			if (car != null) {
				// TODO: Make this a real menu.
				trace("Building menu on the fly.");
				Menu menu = new Menu();
				menu.outputLines.add("\nViewing your remaining payments.\n");
				menu.outputLines.add("If you would like to go back to the previous screen, type \"Back\"");
				menu.outputLines.add("\nYour car:");
				StringBuilder sb = new StringBuilder();
				sb.append(car.vin + " " + car.owner);
				menu.outputLines.add(sb.toString());
				sb.delete(0, sb.length());
				sb.append(Menu.userName);
				sb.append("_");
				sb.append(input);
				String offerId = sb.toString();
				menu.outputLines.add("\nRemaining payments:");
				menu.outputLines.add("======================================================================");
				Offer offer = offerDAO.readOffer(offerId);
				NumberFormat formatter = NumberFormat.getCurrencyInstance();
				String formattedOffer = formatter.format(offer.price);
				menu.outputLines.add("Original Price:\t\t\t\t" + formattedOffer);
				Double monthlyPayment = offer.price / offer.durationMonths;
				formattedOffer = formatter.format(monthlyPayment);
				menu.outputLines.add("Original Number of Payments:\t\t" + Integer.toString(offer.durationMonths));
				menu.outputLines.add("Monthly Payment:\t\t\t" + formattedOffer);
				// TODO Put total remaining;
				// Total remaining is offer.amount - (sum of get all payments on vin)
				// Total payments left is total remaining / monthly payment
				trace("Successfully built menu on the fly after reading the car. Menu: " + menu);
				return menu;
			} else {
				System.out.println("Incorrect vin, please try again.");
				debug("Failed to readCar. Car: " + car);
				return new ViewMyCarsMenu();
			}
		}
	}
	
	public Menu getNextMenuNewCars(String input) {
		// Make an offer menu is forward customer menu is back.
		if (input.contentEquals("Back")) {
			trace("Going back to CustomerMenu. Input: " + input);
			return new CustomerMenu();
		} else {
			Car car = carDAO.readCar(input);
			if (car != null) {
				Menu.prevInput = input;
				trace("Moving on to making an offer. Input: " + input);
				return new MakeAnOfferMenu();
			} else {
				System.out.println("Incorrect vin, please try again.");
				debug("Fauled to readCar. Input: " + input);
				return new ViewNewCarsMenu();
			}
		}
	}
	
	public Menu getNextMenuAllPayments(String input) {
		// Go back to manage cars menu.
		// User input is in the form "[VIN]"
		// Read all the payments where the title contains vin.
		if (input.contentEquals("Back")) {
			trace("Going back to ManageCarsMenu. Input: " + input);
			return new ManageCarsMenu();
		} else {
			trace("Building menu on the fly.");
			ArrayList<String> paymentArray = new ArrayList<>();
			paymentArray = paymentDAO.getAllPayments();
			// TODO: Make this a real menu.
			Menu menu = new Menu();
			menu.outputLines.add("\nViewing payments.\n");
			menu.outputLines.add("If you would like to go back to the previous screen, type \"Back\"");
			menu.outputLines.add("\nPayments on " + input + ":");
			menu.outputLines.add("\nPayment Date\tAmount Paid\tUser");
			menu.outputLines.add("======================================================================");
			menu.possibleInputs.add("*");
			for (String s: paymentArray) {
				if (!s.contains(input)) {
					menu.outputLines.add(s);				
				}
			}
			trace("Successfully built menu on the fly.");
			return menu;
		}
	}
	
	public Menu getNextMenuMakePayment(String input) {
		// Go back to customer menu.
		// User input is a string in the form "[Payment Amount] [Vin]"
		if (input.contentEquals("Back")) {
			trace("Returning to CustomerMenu. Input: " + input);
			return new CustomerMenu();
		}
		try {
			String[] arrInput = input.split(" ");
			Double amount = Double.parseDouble(arrInput[0]);
			String owner = Menu.userName;
			String vin = arrInput[1];
			Payment payment = new Payment(amount, owner, vin);
			boolean result = paymentDAO.createPayment(payment);
			if (result) {
				System.out.println("Succeeded in making a payment.");
				trace("Succeeded in making payment. Payment: " + payment);
				return new CustomerMenu();
			} else {
				System.out.println("Failed to make a payment, please try again.");
				debug("Failed to make payment. Failed during createPayment. Input: " + input);
				return new MakePaymentMenu();
			}
		} catch (Exception e) {
			System.out.println("Failed to make a payment, please try again.");
			debug("Failed to make payment. Failed in try/catch. Input: " + input);
			return new MakePaymentMenu();
		}
	}
	
	public boolean rejectAllOtherOffers(String vin, String username) {
		try {
			boolean result = offerDAO.rejectOffers(vin, username);
			trace("Succeeded in deleting other offers. Vin: " + vin + " Username: " + username);
			return result;
		} catch (Exception e) {
			debug("Failed to delete other offers. Failed in getAllOffers. Vin: " + vin + " Username: " + username);
			return false;
		}
	}
}
