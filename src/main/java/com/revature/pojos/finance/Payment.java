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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	@Override
	public String toString() {
		return "Payment [amount=" + amount + ", owner=" + owner + ", paymentDate=" + paymentDate + ", vin=" + vin
				+ ", paymentId=" + paymentId + "]";
	}
	
}
