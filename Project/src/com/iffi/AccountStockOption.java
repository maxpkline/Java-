package com.iffi;

import java.time.LocalDate;

/** 
 * This class is used to hold instances of Stock Call options from the Accounts.csv file.
 * Each Stock option differs, but they use the same variables to determine loss or gain,
 * unlike owning the stock outright. The specific Account asset is always associated with 
 * the owner account through the use of a list in the Owner Account holding every asset 
 * which extends AccountAsset class
 * @author maxpk
 *
 */
public class AccountStockOption extends AccountAsset {

	public String optionType;
	public LocalDate purchaseDate;
	public double shareStrikePrice;
	public int shareLimit;
	public double sharePremium;
	public LocalDate strikeDate;
	
	public AccountStockOption(String accountCode, String assetCode, String assetType, String optionType, 
			LocalDate purchaseDate, double shareStrikePrice, int shareLimit, double sharePremium, LocalDate strikeDate) {
		super(accountCode, assetCode, assetType);
		this.optionType = optionType;
		this.purchaseDate = purchaseDate;
		this.shareStrikePrice = shareStrikePrice;
		this.shareLimit = shareLimit;
		this.sharePremium = sharePremium;
		this.strikeDate = strikeDate;
	}

	

	public String getOptionType() {
		return optionType;
	}

	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public double getShareStrikePrice() {
		return shareStrikePrice;
	}

	public void setShareStrikePrice(double shareStrikePrice) {
		this.shareStrikePrice = shareStrikePrice;
	}

	public int getShareLimit() {
		return shareLimit;
	}

	public void setShareLimit(int shareLimit) {
		this.shareLimit = shareLimit;
	}

	public double getSharePremium() {
		return sharePremium;
	}

	public void setSharePremium(double sharePremium) {
		this.sharePremium = sharePremium;
	}

	public LocalDate getStrikeDate() {
		return strikeDate;
	}

	public void setStrikeDate(LocalDate strikeDate) {
		this.strikeDate = strikeDate;
	}


	@Override
	public String toString() {
		return "AccountStockOption [optionType=" + optionType + ", purchaseDate=" + purchaseDate + ", shareStrikePrice="
				+ shareStrikePrice + ", shareLimit=" + shareLimit + ", sharePremium=" + sharePremium + ", strikeDate="
				+ strikeDate + "]";
	}
	
	

}
