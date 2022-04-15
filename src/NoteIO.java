/* Class for handling file IO of individual notes to local filesystem */
import java.io.FileWriter;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
public abstract class NoteIO {
	
	/*
	 * Write an individual note to the local filesystem
	 * currently writes to the application folder only
	 */
	public static boolean writeNoteToFile(String noteName, String noteContent) {
		try {
			FileWriter noteWriter = new FileWriter(noteName + ".txt");
			noteWriter.write(noteContent);
			noteWriter.close();
			System.out.println("Wrote " + noteName + " contents to disk.");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/*
	 * Write all notes present in the passed notes hash to filesystem
	 */
	public static void saveNotesToDisk(java.util.HashMap<String, String> notesHash) {
		// if a notes directory does not currently exist, create it
		// and provide an informative message to the user
		if(createNotesDirectory()) {
			System.out.println("Notes directory created in application folder...");
		}
		
		for(String hashKey : notesHash.keySet()) {
			writeNoteToFile(hashKey, notesHash.get(hashKey));
		}
	}
	
	/*
	 * If notes directory does not exist in application folder, create it
	 * otherwise don't do anything if the notes directory already exists
	 */
	public static boolean createNotesDirectory() {
		try {
			Files.createDirectories(Paths.get("notes"));
			return true;
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
			return false;
		}
	}
	
	/*
	 * load an individual note from the local filesystem
	 * into passed notes hash (currently only checks application folder)
	 */
	public static void readNoteFromFile(java.util.HashMap<String, String> notesHash,
			String fileName) {
				Scanner noteReader = new Scanner(fileName);
				String noteContents = "";
				while (noteReader.hasNextLine()) {
					noteContents += noteReader.nextLine();
				}
				notesHash.put(fileName, noteContents);
//				System.out.println("Loaded note from file " + fileName + " successfully.");
	}
	
}
