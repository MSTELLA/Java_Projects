//----------------------------------------------------------------------
//COMP 249 Assignment 3 - Part 8/11
//Written by: Marie-Josee Castellanos, 40168044
//----------------------------------------------------------------------
/**
 * @author Marie-Josee Castellanos 40168044
 *COMP 249
 *Assignment # 3
 *Due Date March 3rd 2021.
 * File 8/11
 * The LinesTokenizer Class contains methods to separate all components of a data line while keeping all necessary information for further methods in the outside classes
 */

package a3;

import java.util.StringTokenizer;

public class LinesTokenizer extends StringTokenizer {

	String[] line;
	int count;
	int missing;
	String[] missingLine;
	String fileName;
	int row;
	HeaderTokenizer headers;
/**
 * Unique Constructor
 * @param String toTokenize string to be analyzed (is the first line of the file being processed)
 * @param int row of the line in the file
 * @param String fileName Keeping track of the name of the file for error logging purposes
 * @param HeaderTokenizer headers received from the previous step in the HeaderTokenizer class
 * @throws CSVDataMissingException catched by the outer/calling class in the software
 */
	public LinesTokenizer(String toTokenize, int row, String fileName, HeaderTokenizer headers) throws CSVDataMissingException {
		super(toTokenize);
		count = this.countTokens();
		line = new String[count];
		missingLine = new String[count];
		missing = 0;
		this.fileName = fileName;
		this.row = row;
		this.headers = headers;
		this.StoreArraysData(toTokenize);
		this.ValidateData();
	}
/**
 * Method to save the line being processed into saved arrays. It also cleans up any accidental splitting by the presence of comas not meaning to separate the values.
 * @param String toTokenize to be analyzed. Is a data line, not a header line.
 */
	private void StoreArraysData(String toTokenize) {
		String[] parts = toTokenize.split(",");
		boolean stringDone = false;

		if (!(parts.length == count)) {
			for (int i = 0; i < parts.length; i++) {
				{
					stringDone = false;
					try {
						if (parts[i].contains("\"")) {
							int j = 1;
							while (!stringDone) {
								if (parts[i + j].contains("\"")) {
									parts[i] = parts[i] + ", " + parts[i + j];
									stringDone = true;
									for (int k = j; k > 0; k--) {
										parts[i + k] = null;
									}
								} else {
									parts[i] = parts[i] + parts[i + j];
									j++;
								}
							}

						}
					} catch (NullPointerException e) {
						continue;
					}
				}
			}
			// Adjust the count
			this.count = 0;
			for (int i = 0; i < parts.length; i++) {
				if (parts[i] != null)
					this.count++;
			}
			// Adjust parts[i]
			String[] parts2 = new String[count];

			int j = 0;
			for (int k = 0; k < count; k++) {
				boolean adjusted = false;
				if (parts[j] != null) {
					parts2[k] = parts[j];
					j++;
				} else {
					while (!adjusted) {
						j++;
						if (parts[j] != null) {
							parts2[k] = parts[j];
							adjusted = true;
							j++;
						}
					}
				}
			}
			this.line = new String[count];
			this.missingLine = new String[count];
			for (int i = 0; i < count; i++) {
				this.line[i] = this.missingLine[i] = parts2[i];
			}
		} else {
			for (int i = 0; i < count; i++) {
				this.line[i] = this.missingLine[i] = parts[i];
			}
		}
	}
/**
 * Method that validates any data lines by noting all missing fields, replacing them with *** 
 * The starting point of the CSVDataMissingException
 * @throws CSVDataMissingException handled in the outer/calling class in the software
 */
	private void ValidateData() throws CSVDataMissingException {
		for (int i = 0; i < line.length; i++) {
			if (line[i] == null || line[i].equals("")) {
				this.missing++;
				missingLine[i] = "***";
				throw new CSVDataMissingException(this); 
			}
		}
	}
	
/**
 * Method that returns the header of the missing field
 * @return String attribute of the missing field
 */
	private String getMissingAttribute() {
		int indexOfMissing = 0;
		for (int i = 0; i < missingLine.length; i++) {
			if (missingLine[i].equals("***"))
				indexOfMissing = i;
		}
		return this.headers.line[indexOfMissing];
	}
	/**
	 * Method that collects all necessary information to properly format the information to write on the log file about the CSVDataMissingException
	 * @return
	 */
	public String getDataLineErrors() {
		String dataErrorLine1 = ("In File " + fileName +" line " + row + "\n");
		String dataErrorLine2 = "";
		for(int i=0; i<count; i++) {
		dataErrorLine2 += (missingLine[i] + "\t");
		}
		String dataErrorLine3 =( "\nMissing Field information: " + this.getMissingAttribute() + "\n");
		return (dataErrorLine1 + dataErrorLine2 + dataErrorLine3);
}

	// Accessor
	
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
	/**
	 * Accessor for the row of the current line in the file
	 * @return int row
	 */
	public int getRow() {return row;}

	// Mutators
	/**
	 * Mutators for the elements in the line to be put into the Array instance variable
	 * @param String line to be tokenized and analyzed
	 */
	public void setLineArray(String[] line) {this.line = line;}
	/**
	 * Mutators for the elements in an incomplete line to be put into the Array instance variable with the appropriate ***
	 * @param String missingLine
	 */
	public void setLineMissing(String[] missingLine) {this.missingLine = missingLine;}
	/**
	 * Mutators for the numbers of tokens in the line
	 * @param int count
	 */
	public void setLineTokens(int count) {this.count = count;}
	/**
	 * Mutator for the number of fields missing in the line
	 * @param missing
	 */
	public void setMissing(int missing) {this.missing = missing;}
	/**
	 * Mutator for the name of the file being analyzed
	 * @param String fileName
	 */
	public void setfileName(String fileName) {this.fileName = fileName;}
	/**
	 * Mutator for the number of the row of the line being analyzed
	 * @param int row
	 */
	public void setRow(int row) {this.row = row;}
}
