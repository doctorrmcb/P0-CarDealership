package com.revature.pojos.finance;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Payment implements Serializable {
	public double amount;
	public String owner;
	public LocalDateTime paymentDate;
	public String vin;
	public String paymentId;
	public Payment(double amount, String owner, String vin) {
		super();
		this.amount = amount;
		this.owner = owner;
		this.paymentDate = LocalDateTime.now();
		this.vin = vin;
		this.paymentId = vin + paymentDate.toString();
	}
	
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
