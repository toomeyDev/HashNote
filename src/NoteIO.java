/* Class for handling file IO of individual notes to local filesystem */
import java.io.FileWriter;
import java.io.File;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
public abstract class NoteIO {
	
	/*
	 * Write an individual note to the local filesystem
	 * currently writes to the application folder only
	 */
	public static boolean writeNoteToFile(String noteName, String noteContent) {
		try {
			// make sure a notes directory is present, if not create one
			if(createNotesDirectory()) {
				System.out.println("\nNo notes directory present, created 'notes' folder\n"
						+ "in application directory...");
			}
			
			// create file references to notes folder, file to be created
			File notesDir = new File("notes");
			File noteFile = new File (notesDir, (noteName + ".txt"));
			
			FileWriter noteWriter = new FileWriter(noteFile);
			noteWriter.write(noteContent);
			noteWriter.close();
			System.out.println("\nWrote " + noteName + " contents to disk.");
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
		if(!Files.exists(Paths.get("notes"))) {
			try {
				Files.createDirectories(Paths.get("notes"));
				return true;
			} catch (IOException e) {
				System.out.println(e.getStackTrace());
				return false;
			}	
		}
		// if directory was not created, return false
		return false;
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
				noteReader.close();
	}
	
	/*
	 * load all local note files stored in the "notes" directory if present
	 * otherwise create the notes directory and present a message indicating
	 * that the directory has been created
	 */
	public static void loadLocalNotes(java.util.HashMap<String, String> notesHash) {
		if(createNotesDirectory()) {
			System.out.println("\nNo notes directory present, created 'notes' folder\n"
					+ "in application directory...");
		}
		
		List<Path> filesInFolder = null;
		// attempt to get list of files in folder, read any files found into
		// application from local notes folder
		try {
			filesInFolder = Files.list(Paths.get("notes"))
				.filter(Files::isRegularFile)
				.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			for (Path path : filesInFolder) {
				readNoteFromFile(notesHash, path.getFileName().toString());
			}
		}
	}
}
