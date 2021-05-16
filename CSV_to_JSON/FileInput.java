//----------------------------------------------------------------------
//COMP 249 Assignment 3 - Part 2/11
//Written by: Marie-Josee Castellanos, 40168044
//----------------------------------------------------------------------
package a3;

import java.util.Scanner;
/**
 * @author Marie-Josee Castellanos 40168044
 *COMP 249
 *Assignment # 3
 *Due Date March 3rd 2021.
 * File 2/11
 * The File Input Class contains Static Methods that handle the User input for number of files and names of files to process.
 */
public class FileInput {
	
/**
 * Private method to handle the user's Input of number of files to Process.
 * This method supports the InputFileNames() Method in the class.
 * @param userKeyboard
 * @return int InputNumberOfFiles for the creation of an Array of StringNames of the right size
 */
	private static int InputNumberOfFiles(Scanner userKeyboard) {
		boolean numberOfFilesValidated = false;
		int numberOfFiles = 0;
		
		while (numberOfFilesValidated == false) {
			System.out.println("How many files do you want to convert (minimum of 2)?");
			numberOfFiles = userKeyboard.nextInt();
			if (numberOfFiles >= 2) {
				userKeyboard.nextLine(); // to get rid of \n
				numberOfFilesValidated = true;
			} else
				System.out.println("Wrong number of files, try again.");
		}
		
		return numberOfFiles;
	}
/**
 * Public Method that handles the User's input of file names.
 * @return String[] InputFileNames to be used by other methods.
 */
	public static String[] InputFileNames() {
		Scanner userKeyboard = new Scanner(System.in);
		int numberOfFiles = InputNumberOfFiles(userKeyboard );
		String[] csvFileNames = new String[numberOfFiles];
		for (int i = 0; i < numberOfFiles; i++) {
			System.out.println("Enter the name of file " + (i + 1) + ": ");
			String fileName = userKeyboard.nextLine();
			csvFileNames[i] = fileName;
		}
		return csvFileNames;
	}
	
}
