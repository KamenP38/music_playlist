public class MusicApp {
	public static void main(String[] args) {
		StreamingSystem app = new StreamingSystem();
		try {
		app.run();
		}catch(Unplayable unpl) {
			System.out.println();
		}
		
	}
}
