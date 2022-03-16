package com.iffi;

public class Crypto extends Asset{

	public double exchangeRate;
	public double exchangeFeeRate;

	public Crypto(String assetCode, String assetType, String assetName, double exchangeRate, double exchangeFeeRate) {
		super(assetCode, assetType, assetName);
		this.exchangeRate = exchangeRate;
		this.exchangeFeeRate = exchangeFeeRate;
	}

	public double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public double getExchangeFeeRate() {
		return exchangeFeeRate;
	}

	public void setExchangeFeeRate(double exchangeFeeRate) {
		this.exchangeFeeRate = exchangeFeeRate;
	}

	@Override
	public String toString() {
		return "Crypto:  " + assetCode + ", "+ assetName + ", " + exchangeRate + ", " + exchangeFeeRate;
	}
	
	
}
