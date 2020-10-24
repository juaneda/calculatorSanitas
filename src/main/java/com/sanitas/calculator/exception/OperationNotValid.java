package com.sanitas.calculator.exception;

public class OperationNotValid  extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5514293315081757137L;

	public OperationNotValid(String operation) {
		super("Operation not valid: " + operation);
	}
	
}
