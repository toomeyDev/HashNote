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
		
		String noteName = getLines("name", input);
		String noteContent = getLines("content", input);
		
		System.out.println("Your note:\n" + noteName + " : " + noteContent);
		noteFiles.put(noteName, noteContent);
		System.out.println("Note " + noteName + " saved successfully.");
		
		input.close();
	}
	
	// Write a note to the current noteFiles HashMap, taking name and content as arguments
	public void writeNote(String noteName, String noteContent) {
		noteFiles.put(noteName, noteContent);
	}
	
	public void deleteNote(String key) {
		Object removedNote = noteFiles.remove(key);
		if(removedNote == null) {
			System.out.println("Error, cannot delete note " + key + ", not found in notes.");
		} else {
			System.out.println("Note " + removedNote + " deleted.");
		}
	}
	
	public String getLines(String mode, Scanner input) {
		String outString = "";
		
		if(mode.equals("name")) {
			System.out.print("Enter note name: ");
			// store note name to be used as hash key based on user input
			outString += input.nextLine();
		}else if(mode.equals("content")) {
			System.out.print("Write note content (enter '/q' or '/quit' to finish entry): ");
			while(true) {
				String uInput = input.nextLine();
				if(uInput.equals("/q") || uInput.equals("/quit")) {
					break;
				} else {
					outString += uInput;
					outString += "\n";
				}
			}
		}
		return outString;
	}
	
	@Override
	public String toString() {
		String hashString = "Notes:\n";
		for (String hashKey : this.noteFiles.keySet()) {
			hashString += ("- " + hashKey + " Content: \n " + noteFiles.get(hashKey) + "\n ");
		}
		hashString += "\n========================================================"
				+ "=========================";
		return hashString;
	}
}
