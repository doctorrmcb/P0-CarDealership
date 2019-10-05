package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.pojos.Car;
import com.revature.util.ConnectionFactory;

public class CarDAOPostgres implements CarDAO {
	
	private Connection connection = ConnectionFactory.getConnection();
	
	public void setConn(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public boolean createCar(Car car) {
		String sql = "insert into test.cars (ownerusername, vin) values (?, ?);";
		PreparedStatement stmt;
		
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, car.getOwner());
			stmt.setString(2, car.getVin());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Put logging here.
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public Car readCar(String vin) {
		String sql = "select * from test.cars where vin = ?;";
		PreparedStatement stmt;
		
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, vin);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			Car car = new Car(rs.getString("vin"), rs.getString("ownerusername"));
			return car;
		} catch (SQLException e) {
			//TODO Implement logging.
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean updateCar(String vinToUpdate, Car car) {
		String sql = "update test.cars set ownerusername = ?, vin = ? where vin = ?;";
		PreparedStatement stmt;
		
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, car.getOwner());
			stmt.setString(2, car.getVin());
			stmt.setString(3, vinToUpdate);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			//TODO Implement logging.
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean deleteCar(String vin) {
		String sql = "delete from test.cars where vin = ?;";
		PreparedStatement stmt;
		
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, vin);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			//TODO Implement logging.
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public ArrayList<String> getAllCars() {
		String sql = "select * from test.cars;";
		PreparedStatement stmt;
		
		try {
			ArrayList<String> resultList = new ArrayList<>();
			stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// Vin, Owner
				resultList.add(rs.getString("vin") + "\t" + rs.getString("ownerusername"));
			}
			return resultList;
		} catch (SQLException e) {
			//TODO Implement logging.
			e.printStackTrace();
			return null;
		}
	}
}
