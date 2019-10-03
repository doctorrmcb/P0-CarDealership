package com.revature.service;

import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;
import com.revature.dao.AccountDAOSerialization;
import com.revature.dao.CarDAOSerialization;
import com.revature.dao.OfferDAOSerialization;
import com.revature.dao.PaymentDAOSerialization;
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

public class CarSystemImpl implements CarSystem {
	
	private AccountDAOSerialization accountDAO = new AccountDAOSerialization();
	private CarDAOSerialization carDAO = new CarDAOSerialization();
	private OfferDAOSerialization offerDAO = new OfferDAOSerialization();
	private PaymentDAOSerialization paymentDAO = new PaymentDAOSerialization();
	
	public void setOfferDAO(OfferDAOSerialization offerDAO) {
		this.offerDAO = offerDAO;
	}

	public void setPaymentDAO(PaymentDAOSerialization paymentDAO) {
		this.paymentDAO = paymentDAO;
	}

	public void setCarDAO(CarDAOSerialization carDAO) {
		this.carDAO = carDAO;
	}

	public void setAccountDAO(AccountDAOSerialization accountDAO) {
		this.accountDAO = accountDAO;
	}
	
	public String getCommand(Scanner scanner, Menu menu, DisplayImpl display) {
		String input = scanner.nextLine();
		if (menu.possibleInputs.contains(input)) {
			return input;
		} else if (menu.possibleInputs.get(0).equals("*")) {
			// A special process must be run that is menu dependent.
			return input;
		} else {
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
			return null;
		}
		// If the possibleUserInputs is "*"
		if (currentMenu.possibleInputs.get(0).equals("*")) {
			return getSpecialMenu(input, currentMenu);
		} else {
			for (int i = 0; i < currentMenu.possibleInputs.size(); i++) {
				if (input.equals(currentMenu.possibleInputs.get(i))) {
					return currentMenu.possibleMenus.get(i);
				}
			}
		}
		// The program should never get to this point, because the user's input was validated during the getCommand function.
		return null;
	}
	
	public String[] tryLogin(LoginAttempt loginAttempt) {
		Account account = accountDAO.readAccount(loginAttempt.username);
		if (account != null) {
			String[] result = {"true", account.getAccountStatus()};
			return result;
		} else {
			String[] result = {"false", null};
			return result;
		}
	}
	
	public boolean tryRegister(Account account) {
		//AccountDAOSerialization accountDAO = new AccountDAOSerialization();
		boolean result = accountDAO.createAccount(account);
		return result;
	}
	
	public Menu getSpecialMenu(String input, Menu currentMenu) {
		// A special process must be run that is menu dependent.
		// Run the special process that returns the next menu that should be displayed.
		if (currentMenu instanceof LoginMenu) {
			return getNextMenuLogin(input);
		} else if (currentMenu instanceof RegisterMenu) {
			return getNextMenuRegister(input);
		} else if (currentMenu instanceof AddCarMenu) {
			return getNextMenuAddCar(input);
		} else if (currentMenu instanceof MakeAnOfferMenu) {
			return getNextMenuMakeOffer(input);
		} else if (currentMenu instanceof ManageOffersMenu) {
			return getNextMenuManageOffers(input);
		} else if (currentMenu instanceof RemoveCarMenu) {
			return getNextMenuRemoveCar(input);
		} else if (currentMenu instanceof ViewMyCarsMenu) {
			return getNextMenuMyCars(input);
		} else if (currentMenu instanceof ViewNewCarsMenu) {
			return getNextMenuNewCars(input);
		} else if (currentMenu instanceof ViewPaymentsMenu) {
			return getNextMenuAllPayments(input);
		} else if (currentMenu instanceof MakePaymentMenu) {
			return getNextMenuMakePayment(input);
		} else {
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
			return new RegisterMenu();
		} else {
			System.out.println("Registration success.");
			if (accountStatus.equals("Employee")) {
				return new EmployeeMenu();
			} else {
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
				return new EmployeeMenu();
			} else if (result[0].equals("true") && result[1].equals("Customer")) {
				return new CustomerMenu();
			} else {
				System.out.println("Login attempt failed, please try again.");
				return new LoginMenu();
			}
		} catch (Exception e) {
			System.out.println("Login attempt failed, please try again.");
			return new LoginMenu();
		}
	}
	
	public Menu getNextMenuAddCar(String input) {
		String[] inputArray = input.split(" ");
		String vin = inputArray[0];
		String owner = inputArray[1];
		Car car = new Car(vin, owner);
		//CarDAOSerialization carDAO = new CarDAOSerialization();
		boolean daoBool = carDAO.createCar(car);
		if (daoBool == true) {
			System.out.println("\nSuccessfully added car.");
			return new ManageCarsMenu();
		} else {
			System.out.println("\nFailed to add car, please try again.");
			return new AddCarMenu();
		}
		
	}
	
	public Menu getNextMenuMakeOffer(String input) {
		// Should return view new cars after making an offer object.
		if (input.contentEquals("Back")) {
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
			OfferDAOSerialization offerDAO = new OfferDAOSerialization();
			boolean result = offerDAO.createOffer(offer);
			if (result) {
				System.out.println("Succeeded in making offer.");
				return new ViewNewCarsMenu();
			} else {
				System.out.println("Failed to make offer, please try again.");
				return new MakeAnOfferMenu();
			}
		} catch (Exception e) {
			System.out.println("Failed to make offer, please try again.");
			return new MakeAnOfferMenu();
		}
	}
	
