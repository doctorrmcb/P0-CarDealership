package com.revature.pojos;

import com.revature.pojos.finance.Loan;
import java.io.Serializable;
import com.revature.pojos.storage.systemlist.OfferList;
import com.revature.pojos.storage.systemlist.PaymentList;

public class Car implements Serializable {
	public String vin;
	public enum make {
		TOYOTA, HONDA, AUDI, CADILLAC, FORD, CHEVROLET, BMW, GM, GMC, JEEP
	};
	//public String model;
	//public int year;
	//public String color;
	// Customer username, or company.
	public String owner;
	//public double price;
	//public Lot storageLocation;
	//public PaymentList payments;
	//public OfferList offers;
	//public Loan loan;
	
	public Car(String vin, String owner) {
		super();
		this.vin = vin;
		this.owner = owner;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
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
		Car other = (Car) obj;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (vin == null) {
			if (other.vin != null)
				return false;
		} else if (!vin.equals(other.vin))
			return false;
		return true;
	}
}
