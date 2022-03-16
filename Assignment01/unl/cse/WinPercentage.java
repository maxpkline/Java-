package cse;

public class WinPercentage {
	
	
	
	 /**
	  * Method which takes in the four computed P values, computes the Expected Win 
	  * Percentage and outputs that to a table 
	  * @param runsScored
	  * @param runsAgainst
	  * @param gamesWon
	  * @param pValue - p value is given/changed based on which iteration we are running
	  */
	public static void expectedWinPercentage(int runsScored, int runsAgainst, int gamesWon, double pValue) {
		String bodyFormat = "%-15f %-15.8f %-15.0f %-15.0f\n";
		
		double ewp = ((Math.pow(runsScored, pValue)/((Math.pow(runsScored, pValue) + Math.pow(runsAgainst, pValue)))));
		double predictedWins =  (162*ewp);
		System.out.printf(bodyFormat, pValue, ewp * 100, predictedWins, gamesWon - predictedWins);
	}
	
	public static double getP3Value (int runsScored, int runsAgainst) {
		double p3 = (Math.log10(((runsScored+runsAgainst)/162)))*1.5 + 0.45;
		return p3;
	}
	
	public static double getP4Value (int runsScored, int runsAgainst) {
		double p4 = Math.pow(((runsScored+runsAgainst)/162), 0.287);
		return p4;
	}
	
	
	public static void main(String[] args) {
		
		// Checking to see that the correct number of arguments have been passed
		if(args.length != 3) {
			throw new IllegalArgumentException("Please enter: the team's number of runs,"
					+ " runs scored against the team, and the number of games won.");
			
		}
		String titleFormat = "%-15s %-15s %-15s %-15s\n";
		System.out.printf(titleFormat, "p value", "Win Percentage", "Predicted Wins",  "Performance");
		System.out.println("============================================================");
		
		// Parsing the integers given from the command line into correct variables
		int runsScored = Integer.parseInt(args[0]);
		int runsAgainst = Integer.parseInt(args[1]);
		int gamesWon = Integer.parseInt(args[2]);
		
		int p1 = 2; 
		double p2 = 1.83;
		double p3 = 0.00;
		double p4 = 0.00;
		
		p3 = getP3Value(runsScored, runsAgainst);
		p4 = getP4Value(runsScored, runsAgainst);
		
		
		expectedWinPercentage(runsScored, runsAgainst, gamesWon, p1);
		expectedWinPercentage(runsScored, runsAgainst, gamesWon, p2);
		expectedWinPercentage(runsScored, runsAgainst, gamesWon, p3);
		expectedWinPercentage(runsScored, runsAgainst, gamesWon, p4);

		
	}

}
