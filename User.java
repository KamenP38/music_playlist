
public class User implements Playable{
	private static int userIDCount = 0;
	private int userID;
	private Playlist aPlaylist;
	private String name;
	
	User(String aName, String nameOfPlaylist){
		userIDCount++;
		userID = userIDCount;
		setName(aName);
		aPlaylist = new Playlist(nameOfPlaylist);
	}
	
	public void setName(String nameOfUser) {
		if(nameOfUser != null)
			name = nameOfUser;
		else 
			name = "Unknown";
	}
	
	public String getName() {
		return name;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public Playlist getPlaylist() {
		return aPlaylist;
	}

	@Override
	public void play() {
		aPlaylist.play();
	}
	
	public void play(int playlistIndex) {
		aPlaylist.play(playlistIndex);
	}
	
	public void play(String nameOfSong) {
		aPlaylist.play(nameOfSong);
	}
}
