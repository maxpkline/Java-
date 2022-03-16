package com.iffi;

public class Asset {

	public String assetCode;
	public String assetType;
	public String assetName;
	
	public Asset(String assetCode, String assetType, String assetName) {
		super();
		this.assetCode = assetCode;
		this.assetType = assetType;
		this.assetName = assetName;
	}

	public String getAssetCode() {
		return assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	@Override
	public String toString() {
		return assetCode + ", " + assetType + ", " + assetName;
	}
	
	
	
}
