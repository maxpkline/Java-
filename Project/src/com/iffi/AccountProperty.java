package com.iffi;

import java.time.LocalDate;

/**
 * Class to hold account Property Assets, as it is just easier to split them up,
 * and then compare them, to make it easier to integrate different values later on
 * @author maxpk
 *
 */

public class AccountProperty extends AccountAsset {

	public LocalDate purchaseDate;
	public double purchasePrice;
	public double propertyFees = 100.0;
	
	public AccountProperty(String accountCode, String assetCode, String assetType, 
			LocalDate purchaseDate, double purchasePrice) {
		super(accountCode, assetCode, assetType);
		this.purchaseDate = purchaseDate;
		this.purchasePrice = purchasePrice;
//		double propertyFees = 100.0;
	}
	
	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	@Override
	public String toString() {
		return "AccountProperty [purchaseDate=" + purchaseDate + ", purchasePrice=" + purchasePrice + "]";
	}

	public double getPropertyFees() {
		return propertyFees;
	}

	

}
