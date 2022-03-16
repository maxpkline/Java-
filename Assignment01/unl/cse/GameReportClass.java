
public class GameReportClass {

	String gameTitle;
	String publisher;
	int yearPublished;
	String platform;
	
	
	public GameReportClass() {
		
	}
	public GameReportClass(String gameTitle, String publisher, int yearPublished, String platform) {
		super();
		this.gameTitle = gameTitle;
		this.publisher = publisher;
		this.yearPublished = yearPublished;
		this.platform = platform;
	}
	public String getGameTitle() {
		return gameTitle;
	}

	public void setGameTitle(String gameTitle) {
		this.gameTitle = gameTitle;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getYearPublished() {
		return yearPublished;
	}

	public void setYearPublished(int yearPublished) {
		this.yearPublished = yearPublished;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
	

}
