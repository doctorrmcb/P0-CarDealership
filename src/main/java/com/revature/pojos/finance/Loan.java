package com.revature.pojos.finance;

public class Loan {
	public enum InterestType {
		SIMPLE, COMPOUND;
	}
	
	public enum Period {
		WEEKLY, MONTHLY, YEARLY;
	}
	
	private double principle;
	
	private double interest;
	
	private int length;  // in years
	
	private InterestType interestType;
	
	private Period period;
}
