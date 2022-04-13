/*
 * Class for handling HashNote functionality (writing notes, accessing saved notes)
 * (listing out all notes stored in the noteFiles hash)
 */
import java.util.HashMap;

public class HashNote {
	// store notes in a local hashmap instance
	private HashMap<String, String> noteFiles;
	
	public HashNote() {
		noteFiles = new HashMap<String, String>();
		noteFiles.put("Sample Note", "This is a sample note, intended to test this feature.");
		noteFiles.put("Test Note", "This test note is intended as dummy-data to test HashNote features.");
	}
	
	@Override
	public String toString() {
		String hashString = "Notes:\n";
		for (String hashKey : this.noteFiles.keySet()) {
			hashString += ("- " + hashKey + " = " + noteFiles.get(hashKey) + "\n");
		}
		hashString += "========================================================"
				+ "=========================";
		return hashString;
	}
}
