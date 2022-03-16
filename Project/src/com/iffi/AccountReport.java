package com.iffi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class handles the creation and printing of the Account Owner Summary Report and the 
 * Account SUmmary report by Owner
 * 
 * @author maxpk
 *
 */
public class AccountReport {

	/**
	 * This method is used to output the account owner summary to the standard output as well as 
	 * the output.txt file
	 * @param accountMap
	 * @param personList
	 */
	public static void accountOwnerSummaryReport(HashMap<String, Account> accountMap, List<Person> personList, File outputFile) {
		
		try {
		PrintWriter pw = new PrintWriter(outputFile);
		
		System.out.println("Account Summary Report by Owner ");
		System.out.println("==============================================================================================================");
		System.out.println("Account    Owner                Manager                       Fees          Return         Ret%          Value");
		System.out.println("--------------------------------------------------------------------------------------------------------------");
		
		pw.println("Account Summary Report by Owner ");
		pw.println("==============================================================================================================");
		pw.println("Account    Owner                Manager                       Fees          Return         Ret%          Value");
		pw.println("--------------------------------------------------------------------------------------------------------------");
		
		double totalValue = 0.0, totalFees = 0.0, totalReturn = 0.0, totalReturnPercent = 0.0;
		
		for(Account a : accountMap.values()) {
			if (accountMap.containsKey(a.getAccountCode())) {
				String key = a.getAccountCode();
				Account tempAccount = accountMap.get(key);
				
				//Getting the individual code for a person/manager/beneficiary
				String personCode = a.getPersonCode();
				String managerCode = a.getManagerCode();
				
				// Creating a Person which holds the values temporarily, and then prints the according info
				Person owner = a.getPerson(personCode, personList);
				Person manager = a.getPerson(managerCode, personList);
				
				String format = "%-10s%-20s%-25s%-7s%6.2f%-4s%10.2f%-4s%6.2f%-4s%8.2f\n";
				List<Double> totals = AccountAsset.getTotalsForAccountDetails(a);
				
				System.out.printf(format, tempAccount.getAccountCode(), owner.getFirstName() + ", " + owner.getLastName(),
						manager.getFirstName() + ", " + manager.getLastName(), "$      ", totals.get(2), "   $   ", 
						totals.get(3),"  %  ", totals.get(4), "   $ ", totals.get(0));
				
				pw.printf(format, tempAccount.getAccountCode(), owner.getFirstName() + ", " + owner.getLastName(),
						manager.getFirstName() + ", " + manager.getLastName(), "$      ", totals.get(2), "   $   ", 
						totals.get(3),"  %  ", totals.get(4), "   $ ", totals.get(0));
				
				totalReturn += totals.get(3);
				totalFees += totals.get(2);
				totalValue += totals.get(0);
				totalReturnPercent += totals.get(4);
			}
		}

		System.out.println("                                                       --------------------------------------------------------");
		pw.println("                                                       --------------------------------------------------------");
		String format = "%56s%12.2f%-4s%13.2f%-3s%8.2f%-4s%11.2f";
		System.out.printf(format, "Totals $", totalFees, "   $", totalReturn, "  %", totalReturnPercent, "   $", totalValue);
		pw.printf(format, "Totals $", totalFees, "   $", totalReturn, "  %", totalReturnPercent, "   $", totalValue);
		System.out.println("\n\n");
		pw.println("\n\n");
		
		pw.close();
		
	} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * This method outputs the Account Details based on the owner to the standard output, as well as outputting the same
	 * information into the output.txt file
	 * @param accountMap
	 * @param personList
	 * @param outputFile
	 */
	public static void accountDetailsByOwner(HashMap<String, Account> accountMap, List<Person> personList, File outputFile) {
		
		FileWriter fw;
		try {
			fw = new FileWriter(outputFile, true);
		
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		
		System.out.println("Account Summary Report by Owner ");
		pw.println("Account Summary Report by Owner ");
		System.out.println("==============================================================================================================");
		pw.println("==============================================================================================================");
		
		for(Account a : accountMap.values()) {
			
			// This chunk of code just prints out the account type, code, and formatting
			System.out.println("=======================================");
			pw.println("=======================================");
			
			if(a.accountType.contentEquals("P")) {
				System.out.println("||        Pro Account: " + a.getAccountCode() + "         ||");
				pw.println("||        Pro Account: " + a.getAccountCode() + "         ||");
				
			} else if(a.accountType.contentEquals("N")) {
				System.out.println("||       Noob Account: " + a.getAccountCode() + "         ||");
				pw.println("||       Noob Account: " + a.getAccountCode() + "         ||");
				
			}
			
			System.out.println("=======================================");
			pw.println("=======================================");
			
			// This chunk of code prints out the Owner, Manager, and Beneficiary of the account
			String personCode = a.getPersonCode();
			String managerCode = a.getManagerCode();
			String beneficiaryCode = a.getBeneficiaryCode();
			Person owner = a.getPerson(personCode, personList);
			Person manager = a.getPerson(managerCode, personList);
			Person beneficiary = a.getPerson(beneficiaryCode, personList);
			String format = "%-25s\n%-50s\n%-20s\n";
			
			System.out.println("+-----------+");
			System.out.println("|   Owner   |");
			System.out.println("+-----------+");
			pw.println("+-----------+");
			pw.println("|   Owner   |");
			pw.println("+-----------+");
			
			System.out.printf(format, owner.getPersonName(), owner.getEmails(), owner.splitAddressString());
			pw.printf(format, owner.getPersonName(), owner.getEmails(), owner.splitAddressString());
			
			System.out.println("+-------------+");
			System.out.println("|   Manager   |");
			System.out.println("+-------------+");
			pw.println("+-------------+");
			pw.println("|   Manager   |");
			pw.println("+-------------+");
			
			System.out.printf(format, manager.getPersonName(), manager.getEmails(), manager.splitAddressString());
			pw.printf(format, manager.getPersonName(), manager.getEmails(), manager.splitAddressString());
			
			System.out.println("+---------------+");
			System.out.println("|  Beneficiary  |");
			System.out.println("+---------------+");
			pw.println("+---------------+");
			pw.println("|  Beneficiary  |");
			pw.println("+---------------+");
			
			if(beneficiary != null) {
				System.out.printf(format, beneficiary.getPersonName(), beneficiary.getEmails(), beneficiary.splitAddressString());
				pw.printf(format, beneficiary.getPersonName(), beneficiary.getEmails(), beneficiary.splitAddressString());
			} else {
				System.out.println("No Beneficiary");
				pw.println("No Beneficiary");
			}
			
			
			// This chunk of code prints out the assets, as well as the totals for each account
			System.out.println("+----------------+");
			System.out.println("|     Assets     |");
			System.out.println("+----------------+");
			pw.println("+----------------+");
			pw.println("|     Assets     |");
			pw.println("+----------------+");
			
			for(AccountAsset aa : a.assetList) {
				if(aa.assetType.contains("P")) {
					String pString = aa.getPropertyOutputString(aa.getAssetCode(), ((AccountProperty) aa));
					System.out.println(pString);
					pw.println(pString);
					
				} else if (aa.assetType.contains("C")) {
					String cString = aa.getCryptoOutputString(aa.getAssetCode(), ((AccountCrypto) aa));
					System.out.println(cString);
					pw.println(cString);
					
				} else if (aa.assetType.contains("S")) {
					if(aa instanceof AccountStockOption && ((AccountStockOption) aa).optionType.contains("SP")) {
						String putString = aa.getPutOptionOutputString(aa.getAssetCode(), ((AccountStockOption) aa));
						System.out.println(putString);
						pw.println(putString);
						
					} else if(aa instanceof AccountStockOption && ((AccountStockOption) aa).optionType.contains("SC")) {
						String callString = aa.getCallOptionOutputString(aa.getAssetCode(), ((AccountStockOption) aa));
						System.out.println(callString);
						pw.println(callString);
						
					} else if(aa instanceof AccountStock) {
						String sString = aa.getStockOutputString(aa.getAssetCode(), ((AccountStock) aa));
						System.out.println(sString);
						pw.println(sString);
					}
					
				} 
			}

			System.out.println("+---------------+");
			System.out.println("|    Totals     |");
			System.out.println("+---------------+");
			pw.println("+---------------+");
			pw.println("|    Totals     |");
			pw.println("+---------------+");

			// FOR EACH ACCOUNT GET THE TOTALS OF THE ASSET LIST -- call a function in the account maybe? this can also be used for AccountOwnerSummary
			List<Double> totals = AccountAsset.getTotalsForAccountDetails(a);
			String format1 = "%-26s%12.2f\n";
			System.out.printf(format1, "Total Value:         $    ", totals.get(0));
			pw.printf(format1, "Total Value:         $    ", totals.get(0));
			System.out.printf(format1, "Cost Basis:          $    ", totals.get(1));
			pw.printf(format1, "Cost Basis:          $    ", totals.get(1));
			System.out.printf(format1, "Total Account Fees:  $    ", totals.get(2));
			pw.printf(format1, "Total Account Fees:  $    ", totals.get(2));
			System.out.printf(format1, "Total Return:        $    ", totals.get(3));
			pw.printf(format1, "Total Return:        $    ", totals.get(3));
			System.out.printf(format1, "Total Return %:      $    ", totals.get(4));
			pw.printf(format1, "Total Return %:      $    ", totals.get(4));
			
			System.out.println("\n");
			pw.println("\n");
			
			}
		
			pw.close();
			bw.close();
			fw.close();
			
			} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void main(String[] args) {
		
		HashMap<String, Account> accountMap = new HashMap<String, Account>();
		File outputFile = new File("data/output.txt");
		
		// Need both the Asset and Person file to create accounts
		List<Asset> assetList = new ArrayList<Asset>();
		assetList = DataParser.loadAssetFile();
		List<Person> personList = new ArrayList<Person>();
		personList = DataParser.loadPersonFile();
		
		
		accountMap = DataParser.loadAccountFile(assetList);
		
		accountOwnerSummaryReport(accountMap, personList, outputFile);
		accountDetailsByOwner(accountMap, personList, outputFile);
		

	}

}
