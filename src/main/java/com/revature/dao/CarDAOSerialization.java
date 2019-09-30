package com.revature.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.revature.pojos.Car;

public class CarDAOSerialization implements CarDAO {
	public boolean createCar(Car car) {
		String fileName;
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		String directoryName = ".//src//main//resources//cars//";
		Path carPath = Paths.get(directoryName);
		if (Files.notExists(carPath, LinkOption.NOFOLLOW_LINKS)) {
			new File(directoryName).mkdir();
		}
		if (car.getVin() != null) {
			fileName = ".//src//main//resources//cars//" + car.getVin() + ".dat";
		} else {
			return false;
		}

		try {
			fos = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(car);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		return true;

	}
	public Car readCar(String vin) {
		return null;
	}
	public boolean updateCar(Car car) {
		return false;
	}
	public boolean deleteCar(String vin) {
		return false;
	}
}
