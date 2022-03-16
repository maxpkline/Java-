package com.iffi;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class DataConverter {
	
	/**
	 * This method takes in a List of the type Person which has been created using 
	 * the loadPersonFile() method, serializes the Person objects into XML Objects, 
	 * and finally prints the XML objects to a file named Persons.xml
	 * @param personList
	 */
	public static void personsToXML(List<Person> personList) {
		
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("Person", Person.class);
		
		try {
			PrintWriter pw = new PrintWriter("data/Persons.xml");
			pw.println(xstream.toXML(personList));			
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This method takes in a List of the type Assets which has been created using 
	 * the loadAssetFile() method, serializes the Assets objects into XML Objects, 
	 * and finally prints the XML objects to a file named Assets.xml
	 * @param assetList
	 */
	public static void assetsToXML(List<Asset> assetList) {
		
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("Property", Property.class);
		xstream.alias("Stock", Stock.class);
		xstream.alias("Cryptocurrency", Crypto.class);
		
		try {
			PrintWriter pw = new PrintWriter("data/Assets.xml");
			pw.println(xstream.toXML(assetList));			
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		
		// This calls the LoadPersonFile method, and returns it to a list of persons, i.e: personList
		List<Person> personList = new ArrayList<Person>();
		personList = DataParser.loadPersonFile();
		
		// This calls the personsToXML method, and a file named Persons.xml is created
		personsToXML(personList);
		
		
		
		
		
		// TODO: write this into the output file/standard output file
		for(Person p : personList) {
			System.out.println(p.toString());
		}
		
		
		
		
		
		
		// This calls the LoadAssetFile method, and returns it to a list of assets, i.e: assetList
		List<Asset> assetList = new ArrayList<Asset>();
		assetList = DataParser.loadAssetFile();
		
		// This calls the assetsToXML method, and a file named Assets.xml is created
		assetsToXML(assetList);
		
		
		
		
		// TODO: write this into the output file/standard output file
		for(Asset a : assetList) {
			System.out.println(a.toString());
		}
		
		
		
		
		
		
		
		

	}

}
