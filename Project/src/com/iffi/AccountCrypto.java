package com.iffi;

import java.time.LocalDate;

/**
 * Class to hold account crypto Assets, as it is just easier to split them up,
 * and then compare them, to make it easier to integrate different values later on
 * @author maxpk
 *
 */
public class AccountCrypto extends AccountAsset{

	public LocalDate purchaseDate;
	public double coinsOwned;
	public double originalExchangeRate;
	public double cryptoFees = 10.00;
	
	public AccountCrypto(String accountCode, String assetCode, String assetType, LocalDate purchaseDate, 
			double originalExchangeRate, double coinsOwned) {
		super(accountCode, assetCode, assetType);
		this.purchaseDate = purchaseDate;
		this.coinsOwned = originalExchangeRate;
		this.originalExchangeRate = coinsOwned;
		
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public double getOriginalExchangeRate() {
		return coinsOwned;
	}

	public void setOriginalExchangeRate(double originalExchangeRate) {
		this.coinsOwned = originalExchangeRate;
	}

	public double getCoinsOwned() {
		return originalExchangeRate;
	}

	public void setCoinsOwned(double coinsOwned) {
		this.originalExchangeRate = coinsOwned;
	}

	@Override
	public String toString() {
		return "AccountCrypto [purchaseDate=" + purchaseDate + ", coinsOwned=" + coinsOwned
				+ ", originalExchangeRate=" + originalExchangeRate + "]";
	}
	
	
	
	

}
