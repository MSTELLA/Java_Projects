//----------------------------------------------------------------------
//COMP 249 Assignment 3 - Part 4/11
//Written by: Marie-Josee Castellanos, 40168044
//----------------------------------------------------------------------
/**
 * @author Marie-Josee Castellanos 40168044
 *COMP 249
 *Assignment # 3
 *Due Date March 3rd 2021.
 * File 4/11
 * The FileValidation Class contains Static Methods that handle the validation of the files to process
 */
package a3;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class FileOutput {
	
/**
 * Method that handles writing a new JSON formatted file after its validation. It receives the fileName to be created and the information to be written.
 * @param String csvfileName
 * @param String[][] dataRows
 */
	public static void WriteJSON(String csvfileName, String[][] dataRows) {
		csvfileName = csvfileName.substring(0,(csvfileName.length()-4));
		PrintWriter printingJSON = null;
		try {
			printingJSON = new PrintWriter(new FileOutputStream(csvfileName + ".json"));
		
		printingJSON.print("\n[");
		for (int j = 1; j < dataRows.length; j++) {
			if (dataRows[j][1] != null) {
				printingJSON.print("\n{");
				for (int k = 0; k < dataRows[0].length; k++) {
					if(dataRows[j][k]!=null) {
					printingJSON.print("\n\"" + dataRows[0][k] + "\":" + dataRows[j][k] + ",");}
				}
				printingJSON.print("\n}");
			}
		}
		printingJSON.print("\n]");} 
		catch (FileNotFoundException e) {
			System.out.println("An Error occurred while creating a file");
		}
		printingJSON.close();
	}
}
