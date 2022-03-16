package com.iffi;

import java.time.LocalDate;
/**
 * Class to hold account Stock Assets, as it is just easier to split them up,
 * and then compare them, to make it easier to integrate different values later on
 * @author maxpk
 *
 */
public class AccountStock extends AccountAsset{
	
	public LocalDate purchaseDate;
	public double purchaseSharePrice;
	public double numberOfShares;
	public double dividendTotal;
	
	public AccountStock(String accountCode, String assetCode, String assetType, LocalDate purchaseDate, 
			double purchaseSharePrice, double numberOfShares, double dividendTotal) {
		super(accountCode, assetCode, assetType);
		this.purchaseDate = purchaseDate;
		this.purchaseSharePrice = purchaseSharePrice;
		this.numberOfShares = numberOfShares;
		this.dividendTotal = dividendTotal;
	}
	

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public double getPurchaseSharePrice() {
		return purchaseSharePrice;
	}

	public void setPurchaseSharePrice(double purchaseSharePrice) {
		this.purchaseSharePrice = purchaseSharePrice;
	}

	public double getNumberOfShares() {
		return numberOfShares;
	}

	public void setNumberOfShares(double numberOfShares) {
		this.numberOfShares = numberOfShares;
	}

	public double getDividendTotal() {
		return dividendTotal;
	}

	public void setDividendTotal(double dividendTotal) {
		this.dividendTotal = dividendTotal;
	}


	@Override
	public String toString() {
		return "AccountStock [purchaseDate=" + purchaseDate + ", purchaseSharePrice=" + purchaseSharePrice
				+ ", numberOfShares=" + numberOfShares + ", dividendTotal=" + dividendTotal + "]";
	}
	
	
	

}
