package com.iffi;

import java.util.List;

/**
 * This class is mainly used to hold the information of a specified account, as well as the 
 * assets which are associated with the account. This is in the form of a List<AccountAsset>
 * which helps to make sure that the account information, as well as the different assets can 
 * all be stored in a singular object
 * @author maxpk
 *
 */

public class Account {

	public String accountCode;
	public String accountType;
	public String personCode;  // this holds the account owner number which is a specific person code 
	public String managerCode;
	public String beneficiaryCode;
	public List<AccountAsset> assetList;
	
	public Account(String accountCode, String accountType, String personCode, String managerCode,
			String beneficiaryCode, List<AccountAsset> assetList) {
		super();
		this.accountCode = accountCode;
		this.accountType = accountType;
		this.personCode = personCode;
		this.managerCode = managerCode;
		this.beneficiaryCode = beneficiaryCode;
		this.assetList = assetList;
	}
	

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public String getManagerCode() {
		return managerCode;
	}

	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}

	public String getBeneficiaryCode() {
		return beneficiaryCode;
	}

	public void setBeneficiaryCode(String beneficiaryCode) {
		this.beneficiaryCode = beneficiaryCode;
	}


	public List<AccountAsset> getAssetList() {
		return assetList;
	}


	public void setAssetList(List<AccountAsset> assetList) {
		this.assetList = assetList;
	}
	
	
	/** 
	 * This method is used to get a person when given a specific code, and then passes the 
	 * created/checked person back. Only whilst making a report is this used
	 * @param personCode
	 * @param personList
	 * @return
	 */
	public Person getPerson(String personCode, List<Person> personList) {
		Person person = null;
		
		for(Person p : personList) {
			if (p.getPersonCode().equals(personCode)) {
				person = new Person(p.getPersonCode(), p.getLastName(), p.getFirstName(), p.getAddress(), p.getEmails());
			}
		}
		
		return person;
		
	}
	
}
