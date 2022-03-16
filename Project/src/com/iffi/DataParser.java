package com.iffi;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class DataParser {

public static final String SERIALIZED_FILE_NAME = "Persons.xml";
	
	/**
	 * This method is called from the main of the DataConverter class, searches for a file to scan, and if there is one, the 
	 * method parses the given CSV Data into an ArrayList<Person>, accounting for the correct 
	 * variables (i.e. a person can have any number of emails), and then returns the List containing
	 * the data of the persons in the CSV file.
	 * @return
	 */
	public static List<Person> loadPersonFile() {
		
		List<Person> personList = new ArrayList<Person>();
		Scanner sc = null;
		
		try {
			sc = new Scanner(new File("data/Persons.csv"));
			sc.nextLine();
			
			while (sc.hasNext()) {
				Person person = null;
				Address address = null;
				ArrayList<String> emails = new ArrayList<String>();
				
				String line = sc.nextLine();
				String tokens[] = line.split(",");
				
				if(tokens.length != 0) { 
				String code = tokens[0];
				String lastName = tokens[1];
				String firstName = tokens[2];
				String street = tokens[3];
				String city = tokens[4];
				String state = tokens[5];
				String zipCode = tokens[6];
				String country = tokens[7];
				
				if (tokens.length > 7) {
					int i = 8;
					while (i < tokens.length) {
						emails.add(tokens[i]);
						i++;
					}
				}
				
				address = new Address(street, city, state, zipCode, country);
				person = new Person(code, lastName, firstName, address, emails);

				personList.add(person);
				}
				
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		return personList;
		}
	
	
	/**
	 * This method is called from the main of the DataConverter class, reads in a CSV file if there is one, and then 
	 * iterates over each line, correctly sorting the values to their place in the Asset class,
	 * creating an asset with the type of asset that is passed (property, stock or crypto). This
	 * asset is added to a list of assets which is then returned. 
	 * @return
	 */
	public static List<Asset> loadAssetFile() {
		
		List<Asset> assetList = new ArrayList<Asset>();
		Scanner sc = null;
		
		try {
			sc = new Scanner(new File("data/Assets.csv"));
			sc.nextLine();
			
			while (sc.hasNext()) {
				Asset asset = null;
				String line = sc.nextLine();
				String tokens[] = line.split(",");
				
				String code = tokens[0];
				String type = tokens[1];
				String label = tokens[2];
				
				//Checking to see what type and sorting accordingly
				if (tokens[1].equals("P")) {
					double appraisedValue = Double.parseDouble(tokens[3]);
					asset = new Property(code, type, label, appraisedValue);
					
				} else if (tokens[1].equals("S")) {
					String symbol = tokens[3];
					double sharePrice = Double.parseDouble(tokens[4]);
					asset = new Stock(code, type, label, symbol, sharePrice);
					
				} else if (tokens[1].equals("C")) {
					double exchangeRate = Double.parseDouble(tokens[3]);
					double exchangeFeeRate = Double.parseDouble(tokens[4]);
					asset = new Crypto(code, type, label, exchangeRate, exchangeFeeRate);
				}
				
				assetList.add(asset);
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		return assetList;
		
	}
	
	
	/**
	 * This method checks the Asset code from the Accounts file with the list of Assets in the asset
	 * file, and returns the type of the Asset, which will be used to create the different assets
	 * @param assetCode
	 * @param assetList
	 * @return
	 */
	public static String checkAssetMatch(String assetCode, List<Asset> assetList) {
		String match = null;
		
		for (Asset a : assetList) {
			if(assetCode.equals(a.getAssetCode())) {
				match = a.getAssetType();
				return match;	
			} 
		}
		
		return null;

	}
	
	
	/** 
	 * This method is used to split up the Account creation into two methods for cleanliness.
	 * This method takes the specified line in the file, and then creates the account asset list
	 * based on what was in the line passed. This list is then passed back to the LoadAccountFile
	 * method, and the account creation is completed 
	 * @param line
	 * @param assetList
	 * @return
	 */
	public static List<AccountAsset> createAccountAssetList(String line, List<Asset> assetList) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<AccountAsset> accountAssetList = new ArrayList<AccountAsset>();
		AccountAsset accountAsset = null;
		String tokens[] = line.split(",");
		String tempAssetType = "";
		int index = 5;
		
		
		while(index < tokens.length) {
			
			if(tokens[index] != null) {
				String tempAssetCode = tokens[index];
				
				if(checkAssetMatch(tempAssetCode, assetList) != null) {
					tempAssetType = checkAssetMatch(tempAssetCode, assetList);
				}
				else if (checkAssetMatch(tempAssetCode, assetList) == null) {
					tempAssetType = "NONE";
				}
			}
			
			if(tempAssetType.equals("NONE")) {
				index += 6;
			} else if (tempAssetType.equals("P")) {
				accountAsset = new AccountProperty(tokens[0], tokens[index], tempAssetType, LocalDate.parse(tokens[index+1], formatter), Double.parseDouble(tokens[index+2]));
				index += 3;
			} else if(tempAssetType.equals("C")) {
				accountAsset = new AccountCrypto(tokens[0], tokens[index], tempAssetType, LocalDate.parse(tokens[index+1], formatter), Double.parseDouble(tokens[index+2]), Double.parseDouble(tokens[index+3]));
				index += 4;
			} else if (tempAssetType.equals("S")) {
				if(tokens[index+1].equals("S")) {
					accountAsset = new AccountStock(tokens[0], tokens[index], tempAssetType, LocalDate.parse(tokens[index+2], formatter), Double.parseDouble(tokens[index+3]), Double.parseDouble(tokens[index+4]), Double.parseDouble(tokens[index+5]));
					index += 6;
				} else if (tokens[index+1].equals("P")) {
					accountAsset = new AccountStockOption(tokens[0], tokens[index], tempAssetType, "SP", LocalDate.parse(tokens[index+2], formatter), Double.parseDouble(tokens[index+3]), Integer.parseInt(tokens[index+4]), Double.parseDouble(tokens[index+5]), LocalDate.parse(tokens[index+6], formatter));
					index+=7;
				} else if(tokens[index+1].equals("C")) {
					accountAsset = new AccountStockOption(tokens[0], tokens[index], tempAssetType, "SC", LocalDate.parse(tokens[index+2], formatter), Double.parseDouble(tokens[index+3]), Integer.parseInt(tokens[index+4]), Double.parseDouble(tokens[index+5]), LocalDate.parse(tokens[index+6], formatter));
					index += 7;
				} else {
					index+= 3;
				}
			}	
			
			accountAssetList.add(accountAsset);
			
		}
		
		return accountAssetList;
		
	}
	
	
	
	/** 
	 * This method reads in the input from the Accounts.csv file, separates the information into 
	 * a List of assets (from the createAccountAssetList Method), as well account information, and 
	 * then combines the two into an Account object. Once the Account object has been created, 
	 * the account is added to a HashMap where the key is the Person code, and the value passed is 
	 * an Account, with every code, and asset passed. 
	 * @return
	 */
	public static HashMap<String, Account> loadAccountFile(List<Asset> assetList) {
		HashMap<String, Account> accountMap = new HashMap<String, Account>();
		Scanner sc = null;
		int i = 0;
		
		try {
			sc = new Scanner(new File("data/Accounts.csv"));
			 int numAccounts = Integer.parseInt(sc.nextLine());

		 // We somewhat need to check the number of lines (due to garbage in Accounts.csv, so we iterate i
			while (sc.hasNext() && i<numAccounts) {
			// We need an account to hold the account information, as well as a list of assets 
				Account account = null;
				AccountAsset accountAsset = null;
				List<AccountAsset> accountAssetList = new ArrayList<AccountAsset>();
				
				String line = sc.nextLine();
				String tokens[] = line.split(",");
				
				String accountCode = tokens[0];
				String accountType = tokens[1];
				String personCode = tokens[2];
				String managerCode = tokens[3];
				String beneficiaryCode = tokens[4];
				
				// Checking for the first asset and handling according
				if(tokens[5] != null) {
					String tempAssetCode = tokens[5];
					
					if(checkAssetMatch(tempAssetCode, assetList) != null) {
						accountAssetList = createAccountAssetList(line, assetList);
					} else {
		
					}
				}
				account = new Account(accountCode, accountType, personCode, managerCode, beneficiaryCode, accountAssetList);				
				accountMap.put(accountCode, account);
				i++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return accountMap;
		
	}
}
