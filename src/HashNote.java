/*
 * Class for handling HashNote functionality (writing notes, accessing saved notes)
 * (listing out all notes stored in the noteFiles hash)
 */
import java.util.HashMap;
import java.util.Scanner;

public class HashNote {
	// store notes in a local hashmap instance
	private HashMap<String, String> noteFiles;
	
	public HashNote() {
		noteFiles = new HashMap<String, String>();
		writeNote("Sample Note", "This is a sample note for testing HashNote functionality.");
	}
	
	/* 
	 * Write a note to the current noteFiles HashMap, prompting for name and 
	 * content as user input taken from STDIN, give appropriate feedback to user
	 */
	public void writeNote() {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter note name: ");
		// store note name to be used as hash key based on user input
		String noteName = input.nextLine();
		
		System.out.print("Write note content: ");
		String noteContent = input.nextLine();
		
		System.out.println("Your note:\n" + noteName + " : " + noteContent);
		noteFiles.put(noteName, noteContent);
		System.out.println("Note " + noteName + " saved successfully.");
		
		input.close();
	}
		
	// Write a note to the current noteFiles HashMap, taking name and content as arguments
	public void writeNote(String noteName, String noteContent) {
		noteFiles.put(noteName, noteContent);
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
