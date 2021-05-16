//----------------------------------------------------------------------
//COMP 249 Assignment 3 - Part 3/11
//Written by: Marie-Josee Castellanos, 40168044
//----------------------------------------------------------------------
/**
 * @author Marie-Josee Castellanos 40168044
 *COMP 249
 *Assignment #3
 *Due Date March 3rd 2021.
 * File 3/11
 * The FileValidation Class contains Static Methods that handle the validation of the files to process
 */
package a3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileValidation {
/**
 * Method That is the Core Engine of the software. It calls all the steps (fileInput, methods to validate, FileOutput)
 * Structured to need no parameters and return nothing, all parameters needed by other methods are inside of this one.
 */
	public static void ProcessFilesForValidation() {
		// USER INPUTS FILE NAMES
		String[] csvFileNames = FileInput.InputFileNames();

		// LOG FILE
		PrintWriter writingLogFile = null;
		try {
			writingLogFile = new PrintWriter(new FileOutputStream("log.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("An Error occurred while creating a Log file");
		}

		// MAIN FILE
		File[] csvFiles = new File[csvFileNames.length];
		String[][] dataRows = null;

		for (int i = 0; i < csvFileNames.length; i++) {
			// lines count
			int lineCounter = 0;
			try {
				lineCounter = getLinesCount(csvFileNames[i]);
			} catch (FileNotFoundException e) {
				System.out.println("Error Occurred with " + csvFileNames[i]
						+ " file for reading. \n Please check if file exists! Program will terminate after closing all opened files.");
			}

			// Validation headers & Data (inside the Header)
			dataRows = ValidatingHeaders(csvFileNames[i], lineCounter, writingLogFile, csvFileNames);

			// Writing
			FileOutput.WriteJSON(csvFileNames[i], dataRows);
		}
	}
/**
 * Method that counts the lines of the file being processed
 * @param String csvFileName
 * @return int LineCounter which represent the lines of the file
 * @throws FileNotFoundException That can be catched by the outer/calling method
 */
	public static int getLinesCount(String csvFileName) throws FileNotFoundException {
		int lineCounter = 0;
		Scanner readfromCSV = null;
		try {
			File filecounted = new File(csvFileName);
			readfromCSV = new Scanner(filecounted);
			while (readfromCSV.hasNextLine()) {
				lineCounter++;
				readfromCSV.nextLine();
			}
		} finally {
			readfromCSV.close();
		}
		return lineCounter;
	}
/**
 * Method that validates the headers by finding any missing ones and proceeding to the CSVFileInvalidException that closes the program and deletes any JSON files created
 * @param String csvFileName Name of the file being processed
 * @param int LineCounter which represent the lines of the file
 * @param PrintWriter writingLogFile PrintWriter that handles all things to be written on the log file
 * @param String[] csvFileNames All files being processed for the deleting part of the CSVFileInvalidException
 * @return String[][] That represent all the data read from the file being processed. Only contains the Headers.
 */
	public static String[][] ValidatingHeaders(String csvFileName, int lineCounter, PrintWriter writingLogFile, String[] csvFileNames) {
		Scanner readfromCSV = null;
		String[][] dataRows = null;

		try {
			readfromCSV = new Scanner(new FileInputStream(csvFileName));
			String line = readfromCSV.nextLine();
			HeaderTokenizer HeaderTokens = new HeaderTokenizer(line, ",", csvFileName);
			// Data Row Initialization for actual size
			dataRows = new String[lineCounter][HeaderTokens.getCount()];
			for (int j = 0; j < HeaderTokens.line.length; j++) {
				dataRows[0][j] = HeaderTokens.getLine()[j];
			}

			// Continue to DATALINES
			ValidatingDataLines(csvFileName, HeaderTokens, writingLogFile, dataRows, readfromCSV);

		} catch (CSVFileInvalidException e) {
			System.out.println(e.getMessage());
			System.out
					.println("The system will delete all JSON files generated and exit. Thank you for using CSV2JSON");
			writingLogFile.print(e.getErrorLine());
			writingLogFile.close();
			// Add statement to delete all files HERE
			for (int j = 0; j < csvFileNames.length; j++) {
				if (csvFileNames[j] != null)  {
					File file = new File(csvFileNames[j]);
					if(file.exists() && !(file.isDirectory()))
					new File(csvFileNames[j] + ".json").delete();
				}
			}
			System.exit(0);
		} catch (FileNotFoundException e) {
			System.out.println("Error Checking the Headers.");
		}
		return dataRows;
	}
/**
 * Method that validates the Data lines by finding any missing ones and proceeding to the CSVDataMissingException that logs the error without stopping the process of the next lines or next file
 * @param  String csvFileName Name of the file being processed
 * @param HeadTokenizer HeaderTokens Object that tokenizes/splits and processes the line to proceed to the validation
 * @param PrintWriter writingLogFile PrintWriter that handles all things to be written on the log file
 * @param String [][] dataRows Is the 2DString that represents all data read from the file being processed
 * @param Scanner readfromCSV that reads the file.
 * @return String[][] dataRows2 Complete data read on the file
 */
	public static String[][] ValidatingDataLines(String csvFileName, HeaderTokenizer HeaderTokens,PrintWriter writingLogFile, String[][] dataRows, Scanner readfromCSV) {
		String[][] dataRows2 = dataRows;
		try {
			readfromCSV = new Scanner(new FileInputStream(csvFileName));
			String line;
			LinesTokenizer lineTokens = null;
			for (int j = 0; j < dataRows2.length; j++) {// rows
				try {
				line = readfromCSV.nextLine(); 
				lineTokens = new LinesTokenizer(line, (j), csvFileName, HeaderTokens);
				for (int k = 0; k < lineTokens.getCount(); k++) {
					dataRows2[j][k] = (lineTokens.getLine())[k];
					}
				}
				catch (CSVDataMissingException e) {
					System.out.println(e.getMessage());
					try {
						writingLogFile = new PrintWriter(new FileOutputStream("log.txt", true));
					} catch (FileNotFoundException e1) {
						System.out.println("An Error occurred while writing to a Log file");
					}
					writingLogFile.print(e.getErrorLine());
					writingLogFile.close();
					continue; // continue for loop for next row
				}
			}
		}
		 catch (FileNotFoundException e2) {
			 System.out.println("Error Checking the DataLines.");
		 } 
		
		readfromCSV.close();
		return dataRows2;
		}
}
