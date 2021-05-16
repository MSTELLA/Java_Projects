package a3;
//----------------------------------------------------------------------
//COMP 249 Assignment 3 - Part 1/11
//Written by: Marie-Josee Castellanos, 40168044
//----------------------------------------------------------------------
/**
 * @author Marie-Josee Castellanos 40168044
 *COMP 249
 *Assignment # 3
 *Due Date March 3rd 2021.
 * File 1/11
 * Contains the Main method that will call other static methods in Accessible Classes
 */
import java.util.*;

public class CSV2JSON2 {

	public static void main(String[] args) {

		System.out.println("Welcome to the CSV2JSON Software.");
		
		Scanner userKeyboard = new Scanner(System.in);

		FileValidation.ProcessFilesForValidation();

		OpeningFile.UserOpensFile(userKeyboard);
		
		userKeyboard.close();
	}
	
}
