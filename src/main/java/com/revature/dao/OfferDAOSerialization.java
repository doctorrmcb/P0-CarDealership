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
import com.revature.pojos.finance.Offer;

public class OfferDAOSerialization implements OfferDAO {
	@Override
	public boolean createOffer(Offer offer) {
		String fileName;
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		String directoryName = ".//src//main//resources//offers//";
		Path offerPath = Paths.get(directoryName);
		if (Files.notExists(offerPath, LinkOption.NOFOLLOW_LINKS)) {
			new File(directoryName).mkdir();
		}
		if (offer.getOfferId() != null) {
			fileName = ".//src//main//resources//offers//" + offer.getOfferId() + ".dat";
		} else {
			return false;
		}

		try {
			fos = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(offer);
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
	public Offer readOffer(String offerId) {
				
		Offer offer = null;
		
		//try with resources
		try (FileInputStream fis = new FileInputStream(".//src//main//resources//offers//" + offerId + ".dat");
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			offer = (Offer) ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return offer;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return offer;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return offer;
		}
			
		return offer;

	}
	
	@Override
	public boolean updateOffer(String offerToUpdate, Offer outputOffer) {
		String fileName;
		String fileDeleteName;
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		File fileDelete = null;
		File file = null;
		String directoryName = ".//src//main//resources//offers//";
		Path offerPath = Paths.get(directoryName);
		
		if (offerToUpdate != null) {
			fileDeleteName = ".//src//main//resources//offers//" + offerToUpdate + ".dat";
			fileDelete = new File(fileDeleteName);
		} else {
			return false;
		}

		fileDelete.delete();
		
		if (outputOffer != null) {
			fileName = ".//src//main//resources//offers//" + outputOffer.getOfferId() + ".dat";
			file = new File(fileName);
		} else {
			return false;
		}
		
		try {
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(outputOffer);
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
	public boolean deleteOffer(String offerId) {
		File fileDelete = null;
		String fileDeleteName;
		if (offerId != null) {
			fileDeleteName = ".//src//main//resources//cars//" + offerId + ".dat";
			fileDelete = new File(fileDeleteName);
			fileDelete.delete();
		} else {
			return false;
		}
		return true;
	}
}
