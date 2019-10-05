package com.revature.dao;

import java.util.ArrayList;

import com.revature.pojos.finance.Payment;

public interface PaymentDAO {
	public boolean createPayment(Payment payment); 
	public Payment readPayment(String paymentId); 
	public ArrayList<String> getAllPayments();
}
