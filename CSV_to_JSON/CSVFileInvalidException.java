//----------------------------------------------------------------------
//COMP 249 Assignment 3 - Part 10/11
//Written by: Marie-Josee Castellanos, 40168044
//----------------------------------------------------------------------
/**
 * @author Marie-Josee Castellanos 40168044
 *COMP 249
 *Assignment # 3
 *Due Date March 3rd 2021.
 * File 10/11
 * The CSVFileInvalidException will notify the user, delete all JSON files created and exit the program
 */
package a3;

public class CSVFileInvalidException extends InvalidException {
	
	String errorLine; 

	public CSVFileInvalidException() {super();}
/**
 * Constructor that generates a message ready to be shared with the user.	
 * @param HeaderTokenizer line to call back on the name of the file for display purposes
 */
	public CSVFileInvalidException(HeaderTokenizer line) {
		super();
		this.message = "Error Occurred with the file " +line.fileName+ " as it is missing a column title. consult the Log file for more information.";
		errorLine = line.getHeaderErrors();
		}
	
	/**
	 * Accessor for the Exception Message
	 * return String message generated
	 */
	public String getMessage() {return message;}
	/**
	 * Accessor for the errorLine (line which will be written in the log file)
	 * @return String errorLine
	 */
	public String getErrorLine() {return errorLine;}
	
	/**
	 * Mutator for the Exception message
	 * @param String message
	 */
	public void setMessage(String message) {this.message = message;}
	/**
	 * Mutator for the Exception errorLine (for the log file)
	 * @param String errorLine
	 */
	public void setErrorLine(String errorLine) {this.errorLine = errorLine;}
}
