//----------------------------------------------------------------------
//COMP 249 Assignment 3 - Part 9/11
//Written by: Marie-Josee Castellanos, 40168044
//----------------------------------------------------------------------
/**
 * @author Marie-Josee Castellanos 40168044
 *COMP 249
 *Assignment # 3
 *Due Date March 3rd 2021.
 * File 9/11
 * The InvalidException was required in the assignment as being the base Exception of the other two custom Exceptions. Not used in the software.
 */
package a3;

public class InvalidException extends Exception{

	String fileName;
	String message;
	/**
	 * Default constructor of the exception with no information on the row or line causing the problem.
	 */
	public InvalidException() {
		this.message= "Error: Input row cannot be parsed due to missing information"; /// This message is written in the instructions but does not make sense here.
	}
	/**
	 * Parameterized Constructor to output the name of the file being analyzed
	 * @param fileName
	 */
	public InvalidException(String fileName) {
		this.message = "Error Occurred with " +fileName+ " file for reading. \n Please check if file exists! Program will terminate after closing all opened files.";
		this.fileName = fileName;
	}
	
	/**
	 * Accessor for the message to be printed to the user
	 * @return message
	 */
	public String getMessage() {return message;}
	
	/**
	 * Accesor for the name of the file kept in the exception
	 * @return fileName
	 */
	public String getFileName() {return fileName;}
	/**
	 * Mutator for the message to be printed to the user.
	 * @param message
	 */
	public void setMessage(String message) {this.message = message;}
	/**
	 * Mutator for the fileName to be analyzed.
	 * @param fileName
	 */
	public void setFileName(String fileName) {this.fileName = fileName;}
	
}
