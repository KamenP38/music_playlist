import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class StreamingSystem {
	private ArrayList<User> dataBase = new ArrayList<User>();
	private int counter = 0;
	

	
	public boolean remove(String userName) {
		int determine = 0;
			for(int i = 0; i < dataBase.size(); i++) {
				if(dataBase.get(i).getName().equals(userName)) {
					dataBase.remove(i);
					determine = 1;
				} else
					determine = 0;
			
			}
			if(determine == 0)
				return false;
			else
				return true;
	}
	
	public boolean remove(int ID) {
		int determine = 0;
			for(int i = 0; i < dataBase.size(); i++) {
				if(dataBase.get(i).getUserID() == ID) {
					dataBase.remove(i);
					determine = 1;
				} else
					determine = 0;
			
			}
			if(determine == 0)
				return false;
			else
				return true;
	}
	
	
	

	

	public void run() throws InputMismatchException{

		Scanner scanner = new Scanner(System.in);
		String[] response = new String[20];
		String username;
		String nameOfPlaylist;
		String responseUser;
		System.out.println("Choose one of the menu options: ");
		System.out.println("[a] Add a user.");
		System.out.println("[b] Remove a user.");
		System.out.println("[c] List all users.");
		System.out.println("[d] User");
		System.out.println("[e] Exit");
		
		do {
		responseUser = scanner.nextLine();
		if (responseUser.equals("a")) {
			System.out.println("Create username: ");
			username = scanner.nextLine();
			System.out.println("Create a name for your playlist: ");
			nameOfPlaylist = scanner.nextLine();
			dataBase.add(new User(username, nameOfPlaylist));
			counter++;
			System.out.println("Your ID is: " + counter);
		} else if (responseUser.equals("b")) {
			System.out.println(
					"You can delete a user by their ID or username. Write either \\\"ID\\\" or \\\"username\\\" to choose which one you are going to use.");
			response[1] = scanner.nextLine();


			if (response[1].equals("ID")) {
				System.out.println("Give user ID: ");
				response[2] = scanner.nextLine();
					remove(Integer.parseInt(response[2]));
					counter--;
			} else {
				System.out.println("Give user's username: ");
				response[2] = scanner.nextLine();
					remove(response[2]);
					counter--;
			}
		} else if (responseUser.equals("c")) {
			for (User currentUser : dataBase) {
				System.out.println("Username: " + currentUser.getName() + ", ID: " + currentUser.getUserID()
						+ ", Playlist name: " + currentUser.getPlaylist().getName());
			}
		} else if(responseUser.equals("d")){

			
			System.out.println("Choose one of the following: ");
			System.out.println("[1] Add a recording.");
			System.out.println("[2] Add playlist from file.");
			System.out.println("[3] Add playlist from another user.");
			System.out.println("[4] Remove recording from playlist.");
			System.out.println("[5] Play individual recording.");
			System.out.println("[6] Play entire playlist once.");
			System.out.println("[7] Shuffle entire playlist once.");
			System.out.println("[8] Save playlist to a file.");
			System.out.println("[9] Display playlist stats.");
			System.out.println("[0] Exit");
			
			String str = "Choose one of the following: \n" + "[1] Add a recording.\n" +"[2] Add playlist from file.\n" + "[3] Add playlist from another user.\n" + "[4] Remove recording from playlist.\n" + "[5] Play individual recording.\n" + 
					"[6] Play entire playlist once.\n" + "[7] Shuffle entire playlist once.\n" + "[8] Save playlist to a file.\n" + "[9] Display playlist stats.\n" + "[0] Exit";

			response[4] = scanner.nextLine();
			if (response[4].equals("1")) {
				
				try{
				System.out.println(
						"Is it an audio or video recording? Write V for video recording or A for audio recording.");
				response[5] = scanner.nextLine();
				
				while(!"A".equals(response[5]) && !"V".equals(response[5])) {
					System.out.println("Bruh.. Give a valid INPUT!");
					response[5] = scanner.nextLine();
				}
				System.out.println("What's the name of the song?");
				response[6] = scanner.nextLine();
				System.out.println("What's the name of the artist?");
				response[7] = scanner.nextLine();
				System.out.println("How long is it (in seconds)?");
				response[8] = scanner.nextLine();
				while(Integer.parseInt(response[8]) < 0) {
					System.out.println("Give a value that is >= 0.");
					response[8] = scanner.nextLine();
				}
				
				System.out.println("How many bitrate/framerate is it?");
				response[9] = scanner.nextLine();		
				while(Double.parseDouble(response[9]) < 0) {
					System.out.println("Give a value that is >= 0.");
					response[9] = scanner.nextLine();
				}
				
				
				
				if (response[5].equals("A")) {
					dataBase.get(counter - 1).getPlaylist().add(new AudioRecording(response[7], response[6],
							Integer.parseInt(response[8]), Double.parseDouble(response[9])));
				} else if(response[5].equals("V")){
					dataBase.get(counter - 1).getPlaylist().add(new VideoRecording(response[7], response[6],
							Integer.parseInt(response[8]), Double.parseDouble(response[9])));
				} 
			
				
				
				run();
				}catch(InputMismatchException ime) {
					System.out.println("A!");
				}
				catch(NumberFormatException nfe) {
					System.out.println("A!");
				}

			} else if (response[4].equals("2")) {
				System.out.println("What is the name of the file?");
				response[11] = scanner.nextLine();
				dataBase.get(counter - 1).getPlaylist().add(response[11]);

				run();
			} else if (response[4].equals("3")) {
				try {
				System.out.println("What is the ID of the user?");
				response[12] = scanner.nextLine();
				while(Integer.parseInt(response[12])>dataBase.size()) {
					System.out.println("A! Give a valid ID.");
					response[12] = scanner.nextLine();
				}
				dataBase.get(counter - 1).getPlaylist().add(dataBase.get((Integer.parseInt(response[12]))-1).getPlaylist());
				}catch(NumberFormatException nfe) {
					System.out.println("GIVE A NUMBER!");
				}
				run();
			} else if (response[4].equals("4")) {
				System.out.println("Remove either by index or name. Write either \"index\" or  \"name\".");
				response[13] = scanner.nextLine();
				if (response[13].equals("index")) {
					System.out.println("What is the index?");
					response[19] = scanner.nextLine();
					dataBase.get(counter - 1).getPlaylist().remove(Integer.parseInt(response[19]));
				} else if(response[13].equals("name")){
					System.out.println("What is the name?");
					response[19] = scanner.nextLine();
					dataBase.get(counter - 1).getPlaylist().remove(response[19]);
				}else {
					System.out.println("A! Next time try not to do typos! Please!");
				}

				run();
			} else if (response[4].equals("5")) {
				System.out.println("Play a song by index or name. Write either \\\"index\\\" or  \\\"name\\\".");
				response[14] = scanner.nextLine();
				if (response[14].equals("index")) {
					System.out.println("What is the index?");
					response[18] = scanner.nextLine();
					dataBase.get(counter - 1).play(Integer.parseInt(response[18]));
				} else if(response[14].equals("name")){
					System.out.println("What is the name?");
					response[18] = scanner.nextLine();
					dataBase.get(counter - 1).play(response[18]);
				}else {
					System.out.println("A! Next time try not to do typos! Please!");
				}

				run();
			} else if (response[4].equals("6")) {
				dataBase.get(counter-1).play();

				run();
			} else if (response[4].equals("7")) {
				dataBase.get(counter - 1).getPlaylist().shuffle();

				run();
			} else if (response[4].equals("8")) {
				System.out.println("Give a name for the file.");
				response[15] = scanner.nextLine();
				dataBase.get(counter-1).getPlaylist().save(response[15]);

				run();
			} else if (response[4].equals("9")) {
				dataBase.get(counter - 1).getPlaylist().statistics();

				run();
			} else {
				run();
			}
			
			
			
			
		} else if(responseUser.equals("e")) {
			System.out.println("Thank you for using this music app.");
		}


		}while(!"e".equals(responseUser));
	}
}
