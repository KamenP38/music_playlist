
public class AudioRecording extends Recording {
	//Child class of Recording

	//Constructor - uses super() 
	AudioRecording() {
		super();
	}

	//Parameterized Constructor - uses super() as well
	AudioRecording(String anArtist, String aName, int aDurationInSeconds, double newBitrate) {
		super(anArtist, aName, aDurationInSeconds, newBitrate);
	}
	
	@Override
	public String type() {
		return "A";
	}

	@Override
	//Uses super.toString() to use the same toString as in the parent class
	public String toString() {
		return super.toString() + "[AUDIO | bitrate:  " + super.getRate()  + " kbps]";
	}
}
