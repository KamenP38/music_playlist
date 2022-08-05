public abstract class Recording implements Playable{
	// Declare attributes
	protected final String ARTIST;
	protected final String NAME;
	protected final double rate;
	protected final int DURATION_IN_SECONDS;

	// Create non-default non-parameterized constructor
	public Recording() {
		this.ARTIST = "Unknown";
		this.NAME = "Unknown";
		this.rate = 0;
		this.DURATION_IN_SECONDS = 0;
	}

	// Create non-default parameterized constructor
	public Recording(String anArtist, String aName, int aDurationInSeconds, double theRate) {
		// Check if the arguments are valid inputs for the attributes
		if (anArtist != null && aName != null && aDurationInSeconds >= 0 && theRate > 0) {
			this.ARTIST = anArtist;
			this.NAME = aName;
			this.DURATION_IN_SECONDS = aDurationInSeconds;
			this.rate = theRate;
		} else {
			this.ARTIST = "Unknown";
			this.NAME = "Unknown";
			this.DURATION_IN_SECONDS = 0;
			this.rate = 0;
		}
	}

	// Get method - returns the value of ARTIST
	protected String getArtist() {
		return this.ARTIST;
	}

	// Get method - returns the value of NAME
	protected String getName() {
		return this.NAME;
	}
	
	protected double getRate() {
		return rate;
	}

	// Get method - returns the value of Duration of the recording in seconds
	protected int getDurationInSeconds() {
		return this.DURATION_IN_SECONDS;
	}
	

	// This method prints out a message and turns the duration of the recording from
	// seconds into minutes and seconds
	public void play() throws Unplayable{
		// Check if duration is not equal to zero
		if (DURATION_IN_SECONDS != 0) {
			System.out.println("Now Playing: " + ARTIST + " - " + NAME + "[" + DURATION_IN_SECONDS / 60 + "m"
					+ DURATION_IN_SECONDS % 60 + "s]");
		} else {
			throw new Unplayable("The song cannot be played.");
		}

	}

	// toString method that returns ARTIS - NAME [Duration in minutes and seconds]
	public String toString() {
		return ARTIST + " - " + NAME + "[" + DURATION_IN_SECONDS / 60 + "m" + DURATION_IN_SECONDS % 60 + "s]";
	}

	protected abstract String type();

}