package com.revature.pojos;

import com.revature.pojos.finance.Loan;
import com.revature.pojos.storage.lot.Lot;
import com.revature.pojos.storage.systemlist.OfferList;
import com.revature.pojos.storage.systemlist.PaymentList;

public class Car {
	public String vin;
	public enum make {
		TOYOTA, HONDA, AUDI, CADILLAC, FORD, CHEVROLET, BMW, GM, GMC, JEEP
	};
	public String model;
	public int year;
	public String color;
	public String owner;
	public double price;
	public Lot storageLocation;
	public PaymentList payments;
	public OfferList offers;
	public Loan loan;
}
