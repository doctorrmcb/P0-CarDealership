package com.revature.dao;

import com.revature.pojos.finance.Offer;

public interface OfferDAO {
	public boolean createOffer(Offer Offer); 
	public Offer readOffer(String offerID); 
	public boolean updateOffer(Offer Offer);
	public boolean deleteOffer(String vin);
}
