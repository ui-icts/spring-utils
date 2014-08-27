package edu.uiowa.icts.exception;

public class DatatableException extends Exception {

	private static final long serialVersionUID = 2890579965738044986L;
	
	private String message;

	// ----------------------------------------------
	// Default constructor - initializes instance variable to unknown

	public DatatableException() {
		super(); // call superclass constructor
		message = "unknown";
	}

	// -----------------------------------------------
	// Constructor receives some kind of message that is saved in an instance
	// variable.

	public DatatableException(String mapping) {
		super(mapping); // call super class constructor
		this.message = mapping; // save message
	}

	// ------------------------------------------------
	// public method, callable by exception catcher. It returns the error
	// message.
	public String getError() {
		return message;
	}
}