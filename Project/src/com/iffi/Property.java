package com.iffi;

public class Property extends Asset{
	
	public double appraisedValue;
	
	public Property(String assetCode, String assetType, String assetName, double appraisedValue) {
		super(assetCode, assetType, assetName);
		this.appraisedValue = appraisedValue;
	}

	public double getAppraisedValue() {
		return appraisedValue;
	}

	public void setAppraisedValue(double appraisedValue) {
		this.appraisedValue = appraisedValue;
	}

	@Override
	public String toString() {
		return "Property: " + assetCode + ", " + assetName + ", " + appraisedValue;
	}
	
	
	
}
