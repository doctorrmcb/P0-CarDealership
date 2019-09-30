package com.revature.pojos.finance;

import java.io.Serializable;

public class Offer implements Serializable {
	public String username;
	public double price;
	public String vin;
	public int durationMonths;
	// Accepted, rejected, or pending.
	public String status;
	public String offerId;
	public Object getOfferId() {
		// TODO Auto-generated method stub
		return this.offerId;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + durationMonths;
		result = prime * result + ((offerId == null) ? 0 : offerId.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((vin == null) ? 0 : vin.hashCode());
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
		Offer other = (Offer) obj;
		if (durationMonths != other.durationMonths)
			return false;
		if (offerId == null) {
			if (other.offerId != null)
				return false;
		} else if (!offerId.equals(other.offerId))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (vin == null) {
			if (other.vin != null)
				return false;
		} else if (!vin.equals(other.vin))
			return false;
		return true;
	}
	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}
}
