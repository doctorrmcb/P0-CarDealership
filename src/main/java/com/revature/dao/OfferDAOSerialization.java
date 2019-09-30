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

import com.revature.pojos.finance.Offer;

public class OfferDAOSerialization implements OfferDAO {
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
	public Offer readOffer(String offerId) {
		return null;
	}
	public boolean updateOffer(String offerToUpdate, Offer Offer) {
		return false;
	}
	public boolean deleteOffer(String offerId) {
		return false;
	}
}
