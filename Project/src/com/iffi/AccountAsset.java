package com.iffi;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class is a basis for every asset that is held within an account, works as a Parent to 
 * the AccountProperty, AccountStock, AccountStockOption, and AccountCrypto classes. These AccountAssets
 * are then stored in a list of AccountAssets in the account object, upon the creation of the specified 
 * account object
 * @author maxpk
 *
 */
public class AccountAsset {

	public String accountCode;
	public String assetCode;
	public String assetType;
	
	public AccountAsset(String accountCode, String assetCode, String assetType) {
		super();
		this.accountCode = accountCode;
		this.assetCode = assetCode;
		this.assetType = assetType;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
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

	@Override
	public String toString() {
		return "AccountAsset [accountCode=" + accountCode + ", assetCode=" + assetCode + ", assetType=" + assetType
				+ "]";
	}
	
	
	/**
	 * This method creates the Property string to be output to the standard output, in relation to
	 * the Account Details report. This function essentially just calculates the appropriate gain 
	 * and creates a string that looks clean, as well as de-clutttering the AccountReport class
	 * @param assetCode
	 * @param tempProperty
	 * @return
	 */
	public String getPropertyOutputString(String assetCode, AccountProperty tempProperty) {
		List<Asset> assetList = new ArrayList<Asset>();
		assetList = DataParser.loadAssetFile();
		double appraisedValue = 0.0;
		String output = null; 
		
		for(Asset a : assetList) {
			if(a instanceof Property && a.getAssetCode().contentEquals(tempProperty.getAssetCode())) {
				appraisedValue = ((Property) a).getAppraisedValue();
				double gainPercent = ((appraisedValue/tempProperty.getPurchasePrice())*100) - 100;
				
				output = "" + a.getAssetCode() + "          " + a.getAssetName() + " (Property)" + "\n" +
						"  Cost Basis:  purchased @ $" + tempProperty.getPurchasePrice() + " on " + tempProperty.getPurchaseDate() + "\n" +
						"  Value Basis: appraised @ $" + appraisedValue + "                              " + gainPercent + "%    $       " + appraisedValue + "\n";
			}
		}
		return output;
	}
	
	
	/**
	 * This method creates the crypto String to be output to the standard format in relation to the 
	 * Account Details Summary. All of the correct values are passed back to whatever calls it,
	 * in the form of a string
	 * @param assetCode
	 * @param tempCrypto
	 * @return
	 */
	public String getCryptoOutputString(String assetCode, AccountCrypto tempCrypto) {
		List<Asset> assetList = new ArrayList<Asset>();
		assetList = DataParser.loadAssetFile();
		String output = null;
		
		for(Asset a: assetList) {
			if (a instanceof Crypto && a.getAssetCode().contentEquals(tempCrypto.getAssetCode())){
				double cryptoTotal = (((Crypto) a).getExchangeRate()*tempCrypto.getCoinsOwned()) - (((((Crypto) a).getExchangeFeeRate()/100)*((Crypto) a).getExchangeRate())*tempCrypto.getCoinsOwned());
				double gainPercent = ((cryptoTotal/(tempCrypto.getOriginalExchangeRate() * tempCrypto.getCoinsOwned()))*100) -100; // gain / original 
				
				output = "" + a.getAssetCode() + "          " + a.getAssetName() + " (Crypto)" + "\n" +
						"  Cost Basis:  " + tempCrypto.getCoinsOwned() + " coins @ $" + tempCrypto.getOriginalExchangeRate() + " on " + tempCrypto.getPurchaseDate() + "\n" +
						"  Value Basis: " + tempCrypto.getCoinsOwned() + " coins @ $" + ((Crypto) a).getExchangeRate() + " less " + ((Crypto) a).getExchangeFeeRate() + "%            " + gainPercent + "%    $      " + cryptoTotal + "\n";
			}
		}
		return output;
	}
	
	
	/** 
	 * This method is used to get a string of a Stock to be output to the standard format in 
	 * relation to the Account Details Summary. 
	 * @param assetCode
	 * @param tempStock
	 * @return
	 */
	public String getStockOutputString(String assetCode, AccountStock tempStock) {
		List<Asset> assetList = new ArrayList<Asset>();
		assetList = DataParser.loadAssetFile();
		String output = null;
		
		for(Asset a : assetList) {
			if (a instanceof Stock && a.getAssetCode().contentEquals(tempStock.getAssetCode())){
				double gain = (tempStock.getNumberOfShares() * ((Stock) a).getSharePrice()) + tempStock.getDividendTotal();
				double gainPercent = ((gain / (tempStock.getNumberOfShares() * tempStock.getPurchaseSharePrice()))*100) - 100;
				output = "" + a.getAssetCode() + "     " + a.getAssetName() + "           " + ((Stock) a).getStockSymbol() + " (Stock)" + "\n" +
						"  Cost Basis:  " + tempStock.getNumberOfShares() + " shares @ " + tempStock.getPurchaseSharePrice() + " on " + tempStock.getPurchaseDate() + "\n" + 
						"  Value Basis: " + tempStock.getNumberOfShares() + " shares @ " + ((Stock) a).getSharePrice() + "                       " + gainPercent + "%    $      " + gain + "\n";
			}
		}
		
		return output;
	}
	
	
	/**
	 * This method is used to get the information pertaining to a call option, and return 
	 * that as a string to the Account Report class where it can be output to the standard output/ output.txt
	 * @param assetCode
	 * @param tempStock
	 * @return
	 */
	public String getCallOptionOutputString(String assetCode, AccountStockOption tempStock) {
		List<Asset> assetList = new ArrayList<Asset>();
		assetList = DataParser.loadAssetFile();
		String output = null;
		
		
		for(Asset a : assetList) {
			if (a instanceof Stock && a.getAssetCode().contentEquals(tempStock.getAssetCode())) {
				
				// FOR A SHORT CALL OPTION
				if(((Stock) a).getSharePrice() > tempStock.getShareStrikePrice()) {
					double cost = tempStock.getSharePremium() * tempStock.getShareLimit();
					double value = (((Stock) a).getSharePrice() - tempStock.getShareStrikePrice()) * tempStock.getShareLimit();
					double gain = value-cost;
					double gainPercentage = ((gain/cost)*100) - 100;
					
					output = "" + a.getAssetCode() + "    " + a.getAssetName() + " Option " + ((Stock) a).getStockSymbol() + " (Call)" + "\n" + 
							"  Buy upto " + tempStock.getShareLimit() + " shares @ " + tempStock.getShareStrikePrice() + " til " + tempStock.getStrikeDate() + "\n" +
							"  Premium of $" + tempStock.getSharePremium() + "/share ($" + cost + " total)" + "\n" +
							"  Share Price: $" + ((Stock) a).getSharePrice() + "\n" +
							"  Short Call Value: " + tempStock.getShareLimit() + " shares @ $" + tempStock.getSharePremium() + " = $" + gain + "       " + gainPercentage + "%    $        " + value + "\n";
				
					// FOR A LONG CALL OPTION 
				} else if (((Stock) a).getSharePrice() <= tempStock.getShareStrikePrice()) {
					double cost = tempStock.getSharePremium() * tempStock.getShareLimit();
					
					output = "" + a.getAssetCode() + "    " + a.getAssetName() + " Option " + ((Stock) a).getStockSymbol() + " (Call)" + "\n" + 
							"  Buy upto " + tempStock.getShareLimit() + " shares @ " + tempStock.getShareStrikePrice() + " til " + tempStock.getStrikeDate() + "\n" +
							"  Premium of $" + tempStock.getSharePremium() + "/share ($" + cost + " total)" + "\n" +
							"  Share Price: $" + ((Stock) a).getSharePrice() + "\n" +
							"  Long Call (not executed); Total loss: $" + cost + "       -100.00%    $        0.00" + "\n";
				}
			}
		}
		
		return output;
	}
	
	
	/**
	 * This Method is used to get the String for a put option, which can be different based on 
	 * what type of put it is. A complete string is passed back to the Account Report class to be 
	 * output to the standard output/ output.txt
	 * @param assetCode
	 * @param tempStock
	 * @return
	 */
	public String getPutOptionOutputString(String assetCode, AccountStockOption tempStock) {
		List<Asset> assetList = new ArrayList<Asset>();
		assetList = DataParser.loadAssetFile();
		String output = null;
		
		for(Asset a : assetList) {
			
			if (a instanceof Stock && a.getAssetCode().contentEquals(tempStock.getAssetCode())) {
				double putCheck = ((tempStock.getShareStrikePrice() - ((Stock) a).getSharePrice()) * tempStock.getShareLimit()) + (tempStock.getSharePremium() * tempStock.getShareLimit());

				//FOR NEITHER
				if(putCheck == 0.00) { 
					
					output = "" + a.getAssetCode() + "    " + a.getAssetName() + " Option " + ((Stock) a).getStockSymbol() + " (Put)" + "\n" +
							"  Sell upto " + tempStock.getShareLimit() + " shares @ " + tempStock.getShareStrikePrice() + " til " + tempStock.getStrikeDate() + "\n" +
							"  Premium of $" + tempStock.getSharePremium() + "/share ($" + (tempStock.getShareLimit()*tempStock.getShareLimit()) + " total)" + "\n" +
							"  Share Price: $" + ((Stock) a).getSharePrice() + "\n" +
							"  Short Put Value: " + tempStock.getShareLimit() + " shares @ $" + tempStock.getSharePremium() + " = $" + putCheck +  "       0.000%    $        " + putCheck + "\n";
					
				// FOR A LONG PUT 
				} else if(((Stock) a).getSharePrice() <= tempStock.getShareStrikePrice() && putCheck != 0)  {
					double cost = tempStock.getSharePremium() * tempStock.getShareLimit();
					
					output = "" + a.getAssetCode() + "    " + a.getAssetName() + " Option " + ((Stock) a).getStockSymbol() + " (Put)" + "\n" +
							"  Sell upto " + tempStock.getShareLimit() + " shares @ " + tempStock.getShareStrikePrice() + " til " + tempStock.getStrikeDate() + "\n" +
							"  Premium of $" + tempStock.getSharePremium() + "/share ($" + cost + " total)" + "\n" +
							"  Share Price: $" + ((Stock) a).getSharePrice() + "\n" +
							"  Long Put Value: " + tempStock.getShareLimit() + " shares @ $" + tempStock.getSharePremium() + " = $" + cost +  "       100.00%    $        " + cost + "\n";
				
				// FOR A SHORT PUT OR SHORT PUT WITH GAIN 
				} else if (((Stock) a).getSharePrice() > tempStock.getShareStrikePrice() && putCheck != 0) {
					double cost = tempStock.getSharePremium() * tempStock.getShareLimit();
					double value = ((tempStock.getShareStrikePrice() - ((Stock) a).getSharePrice()) * tempStock.getShareLimit()) + cost;
					
					// SHORT PUT WITH GAIN
					if(value > 0) {
						output = "" + a.getAssetCode() + "    " + a.getAssetName() + " Option " + ((Stock) a).getStockSymbol() + " (Put)" + "\n" +
								"  Sell upto " + tempStock.getShareLimit() + " shares @ " + tempStock.getShareStrikePrice() + " til " + tempStock.getStrikeDate() + "\n" +
								"  Premium of $" + tempStock.getSharePremium() + "/share ($" + cost + " total)" + "\n" +
								"  Share Price: $" + ((Stock) a).getSharePrice() + "\n" +
								"  Short Put Value: " + tempStock.getShareLimit() + " shares @ $" + tempStock.getSharePremium() + " = $" + value +  "       100.00%    $        " + value + "\n";
					// SHORT PUT WITH NO GAIN
					} else if (value < 0) {
						output = "" + a.getAssetCode() + "    " + a.getAssetName() + " Option " + ((Stock) a).getStockSymbol() + " (Put)" + "\n" +
								"  Sell upto " + tempStock.getShareLimit() + " shares @ " + tempStock.getShareStrikePrice() + " til " + tempStock.getStrikeDate() + "\n" +
								"  Premium of $" + tempStock.getSharePremium() + "/share ($" + cost + " total)" + "\n" +
								"  Share Price: $" + ((Stock) a).getSharePrice() + "\n" +
								"  Short Put Value: " + tempStock.getShareLimit() + " shares @ $" + tempStock.getSharePremium() + " = $" + value +  "       -100.00%    $        " + value + "\n";
					}
				}
			}
		}
		
		return output;
	}
	
	
	
	/**
	 * This function Returns the totals in the form of a list to the account report class, so that it can be
	 * output to the standard output/output.txt
	 * @param a
	 * @return
	 */
	public static List<Double> getTotalsForAccountDetails(Account a) {
		double totalValue = 0.0, costBasis = 0.0, totalFees = 0.0, totalReturn = 0.0, totalReturnPercent = 0.0;
		List<Asset> assetList = new ArrayList<Asset>();
		assetList = DataParser.loadAssetFile();
		
		
		for(Asset asset : assetList) {
			for (AccountAsset aa : a.assetList) {
				
				//If the asset in the assetList has the same code as the asset in the asset file, we can then calculate the totals based on type of asset 
				if(aa.getAssetCode().contentEquals(asset.getAssetCode())) {
					//If the asset is a property
					if(aa instanceof AccountProperty) {
						totalValue += ((Property) asset).getAppraisedValue();
						costBasis += ((AccountProperty)aa).getPurchasePrice();
						totalFees += 100;
						
					//If the asset is a Cryptocurrency
					} else if (aa instanceof AccountCrypto) {
						totalValue += (((Crypto) asset).getExchangeRate()*((AccountCrypto) aa).getCoinsOwned()) - (((((Crypto) asset).getExchangeFeeRate()/100)*((Crypto) asset).getExchangeRate())*((AccountCrypto) aa).getCoinsOwned());
						costBasis += ((AccountCrypto) aa).getCoinsOwned() * ((AccountCrypto) aa).getOriginalExchangeRate();
						totalFees += 10;
						
					//If the asset is a Stock
					} else if (aa instanceof AccountStock) {
						totalValue += (((AccountStock) aa).getNumberOfShares() * ((Stock) asset).getSharePrice()) + ((AccountStock) aa).getDividendTotal();;
						costBasis += ((AccountStock) aa).getNumberOfShares() * ((AccountStock) aa).getPurchaseSharePrice();
					
					//If the asset is an AccountStockOption
					} else if (aa instanceof AccountStockOption) {
						
						// For a call option 
						 if(((AccountStockOption) aa).getOptionType().contentEquals("SC")) {
							 // For a Short Call
							 if(((Stock) asset).getSharePrice() > ((AccountStockOption)aa).getShareStrikePrice()) {
								 totalValue += (((Stock) asset).getSharePrice() - ((AccountStockOption) aa).getShareStrikePrice()) * ((AccountStockOption) aa).getShareLimit();
								 costBasis += ((AccountStockOption) aa).getSharePremium() * ((AccountStockOption) aa).getShareLimit();
								 
							// For a Long Call	 
							 } else if (((Stock) asset).getSharePrice() < ((AccountStockOption)aa).getShareStrikePrice()) {
								 totalValue += 0;
								 costBasis += ((AccountStockOption) aa).getSharePremium() * ((AccountStockOption) aa).getShareLimit();
							 }
							 
						// For a put option
						 } else if(((AccountStockOption) aa).getOptionType().contentEquals("SP")) {
							 // For a Long Put
							 if(((Stock) asset).getSharePrice() <= ((AccountStockOption) aa).getShareStrikePrice()) {
								 totalValue += ((AccountStockOption) aa).getSharePremium() * ((AccountStockOption) aa).getShareLimit();
								 costBasis += 0;
								 
							 // For a Short Put
							 } else if(((Stock) asset).getSharePrice() > ((AccountStockOption)aa).getShareStrikePrice()) {
								 totalValue += ((((AccountStockOption) aa).getShareStrikePrice() - ((Stock) asset).getSharePrice()) * ((AccountStockOption) aa).getShareLimit()) + ((AccountStockOption) aa).getSharePremium() * ((AccountStockOption) aa).getShareLimit();

							 }
						 }
					}
				}
			}
		}
		
		//Totaling the fees with discounts, total return, and return %
		if(a.getAccountType().contentEquals("P")) {
			totalFees -= totalFees * 0.25;
		}
		
		totalReturn = totalValue - costBasis;
		totalReturnPercent = ((totalValue/costBasis)*100) - 100;
		
		// Creating a List, adding the correct variables and sending it back
		List<Double> totalList = new ArrayList<>();
		totalList.add(totalValue);
		totalList.add(costBasis);
		totalList.add(totalFees);
		totalList.add(totalReturn);
		totalList.add(totalReturnPercent);
		
		return totalList;
		
	}
	
	
	
	
}
