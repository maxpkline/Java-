package com.iffi;

public class Address {

	private final String street;
	private final String city;
	private final String state;
	private final String zipCode;
	private final String country;
	
	public Address(String street, String city, String state, String zipCode, String country) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public String getCountry() {
		return country;
	}

	@Override
	public String toString() {
		return street + ", " + city + ", " + state + ", " + zipCode;
	}
	
	public String splitAddress() {
		return street + " \n" + city + ", " + state + " " + zipCode;
	}
	
	
	
	
	
}
