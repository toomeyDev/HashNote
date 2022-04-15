import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("HashNote ver 0.1.0");
		
		Scanner scInput = new java.util.Scanner(System.in);
		HashNote notesApp = new HashNote(scInput);
		System.out.println(notesApp); // list current values present in notes
		
		boolean runTime = true;
		
		// display an info message for the user at start with command descriptions
		System.out.println("Type 'write' to start writing a new note,\n"
				+ "'list' to display all stored notes, 'get' to print\n"
				+ "a note to the screen, 'save' to save all notes to disk\n"
				+ "'delete' to delete a note from the program or quit to exit");
		
		while(runTime) {
			System.out.print("\nSelection: ");
			String uSelection = scInput.nextLine();
			switch(uSelection) {
				case "write": 
					notesApp.writeNote();
					break;
				case "list":
					System.out.println(notesApp);
					break;
				case "get":
					System.out.print("\nEnter note name for printing: ");
					String uGet = scInput.nextLine();
					notesApp.printNote(uGet);
					break;
				case "delete":
					System.out.print("\nEnter note name for deletion: ");
					String uDelete = scInput.nextLine();
					notesApp.deleteNote(uDelete);
					break;
				case "save":
					notesApp.saveNotes();
					break;
				case "load":
					notesApp.loadNotes();
					break;
				case "quit":
					System.out.println("Exiting..");
					runTime = false;
					break;
				case "exit":
					System.out.println("Exiting..");
					runTime = false;
					break;
				default:
					System.out.println("Invalid input, please try again.");
					break;
			}
		}
	}
}
