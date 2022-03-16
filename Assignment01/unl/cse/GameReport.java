

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class GameReport {
	
	
	/**
	 * This method takes in a list of game reports, then checks to see if the output map contains the key,
	 * if it is, and it has a game title, add the title to the count, and put the publisher as well as 
	 * their published games into a map, if not, both the game and publisher are put into the map. If anything
	 * else happens, the publisher and null are put into the Map. Returns the publisher map to the main function
	 * 
	 * @param inputList
	 * @return
	 */

	public static Map<String, Set<String>> publisherMapOutput(List<GameReportClass> inputList) {
		
		Set<String> gameCount = new HashSet<String>();
		Map<String, Set<String>> publisherMap = new LinkedHashMap<String, Set<String>>();
		
		// Sorting the list for the publisher games count
		Collections.sort(inputList, new PublisherCmp());
	
		for (GameReportClass report : inputList) {
			
			String publisher = report.getPublisher();
			
			if ((report.getGameTitle().length() > 0) && publisherMap.containsKey(publisher)) {
				gameCount.add(report.getGameTitle());
				publisherMap.put(publisher, gameCount);
				
			} else if (report.getGameTitle().length() > 0) {
					gameCount = new HashSet<String>();
					gameCount.add(report.getGameTitle());
					
					publisherMap.put(publisher, gameCount);
			
			} else if ((report.getGameTitle().length() > 3) && publisherMap.containsKey(publisher)) {
				
				gameCount.add(report.getGameTitle());
				publisherMap.put(publisher, gameCount);
					
				} else if ((report.getGameTitle().length() > 4) && publisherMap.containsKey(publisher)) {
					
					gameCount.add(report.getGameTitle());
					publisherMap.put(publisher, gameCount);
					
				}else {
					publisherMap.put(publisher, new HashSet<String>());
				}
			}
		
		return publisherMap;

	}
	
	public static void PublisherStandardOutput(Map<String, Set<String>> inputList) {
		System.out.println("       Publisher Game Counts:       ");
		System.out.println("-------------------------------------");

			for (String tokens : inputList.keySet()) {
				String format = "%-35s %-10s \n";
				int count = inputList.get(tokens).size();
				System.out.printf(format, tokens, count);
			}
	}
	
	/**
	 * This Method sorts the list of games, and then depending on which arguments are passed, the if-else
	 * statements figure out whether or not to iterate over how many platforms the game is on. Same idea as 
	 * the other method, just different variables to watch out for. Returns the results to the main function
	 * 
	 * @param inputList
	 * @return
	 */

	private static Map<String, Integer> gamesMapOutput(List<GameReportClass> inputList) {
		//Sorting the list for the game platform count
		Collections.sort(inputList, new PlatformCmp());
		
		Map<String, Integer> platformMap = new LinkedHashMap<String, Integer>();
		
		for (GameReportClass report : inputList) {
			String gameTitle = report.getGameTitle();
			
			if (gameTitle.length() > 0) {
				if (report.getPlatform() != null && platformMap.containsKey(gameTitle)) {
					platformMap.put(gameTitle, platformMap.get(gameTitle) + 1);
					
				} else if (report.getPlatform() != null) {
						platformMap.put(gameTitle, 1);
					
					} else if (report.getPlatform() == null) {
						platformMap.put(gameTitle, 0);
					
					} else {
						platformMap.put(gameTitle, 0);
					}
				}
			}
		
		return platformMap;
	}
	
	
	public static void GamePlatformStandardOutput(Map<String, Integer> inputList) {
		System.out.println("        Game Platform Counts:        ");
        System.out.println("-------------------------------------");
        
        for (String key: inputList.keySet()) {
      	  String format = "%-35s %-10s \n";
      	  int count = inputList.get(key);
      	  System.out.printf(format, key, count);
        }
	}

	
	
	public static void main(String[] args) {

		// This is just checking a file is passed, and setting the file name to be used in the try - catch
		if (args.length != 1) {
			throw new IllegalArgumentException("Please enter file name");
		}

		String fileName = args[0];

		
		/**
		 * Reading in the file line by line and taking the specific values into a list 
		 * of GameReportClass(es), then adding the game report to a list to be iterated over 
		 */
		try (Scanner s = new Scanner(new File(fileName))) {

			List<GameReportClass> inputList = new ArrayList<GameReportClass>();
			GameReportClass report = null;

			while (s.hasNextLine()) {
				String line = s.nextLine();
				String tokens[] = line.split(",|\\\\R");
				report = new GameReportClass();

				report.setGameTitle(tokens[0]);
				report.setPublisher(tokens[1]);

				if (tokens.length > 2) {
					report.setYearPublished(Integer.parseInt(tokens[2]));
					if(tokens.length > 3 && tokens[3] != null) {
						report.setPlatform(tokens[3]);
					}
				}
				inputList.add(report);

			}
			

			/**
			 * Calling the functions above and outputting the correct publisher with the
			 * correct count as well
			 */
			
			// for the publisher count
			Map<String, Set<String>> publisherMap = publisherMapOutput(inputList);
			
			PublisherStandardOutput(publisherMap);
	
			//Just to break up the reports to make them easier to see
			System.out.println("\n");

			
			// for the game platform count
			  Map<String, Integer> platformMap = gamesMapOutput(inputList);
			  GamePlatformStandardOutput(platformMap);
              
              
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
