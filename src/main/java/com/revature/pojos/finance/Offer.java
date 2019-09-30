package com.revature.pojos.finance;

import com.revature.pojos.Car;

public class Offer {
	public String username;
	public double price;
	public String vin;
	public int durationMonths;
	// Accepted, rejected, or pending.
	public String status;
	public String offerId;
	public Object getOfferId() {
		// TODO Auto-generated method stub
		return null;
	}
	public Offer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Offer(String username, double price, String vin, int durationMonths) {
		super();
		this.username = username;
		this.price = price;
		this.vin = vin;
		this.durationMonths = durationMonths;
		this.offerId = username + vin;
		this.status = "Pending";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public int getDurationMonths() {
		return durationMonths;
	}
	public void setDurationMonths(int durationMonths) {
		this.durationMonths = durationMonths;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}
}
