package com.revature.dao;

import java.util.ArrayList;

import com.revature.pojos.finance.Payment;

public class PaymentDAOPostgres implements PaymentDAO {
	
	public boolean createPayment(Payment payment) {
		return false;
	}
	
	public Payment readPayment(String paymentId) {
		return null;
	}
	
	public boolean updatePayment(String paymentIdToUpdate, Payment outputPayment) {
		return false;
	}
	
	public boolean deletePayment(String paymentId) {
		return false;
	}
	
	public ArrayList<String> getAllPayments() {
		return null;
	}
}
