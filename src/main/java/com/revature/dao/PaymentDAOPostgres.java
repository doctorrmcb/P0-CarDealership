package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.revature.pojos.finance.Payment;
import com.revature.util.ConnectionFactory;

public class PaymentDAOPostgres implements PaymentDAO {
	
	private Connection connection = ConnectionFactory.getConnection();
	
	public void setConn(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public boolean createPayment(Payment payment) {
		String sql = "insert into test.offers (paymentusername, vin, paymenttime, amount) values (?, ?, ?, ?);";
		PreparedStatement stmt;
		
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, payment.getOwner());
			stmt.setString(2, payment.getVin());
			stmt.setTimestamp(3, Timestamp.valueOf(payment.getPaymentDate()));
			stmt.setDouble(4, payment.getAmount());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Put logging here.
			e.printStackTrace();
			return false;			
		}
	}
	
	@Override
	public Payment readPayment(String paymentId) {
		return null;
	}
	
	@Override
	public boolean updatePayment(String paymentIdToUpdate, Payment outputPayment) {
		return false;
	}
	
	@Override
	public boolean deletePayment(String paymentId) {
		return false;
	}
	
	@Override
	public ArrayList<String> getAllPayments() {
		String sql = "select * from test.payments;";
		PreparedStatement stmt;
		
		try {
			ArrayList<String> resultList = new ArrayList<>();
			stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// Date, Amount, Owner
				resultList.add(rs.getTimestamp(3) + "\t" + rs.getDouble(4) + "\t" + rs.getString(1));
			}
			return resultList;
		} catch (SQLException e) {
			//TODO Implement logging.
			e.printStackTrace();
			return null;
		}
	}
}
