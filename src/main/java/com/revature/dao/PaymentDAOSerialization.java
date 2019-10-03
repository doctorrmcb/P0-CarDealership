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
import java.text.NumberFormat;
import java.util.ArrayList;

import com.revature.pojos.finance.Payment;

public class PaymentDAOSerialization implements PaymentDAO {
	public boolean createPayment(Payment payment) {
		String fileName;
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		String directoryName = ".//src//main//resources//payments//";
		Path paymentPath = Paths.get(directoryName);
		if (Files.notExists(paymentPath, LinkOption.NOFOLLOW_LINKS)) {
			new File(directoryName).mkdir();
		}
		if (payment.getPaymentId() != null) {
			fileName = ".//src//main//resources//payments//" + payment.getPaymentId() + ".dat";
		} else {
			return false;
		}

		try {
			fos = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(payment);
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
	public Payment readPayment(String paymentId) {
		
		Payment payment = null;
		
		//try with resources
		try (FileInputStream fis = new FileInputStream(".//src//main//resources//payments//" + paymentId + ".dat");
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			payment = (Payment) ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return payment;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return payment;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return payment;
		}
			
		return payment;
	}
	
	public boolean updatePayment(String paymentIdToUpdate, Payment outputPayment) {
		String fileName;
		String fileDeleteName;
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		File fileDelete = null;
		File file = null;
		
		if (paymentIdToUpdate != null) {
			fileDeleteName = ".//src//main//resources//payments//" + paymentIdToUpdate + ".dat";
			fileDelete = new File(fileDeleteName);
		} else {
			return false;
		}

		fileDelete.delete();
		
		if (outputPayment != null) {
			fileName = ".//src//main//resources//payments//" + outputPayment.getPaymentId() + ".dat";
			file = new File(fileName);
		} else {
			return false;
		}
		
		try {
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(outputPayment);
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
	public boolean deletePayment(String paymentId) {
		File fileDelete = null;
		String fileDeleteName;
		if (paymentId != null) {
			fileDeleteName = ".//src//main//resources//payments//" + paymentId + ".dat";
			fileDelete = new File(fileDeleteName);
			fileDelete.delete();
		} else {
			return false;
		}
		return true;
	}
	
	@Override
	public ArrayList<String> getAllPayments() {
		String directoryName = ".//src//main//resources//payments//";
		ArrayList<String> testArray = new ArrayList<>();
		// Use below line in DAO refactor.
		//Payment[] offertest = new Payment[5];
		File file = new File(directoryName);
		File[] testReturn = file.listFiles();
		StringBuilder sb = new StringBuilder();
		for (File f : testReturn) {
			sb.append(f.getName());
			sb.trimToSize();
			sb.delete(sb.length() - 4, sb.length());
			Payment payment = readPayment(sb.toString());
			sb.delete(0, sb.length());
			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			String formattedAmount = formatter.format(payment.amount);
			sb.append(payment.paymentDate + "\t" + formattedAmount + "\t" + payment.owner);
			testArray.add(sb.toString());
			sb.delete(0, sb.length());
		}
		return testArray;
	}
}
