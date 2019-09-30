package com.revature.dao;

import com.revature.pojos.finance.Payment;

public class PaymentDAOSerialization implements PaymentDAO {
	public boolean createPayment(Payment payment) {
		return false;
	}
	public Payment readPayment(String paymentId) {
		return null;
	}
	public boolean updatePayment(Payment payment) {
		return false;
	}
	public boolean deletePayment(String paymentId) {
		return false;
	}
}
