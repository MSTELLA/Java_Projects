//----------------------------------------------------------------------
//COMP 249 Assignment 3 - Part 7/11
//Written by: Marie-Josee Castellanos, 40168044
//----------------------------------------------------------------------
/**
 * @author Marie-Josee Castellanos 40168044
 *COMP 249
 *Assignment # 3
 *Due Date March 3rd 2021.
 * File 7/11
 * The HeaderTokenizer Class contains methods to separate all components of a header line while keeping all necessary information for further methods in the outside classes
 */

package a3;

import java.util.StringTokenizer;

public class HeaderTokenizer extends StringTokenizer {

	String[] line;
	int count;
	int missing;
	String[] missingLine;
	String fileName;
	int row;
/**
 * Unique Constructor
 * @param String toTokenize string to be analyzed (is the first line of the file being processed)
 * @param String delimiters to separate the header components
 * @param String fileName Keeping track of the name of the file for error logging purposes
 * @throws CSVFileInvalidException can be catched by outer Class/Calling Class in the software
 */
	public HeaderTokenizer(String toTokenize, String delimiters, String fileName) throws CSVFileInvalidException {
		super(toTokenize, delimiters);
		count = this.countTokens();
		line = new String[count];
		missingLine = new String[count];
		missing = 0;
		this.fileName = fileName;
		this.StoreArrays(toTokenize);
		this.ValidateAttributes();
	}
/**
 * Method to save the String being read into Arrays that can be accessed and modified by other methods;
 * @param toTokenize
 */
	private void StoreArrays(String toTokenize) {
		String[] parts = toTokenize.split(",");
		for (int i = 0; i < count; i++) {
			this.line[i] = this.missingLine[i] = parts[i];
		}
	}

	/**
	 * Method to validate the components of the Header. 
	 * @throws CSVFileInvalidException that can be catched by the calling class in the class
	 */
	private void ValidateAttributes() throws CSVFileInvalidException {
		for (int i = 0; i < line.length; i++) {
			if (line[i] == null || line[i].equals("")) {
				this.missing++;
				missingLine[i] = "***";
			}
		}
		if (this.missing > 0)
			throw new CSVFileInvalidException(this);
	}
	
/**
 * Method that provides a properly formatted Error line for the log file
 * @return String that is the error line to be printed in the log file
 */
	
	public String getHeaderErrors() {
		String headerError1=("The File " + fileName +" is invalid.\n Fields information: "+ count +" Detected,"+ missing +" missing.\n");
		String headerError2="";
		for(int i=0; i<count; i++) {
			headerError2 += (missingLine[i] + "\t");
		}
		return (headerError1 + headerError2);
	}
	
	/**
	 * Accessor for the String[] line that is the line being analyzed by the Tokenizer
	 * @return String[] line
	 */
	public String[] getLine() {return line;}
	/**
	 * Accessor for the String[] missingLine that is the line but has *** instead of empty or null fields
	 * @return String[] missingLine
	 */
	public String[] getMissingLine() {return missingLine;}
	/**
	 * Accessor for the count of tokens in the line being analyzed
	 * @return int count
	 */
	public int getCount() {return count;}
	/**
	 * Accessor for the number of missing tokens int the line
	 * @return int missing
	 */
	public int getMissing() {return missing;}
	/**
	 * Accessor for the file name of the line
	 * @return String file name
	 */
	public String getFileName() {return fileName;}

	// Mutators
	public void setLineArray(String[] line) {this.line = line;}

	public void setLineMissing(String[] missingLine) {this.missingLine = missingLine;}

	public void setLineTokens(int count) {this.count = count;}

	public void setMissing(int missing) {this.missing = missing;}

	public void setfileName(String fileName) {this.fileName = fileName;}


}
