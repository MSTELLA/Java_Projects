//----------------------------------------------------------------------
//COMP 249 Assignment 3 - Part 6/11
//Written by: Marie-Josee Castellanos, 40168044
//----------------------------------------------------------------------
/**
 * @author Marie-Josee Castellanos 40168044
 *COMP 249
 *Assignment # 3
 *Due Date March 3rd 2021.
 * File 6/11
 * NOT USED BUT TOO LATE TO DELETE....
 * The LogFile Class contains methods to properly format information to be logged into the log file.
 */
package a3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class LogFile extends File{
/**
 * Default Constructor, not used
 * @param name
 */
	public LogFile(String name) {
		super(name);
	}

/**
 * Method that writes onto the logFile
 * @param line
 */
	public void writeHeaderErrors(HeaderTokenizer line) {
		try {
			PrintWriter writingDRE = new PrintWriter(this);
			int d = line.getCount();
			int m = line.getMissing();
			writingDRE.print("The File " + line.getFileName() +" is invalid.\n Fields information: "+ d +" Detected,"+ m +" missing.\n" + line.getMissingLine());
			writingDRE.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Error Finding or creating the log file.");
		}
	}
	
	public void writeDataLineErrors(LinesTokenizer line) {
		try {
			PrintWriter writingDRE = new PrintWriter(this);
			writingDRE.print("In File " + line.getFileName() +" line " + line.getRow() + "\n");
			for(int i=0; i<line.getCount(); i++) {
			writingDRE.print(line.missingLine[i] + "\t");
			}
			writingDRE.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Error Finding or creating the log file.");
		}
	}
	
}
