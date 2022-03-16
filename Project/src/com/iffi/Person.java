package com.iffi;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Person")
public class Person {

	public String personCode;
	public String lastName;
	public String firstName;
	public Address address;
	public List<String> emails;
	

	public Person(String personCode, String lastName, String firstName, Address address, List<String> emails) {
		super();
		this.personCode = personCode;
		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
		this.emails = emails;
	}
	
	public Person() {
		
	}
	
	public Person(String[] input) {
		this.personCode = input[0];
		this.lastName = input[1];
		this.firstName = input[2];
		this.address = new Address(input[3], input[4], input[5], input[6], input[7]);
		
		if(input.length >= 8) {
			for (int i = 8; i<=input.length; i++) {
				if(input[i] != null) {
					this.emails.add(input[i]);
				}
			}
		}
	}

	public String getPersonCode() {
		return personCode;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public Address getAddress() {
		return address;
	}

	public List<String> getEmails() {
		return emails;
	}
	
	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	@Override
	public String toString() {
		return personCode + ", " + firstName + ", " + lastName + ", " + address.toString() + ", " + emails;
	}

	// This getter just helps when trying to get the name of the person 
	public String getPersonName() {
		return firstName + ", " + lastName;
		
	}

	public String splitAddressString() {
		return address.splitAddress();
	}
	
	
	
	
	
}
