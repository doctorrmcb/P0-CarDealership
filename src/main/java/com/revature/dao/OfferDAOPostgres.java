package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.pojos.finance.Offer;
import com.revature.util.ConnectionFactory;

public class OfferDAOPostgres implements OfferDAO {
	
	private Connection connection = ConnectionFactory.getConnection();
	
	public void setConn(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public boolean createOffer(Offer offer) {
		String sql = "insert into test.offers (offerusername, vin, price, durationmonths, offerstatus) values (?, ?, ?, ?, ?);";
		PreparedStatement stmt;
		
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, offer.getUsername());
			stmt.setString(2, offer.getVin());
			stmt.setDouble(3, offer.getPrice());
			stmt.setInt(4, offer.getDurationMonths());
			stmt.setString(5, offer.getStatus());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Put logging here.
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public Offer readOffer(String offerID) {
		String[] arrInput = offerID.split("_");
		String sql = "select * from test.offers where offerusername = ? and vin = ?;";
		PreparedStatement stmt;
		
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, arrInput[0]);
			stmt.setString(2, arrInput[1]);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			Offer result = new Offer();
			result.setDurationMonths(rs.getInt("durationmonths"));
			result.setPrice(rs.getDouble("price"));
			result.setStatus(rs.getString("offerstatus"));
			result.setUsername(rs.getString("offerusername"));
			result.setVin(rs.getString("vin"));
			result.setOfferId(rs.getString("offerusername") + "_" + rs.getString("vin"));
			return result;
		} catch (SQLException e) {
			// TODO Put logging here.
			e.printStackTrace();
			return null;			
		}
	}
	
	@Override
	public boolean updateOffer(String offerToUpdate, Offer offer) {
		String[] arrInput = offerToUpdate.split("_");
		String sql = "update test.offers set offerusername = ?, vin = ?, price = ?, durationmonths = ?, offerstatus = ? where offerusername = ? and vin = ?;";
		PreparedStatement stmt;
		
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, offer.getUsername());
			stmt.setString(2, offer.getVin());
			stmt.setDouble(3, offer.getPrice());
			stmt.setInt(4, offer.getDurationMonths());
			stmt.setString(5, offer.getStatus());
			stmt.setString(6, arrInput[0]);
			stmt.setString(7, arrInput[1]);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Put logging here.
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean deleteOffer(String offerID) {
		String[] arrInput = offerID.split("_");
		String sql = "delete from test.offers where offerusername = ? and vin = ?;";
		PreparedStatement stmt;
		
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, arrInput[0]);
			stmt.setString(2, arrInput[1]);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Put logging here.
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public ArrayList<String> getAllOffers() {
		String sql = "select * from test.offers;";
		PreparedStatement stmt;
		
		try {
			ArrayList<String> resultList = new ArrayList<>();
			stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				//OfferID(username+vin), Status
				resultList.add(rs.getString(1) + "_" + rs.getString(2) + "\t" + rs.getString(5));
			}
			return resultList;
		} catch (SQLException e) {
			//TODO Implement logging.
			e.printStackTrace();
			return null;
		}
		
	}
}
