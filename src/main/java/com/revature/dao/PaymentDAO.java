package com.revature.dao;

import com.revature.pojos.finance.Payment;

public interface PaymentDAO {
	public boolean createPayment(Payment payment); 
	public Payment readPayment(String paymentId); 
	public boolean updatePayment(Payment payment);
	public boolean deletePayment(String paymentId);
}
