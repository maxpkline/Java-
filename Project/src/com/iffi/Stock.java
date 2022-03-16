package com.iffi;

public class Stock extends Asset{
	
	public String stockSymbol;
	public double sharePrice;

	public Stock(String assetCode, String assetType, String assetName, String stockSymbol, double sharePrice) {
		super(assetCode, assetType, assetName);
		this.stockSymbol = stockSymbol;
		this.sharePrice = sharePrice;
	}

	
	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public double getSharePrice() {
		return sharePrice;
	}

	public void setSharePrice(double sharePrice) {
		this.sharePrice = sharePrice;
	}


	@Override
	public String toString() {
		return "Stock: " + assetCode + ", " + assetName + ", " + stockSymbol + ", " + sharePrice;
	}
	
	
	

}
