public class VideoRecording extends Recording{//Child class of Recording

	//Constructor - uses super()
	VideoRecording() {
		super();

	}

	//Parameterized Constructor - uses super() as well
	VideoRecording(String anArtist, String aName, int aDurationInSeconds, double newFramerate) {
		super(anArtist, aName, aDurationInSeconds,newFramerate);
	}
	
	@Override
	public String type() {
		return "V";
	}
	
	public String toString() {
		//Uses super.toString() to use the same toString as in the parent class
		return super.toString() + "[VIDEO | framerate: " + super.getRate() + " fps]";
	}
}
