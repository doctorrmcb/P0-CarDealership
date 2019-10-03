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
import java.util.ArrayList;
import com.revature.pojos.Car;

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
	
	@Override
	public ArrayList<String> getAllCars() {
		String directoryName = ".//src//main//resources//cars//";
		//String carPath = Paths.get(directoryName);
		ArrayList<String> testArray = new ArrayList<>();
		/*try (List<Path> pathList = Files.walk(carPath)) {
			pathList.collect(Collectors.toList());	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		File file = new File(directoryName);
		File[] testReturn = file.listFiles();
		StringBuilder sb = new StringBuilder();
		for (File f : testReturn) {
			sb.append(f.getName());
			sb.delete(sb.length() - 4, sb.length());
			Car car = readCar(sb.toString());
			sb.delete(0, sb.length());
			if (car.vin.length() < 8) {
				sb.append(car.vin + "\t\t" + car.owner);
			} else {
				sb.append(car.vin + "\t" + car.owner);
			}
			
			testArray.add(sb.toString());
			sb.delete(0, sb.length());
		}
		return testArray;
	}
}