	public Menu getNextMenuManageOffers(String input) {
		// Should do some stuff before returning manage offers or employee menu.
		if (input.contentEquals("Back")) {
			return new EmployeeMenu();
		} else {
			try {
				String[] arrInput = input.split(" ");
				OfferDAOSerialization offerDAO = new OfferDAOSerialization();
				Offer outputOffer = offerDAO.readOffer(arrInput[0]);
				if (arrInput[1].contentEquals("Accept")) {
					outputOffer.setStatus("Accepted");
					boolean result2 = rejectAllOtherOffers(outputOffer.vin, offerDAO);
					if (result2) {
						System.out.println("Rejected all other offers.");
						boolean result = offerDAO.createOffer(outputOffer);
						if (result) {
							System.out.println("Offer update succeeded.");
							Car car = carDAO.readCar(outputOffer.vin);
							car.owner = outputOffer.username;
							boolean result3 = carDAO.updateCar(car.vin, car);
							if (result3) {
								System.out.println("Car update succeeded.");
								return new EmployeeMenu();
							} else {
								System.out.println("Failed to update offer, please try again");
								return new ManageOffersMenu();
							}
						} else {
							System.out.println("Failed to update offer, please try again");
							return new ManageOffersMenu();
						}
					} else {
						System.out.println("Failed to reject other offers. Contact the administrator.");
						return new ManageOffersMenu();
					}
				} else if (arrInput[1].contentEquals("Reject")) {
					outputOffer.setStatus("Rejected");
					offerDAO.updateOffer(outputOffer.offerId, outputOffer);
					System.out.println("Offer update succeeded.");
					return new EmployeeMenu();
				} else {
					System.out.println("Failed to update offer, please try again.");
					return new ManageOffersMenu();
				}
			} catch (Exception e) {
				System.out.println("Failed to update offer, please try again");
				return new ManageOffersMenu();
			}
		}
	}
	
	public Menu getNextMenuRemoveCar(String input) {
		String vin = input;
		if (input.contentEquals("Back")) {
			return new ManageCarsMenu();
		}
		boolean daoBool = carDAO.deleteCar(vin);
		if (daoBool == true) {
			System.out.println("Successfully deleted car.");
			return new ManageCarsMenu();
		} else {
			System.out.println("Failed to delete car, please try again.");
			return new RemoveCarMenu();
		}
	}
	
	public Menu getNextMenuMyCars(String input) {
		// View remaining payments is forward or customer menu is back. Entering the vin takes to specific car.
		if (input.contentEquals("Back")) {
			return new CustomerMenu();
		} else {
			CarDAOSerialization carDAO = new CarDAOSerialization();
			Car car = carDAO.readCar(input);
			if (car != null) {
				Menu menu = new Menu();
				menu.outputLines.add("\nViewing your remaining payments.\n");
				menu.outputLines.add("If you would like to go back to the previous screen, type \"Back\"");
				menu.outputLines.add("\nYour car:");
				StringBuilder sb = new StringBuilder();
				sb.append(car.vin + " " + car.owner);
				menu.outputLines.add(sb.toString());
				sb.delete(0, sb.length());
				sb.append(Menu.userName);
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
				return menu;
			} else {
				System.out.println("Incorrect vin, please try again.");
				return new ViewMyCarsMenu();
			}
		}
	}
	
	public Menu getNextMenuNewCars(String input) {
		// Make an offer menu is forward customer menu is back.
		if (input.contentEquals("Back")) {
			return new CustomerMenu();
		} else {
			CarDAOSerialization carDAO = new CarDAOSerialization();
			Car car = carDAO.readCar(input);
			if (car != null) {
				Menu.prevInput = input;
				return new MakeAnOfferMenu();
			} else {
				System.out.println("Incorrect vin, please try again.");
				return new ViewNewCarsMenu();
			}
		}
	}
	
	public Menu getNextMenuAllPayments(String input) {
		// Go back to manage cars menu.
		// User input is in the form "[VIN]"
		// Read all the payments where the title contains vin.
		if (input.contentEquals("Back")) {
			return new ManageCarsMenu();
		} else {
			ArrayList<String> paymentArray = new ArrayList<>();
			paymentArray = paymentDAO.getAllPayments();
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
			return menu;
		}
	}
	
	public Menu getNextMenuMakePayment(String input) {
		// Go back to customer menu.
		// User input is a string in the form "[Payment Amount] [Vin]"
		if (input.contentEquals("Back")) {
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
				return new CustomerMenu();
			} else {
				System.out.println("Failed to make a payment, please try again.");
				return new MakePaymentMenu();
			}
		} catch (Exception e) {
			System.out.println("Failed to make a payment, please try again.");
			return new MakePaymentMenu();
		}
	}
	
	public boolean rejectAllOtherOffers(String vin, OfferDAOSerialization offerDAO) {
		try {
			ArrayList<String> listOffer = offerDAO.getAllOffers();
			for (String s : listOffer) {
				if (s.contains(vin)) {
					String fileDeleteName = ".//src//main//resources//offers//" + s + ".dat";
					File fileDelete = new File(fileDeleteName);
					fileDelete.delete();
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
