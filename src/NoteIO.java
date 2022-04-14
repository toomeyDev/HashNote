/* Class for handling file IO of individual notes to local filesystem */
import java.io.FileWriter;
import java.io.IOException;
public class NoteIO {
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
}
