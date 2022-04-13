
public class Main {

	public static void main(String[] args) {
		// create instance of HashNote type to test functionality
		HashNote notes = new HashNote();
		notes.writeNote();
		System.out.println(notes);
		notes.deleteNote("spaghetti");
	}
}
