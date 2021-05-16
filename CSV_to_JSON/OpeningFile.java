//----------------------------------------------------------------------
//COMP 249 Assignment 3 - Part 5/11
//Written by: Marie-Josee Castellanos, 40168044
//----------------------------------------------------------------------
/**
 * @author Marie-Josee Castellanos 40168044
 *COMP 249
 *Assignment # 3
 *Due Date March 3rd 2021.
 * File 5/11
 * The OpeningFile Class contains Static Methods that handle the opening of a JSON file by the user's input of the name
 */
package a3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class OpeningFile {
/**
 * Method that takes the user input for the JSON formatted file to open and prints it in the console. Includes a secondchance().
 * @param Scanner userKeyboard as a continuation of the main method
 */
	public static void UserOpensFile(Scanner userKeyboard) {
			BufferedReader readingJSON= null;
			System.out.println("Enter the name of a file to open: ");
			String fileName = userKeyboard.nextLine();
			try {
				String line = "";
				readingJSON = new BufferedReader(new FileReader(fileName));
				while ((line = readingJSON.readLine()) != null) {
					System.out.println(line);
				}
				readingJSON.close();
			}

			catch (FileNotFoundException e) {
				System.out.println("Error Occurred with " + fileName
						+ " file for reading. \n Please check if file exists! Program will terminate after closing all opened files.");
				secondChance(readingJSON);
			} catch (IOException e) {
				System.out.println("IOException during internal process. Sorry, System will exit.");
			}

		userKeyboard.close();
		System.out.println("Thank you for using CSV2JSON.");
	}
/**
 * Method that allows the user to reddeem themselves by entering a correct filename
 * @param BufferedReader readingJSON as a continuation of the calling method
 */
	private static void secondChance(BufferedReader readingJSON) {
		Scanner userKeyboard = new Scanner(System.in);
			System.out.println("You get a second and final chance, Enter the name of a file to open: ");
			String fileName = userKeyboard.nextLine();
			try {
				String line = "";
				readingJSON = new BufferedReader(new FileReader(fileName));
				while ((line = readingJSON.readLine()) != null) {
					System.out.println(line);
				}
			}
			catch (FileNotFoundException e) {
				System.out.println("Too many mistakes, The program will now close.");
				System.exit(0);
			}catch (IOException e) {
				System.out.println("IOException during internal process. Sorry, System will exit.");
			}
			
		userKeyboard.close();
		System.out.println("Thank you for using CSV2JSON.");
	}
	
}
