import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Playlist extends Unplayable {
	// Attributes
	Unplayable unpl = new Unplayable("This is song is not playable.");
	protected int countingSongs = 0;
	private String name;
	// Total duration of all recordings in the playlist
	private int durationInSeconds = 0;
	int playlistSize = 0;
	ArrayList<Recording> recordingList;

	// Create non-default non-parameterized constructor
	public Playlist() {
		this.name = "Unknown";
		recordingList = new ArrayList<Recording>();

	}

	// Create non-default parameterized constructor
	// Do we even need this constructor? --> Will figure it out later
	public Playlist(String aName) {
		// Check if the arguments' values are valid
		if (aName != null && playlistSize >= 0 /* && aRecordingList != null */) {
			setName(aName);
			recordingList = new ArrayList<Recording>();
		} else {
			this.name = "Unknown";
			recordingList = new ArrayList<Recording>();
		}
	}

	// Get method that returns name
	public String getName() {
		return this.name;
	}

	// Get method that returns the number of recordings
	public int getNumberOfRecordings() {
		return this.playlistSize;
	}

	// Get method that returns duration of the recording in seconds
	public int getDurationInSeconds() {
		return this.durationInSeconds;
	}

	// Get method that returns the max playlist size
	public int getPlaylistSize() {
		return this.playlistSize;
	}

	// Get method that returns
	public ArrayList<Recording> getRecordingList() {
		return this.recordingList;
	}

	// Set the name, if it is null, then set it to unknown
	public void setName(String aName) {
		if (aName != null) {
			this.name = aName;
		} else {
			this.name = "Unknown";
		}
	}

	// add a new audio recording object to the ArrayList
	public boolean add(Recording aRecording) {
		int determine = 0;
		if (aRecording != null) {
			if (playlistSize == 0) {
				recordingList.add(aRecording);
				determine = 1;
				playlistSize++;
			} else {
				int number = 0;
				for (int i = 0; i < playlistSize; i++) {

					if (aRecording.getName().equals(recordingList.get(i).getName())
							&& aRecording.getArtist() == recordingList.get(i).getArtist()) {
						System.out.println("The song is already in the playlist.");
						determine = 0;
						number = 1;
					}
				}
				if (number == 0) {
					recordingList.add(aRecording);
					determine = 1;
					playlistSize++;
				} else
					determine = 0;

			}
		}
		if (determine == 0) {
			return false;
		} else {
			return true;
		}
	}

	// add a new video recording object to the ArrayList
	public boolean add(String aName) {
		int determine = 0;
		try {
			File input = new File(aName);
			Scanner scanner = new Scanner(input);
			int size = 0;
			while (scanner.hasNextLine()) {
				size++;
				String garbage = scanner.nextLine();
			}

			scanner.close();

			Scanner aReader = new Scanner(input);

			String[][] splitter;
			splitter = new String[size][5];
			String newLine = "";
			int f = 0;
			while (aReader.hasNextLine()) {

				newLine = aReader.nextLine();
				splitter[f] = newLine.split(",");
				f++;

			}
			aReader.close();

			for (int i = 0; i < size; i++) {
				if (splitter[i][0].equals("A")) {
					int gae = Integer.parseInt(splitter[i][3]);
					double gae2 = Double.parseDouble(splitter[i][4]);
					if (this.add(new AudioRecording(splitter[i][2], splitter[i][1], gae, gae2)))
						determine = 1;
					else
						determine = 0;


				} else if (splitter[i][0].equals("V")) {
					int gae = Integer.parseInt(splitter[i][3]);
					double gae2 = Double.parseDouble(splitter[i][4]);

					if (this.add(new VideoRecording(splitter[i][2], splitter[i][1], gae, gae2)))
						determine = 1;
					else
						determine = 0;



				} else {
					System.out.println("Object not created... GGs I guess");
				}
			}
		} catch (FileNotFoundException fnfe) {
			System.out.println("File Not Found!");
		}

		if (determine == 0) {
			return false;
		} else {
			return true;
		}
	}

	// Adding songs from another user's playlist
	public boolean add(Playlist otherPlaylist) {
		int determine = 0;
		if (otherPlaylist != null) {
			for (int z = 0; z < otherPlaylist.recordingList.size(); z++) {
//				System.out.println("Something: " + otherPlaylist.recordingList.get(z));
				if (recordingList.add(otherPlaylist.recordingList.get(z))) {
//					System.out.println(this.recordingList.get(0));
					determine = 1;
				}
				else
					determine = 0;
			}
		}
		if (determine == 0) {
			return false;
		} else {
			return true;
		}
	}

	// Remove a song by index from recordingList
	public boolean remove(int index) {
		if (index >= 0 && index < playlistSize) {
			recordingList.remove(index);
			playlistSize--;
			return true;
		} else
			return false;
	}

	// Remove a song by name from recordingList
	public boolean remove(String name) {
		int determine = 0;
		for (int i = 0; i < playlistSize; i++) {
			if (recordingList.get(i).getName().equals(name)) {
				recordingList.remove(i);
				playlistSize--;
				determine = 1;
			} else
				determine = 0;
		}
		if (determine == 0)
			return false;
		else
			return true;
	}

	// Play by index
	public void play(int playlistIndex) {
		if (recordingList != null && playlistIndex < playlistSize) {
			recordingList.get(playlistIndex).play();
			countingSongs++;
		} else {
			System.out.println("There is an error with the index or there are no recordings.");
		}

	}

	// Play by name
	public void play(String nameOfSong) {
		int keeper = 0;

		if (recordingList != null) {
			for (int i = 0; i < playlistSize; i++) {
				if (recordingList.get(i).getName().equals(nameOfSong)) {
					recordingList.get(i).play();
					countingSongs++;
					keeper = 1;
				}
			}

			if (keeper == 0) {
				System.out.println("There is an error with the name or there are no recordings.");
			}
		}
	}

	// Uses the play method from the recording class to display what song is
	// playing. Give an error if the recording is null.
	public void play() {
		if (recordingList != null) {
			for (int i = 0; i < playlistSize; i++) {
				if (recordingList.get(i) != null) {
					recordingList.get(i).play();
					countingSongs++;
				} else {
					System.out.println("Error! Missing song or empty playlist!");
				}
			}
		}

	}

	// To string that prints out the total duration of the playlist as well as the
	// summary of the songs played.

	protected void shuffle() {
		ArrayList<Recording> copy = new ArrayList<Recording>();
		for (int i = 0; i < recordingList.size(); i++) {
			copy.add(recordingList.get(i));
		}

		int k = 1;
		Random random = new Random();

		for (int i = 0; i < playlistSize; i++) {
			if (k != 0)
				k = random.nextInt(copy.size() - 1);
			else
				k = 0;
			copy.get(k).play();
			copy.remove(k);
		}
	}

	// Save to file
	public void save(String nameOfFile) {
		try {
			FileOutputStream fos = new FileOutputStream(nameOfFile, false);
			PrintWriter pw = new PrintWriter(fos);

			String[][] saver;
			String type;
			saver = new String[playlistSize][5];

			for (int i = 0; i < playlistSize; i++) {
				if (recordingList.get(i).type().equals("V"))
					saver[i][0] = "V";
				else
					saver[i][0] = "A";
				saver[i][1] = recordingList.get(i).getName();
				saver[i][2] = recordingList.get(i).getArtist();
				saver[i][3] = String.valueOf(recordingList.get(i).getDurationInSeconds());
				saver[i][4] = Double.toString(recordingList.get(i).getRate());
			}
		} catch (Exception e) {
			System.out.println("File wasn't outputted.");
		}
	}

	public void statistics() {
		for (int i = 0; i < playlistSize; i++) {
			System.out.println("ARTIST: " + recordingList.get(i).getArtist() + " - " + "NAME: "
					+ recordingList.get(i).getName() + " - " + "Number of plays: " + countingSongs);
		}
	}

	public String toString() {
		String string1 = "";
		String string2 = "";
		for (int i = 0; i < playlistSize; i++) {
			if (recordingList.get(i) != null) {
				durationInSeconds = durationInSeconds + recordingList.get(i).getDurationInSeconds();
			}
		}

		string1 = "\nPlaylist: " + name + "[" + durationInSeconds / 60 + "m" + durationInSeconds % 60 + "s]:\n";
		for (int i = 0; i < playlistSize; i++) {
			string2 = string2 + recordingList.get(i) + "\n";
		}

		return string1 + string2;
	}
}
