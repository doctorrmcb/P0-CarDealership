package com.revature.dao;

import java.util.ArrayList;

import com.revature.pojos.finance.Payment;

public interface PaymentDAO {
	public boolean createPayment(Payment payment); 
	public Payment readPayment(String paymentId); 
	public boolean updatePayment(String paymentIdToUpdate, Payment outputPayment);
	public boolean deletePayment(String paymentId);
	public ArrayList<String> getAllPayments();
}
