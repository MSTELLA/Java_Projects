//----------------------------------------------------------------------
//COMP 249 Assignment 3 - Part 11/11
//Written by: Marie-Josee Castellanos, 40168044
//----------------------------------------------------------------------
/**
 * @author Marie-Josee Castellanos 40168044
 *COMP 249
 *Assignment # 3
 *Due Date March 3rd 2021.
 * File 11/11
 * The CSVDataMissingException will notify the user, write a log entry and continue.
 */
package a3;

public class CSVDataMissingException extends InvalidException{

	String errorLine;
	/**
	 * Default Constructor
	 */
	public CSVDataMissingException() {
		super();
	}
	/**
	*Constructor that generates a message ready to be shared with the user.	
	* @param LinesTokenizer line to call back on the row of the line being processed
	*/
	public CSVDataMissingException(LinesTokenizer line) {
		this.message = "Error Occurred on data row " + line.getRow() + ", consult the Log file for more information.";
		errorLine = line.getDataLineErrors();
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
