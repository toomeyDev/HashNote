/*
 * Class for handling HashNote functionality (writing notes, accessing saved notes)
 * (listing out all notes stored in the noteFiles hash)
 */
import java.util.HashMap;
import java.util.Scanner;

public class HashNote {
	// store notes in a local hashmap instance
	private HashMap<String, String> noteFiles;
	Scanner inputSource;

	public HashNote(Scanner inputSource) {
		this.inputSource = inputSource; 
		noteFiles = new HashMap<String, String>();
	}
	
	/* 
	 * Write a note to the current noteFiles HashMap, prompting for name and 
	 * content as user input taken from STDIN, give appropriate feedback to user
	 */
	public void writeNote() {
		String noteName = getLines("name", inputSource);
		String noteContent = getLines("content", inputSource);
		
		System.out.println("Your note:\n" + noteName + " : " + noteContent);
		noteFiles.put(noteName, noteContent);
		NoteIO.writeNoteToFile(noteName, noteContent);
		System.out.println("Note " + noteName + " saved successfully.");
	}
	
	// Write a note to the current noteFiles HashMap, taking name and content as arguments
	public void writeNote(String noteName, String noteContent) {
		noteFiles.put(noteName, noteContent);
	}
	
	public void deleteNote(String key) {
		Object removedNote = noteFiles.remove(key);
		// ensure note is present in HashMap, otherwise print an error message
		if(removedNote == null) {
			System.out.println("Error, cannot delete note " + key + ", not found in notes.");
		} else {
			System.out.println("Note " + key + " deleted.");
		}
	}
	
	public String getNote(String key) {
		String note = noteFiles.get(key);
		// ensure note is present in HashMap
		if(note == null) {
			return "Note not found";
		} else {
			return note;
		}
	}
	
	public void saveNotes() {
		System.out.println("Saving notes to local disk...");
		NoteIO.saveNotesToDisk(noteFiles);
		System.out.println("Notes written to local disk...");
	}
	
	public void loadNotes() {
		System.out.println("Loading notes from local disk...");
		NoteIO.readNoteFromFile(noteFiles, "Sample Note");
	}
	
	public void printNote(String key) {
		Object note = noteFiles.get(key);
		// ensure note is present in HashMap, otherwise print an error message
		if(note == null) {
			System.out.println("Error, cannot access note " + key + ", not found in notes.");
		} else {
			System.out.println(key + ":\n" + note);
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
			hashString += ("\n- " + hashKey + " Content: \n " + noteFiles.get(hashKey) + "\n ");
		}
		hashString += "\n========================================================"
				+ "=========================";
		return hashString;
	}
}
