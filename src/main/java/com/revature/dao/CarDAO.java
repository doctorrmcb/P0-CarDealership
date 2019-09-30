package com.revature.dao;

import com.revature.pojos.Car;

public interface CarDAO {
	public boolean createCar(Car car); 
	public Car readCar(String vin); 
	public boolean updateCar(String vinToUpdate, Car car);
	public boolean deleteCar(String vin);
}
