package com.revature.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.revature.pojos.Car;
import com.revature.pojos.authentication.Account;

public class CarDAOSerialization implements CarDAO {
	@Override
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
	
	@Override
	public Car readCar(String vin) {
			
		Car car = null;
		
		//try with resources
		try (FileInputStream fis = new FileInputStream(".//src//main//resources//cars//" + vin + ".dat");
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			car = (Car) ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return car;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return car;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return car;
		}
			
		return car;
	}

	@Override
	public boolean updateCar(String vinToUpdate, Car outputCar) {
		String fileName;
		String fileDeleteName;
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		File fileDelete = null;
		File file = null;
		String directoryName = ".//src//main//resources//cars//";
		Path carPath = Paths.get(directoryName);
		
		if (vinToUpdate != null) {
			fileDeleteName = ".//src//main//resources//cars//" + vinToUpdate + ".dat";
			fileDelete = new File(fileDeleteName);
		} else {
			return false;
		}

		fileDelete.delete();
		
		if (outputCar != null) {
			fileName = ".//src//main//resources//cars//" + outputCar.getVin() + ".dat";
			file = new File(fileName);
		} else {
			return false;
		}
		
		try {
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(outputCar);
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
	
	@Override
	public boolean deleteCar(String vin) {
		File fileDelete = null;
		String fileDeleteName;
		if (vin != null) {
			fileDeleteName = ".//src//main//resources//cars//" + vin + ".dat";
			fileDelete = new File(fileDeleteName);
			fileDelete.delete();
		} else {
			return false;
		}
		return true;
	}
}
