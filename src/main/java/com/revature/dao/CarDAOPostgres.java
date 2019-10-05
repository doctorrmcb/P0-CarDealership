package com.revature.dao;

import java.util.ArrayList;

import com.revature.pojos.Car;

public class CarDAOPostgres implements CarDAO {
	
	public boolean createCar(Car car) {
		return false;
	}
	
	public Car readCar(String vin) {
		return null;
	}
	
	public boolean updateCar(String vinToUpdate, Car car) {
		return false;
	}
	
	public boolean deleteCar(String vin) {
		return false;
	}
	
	public ArrayList<String> getAllCars() {
		return null;
	}
}
