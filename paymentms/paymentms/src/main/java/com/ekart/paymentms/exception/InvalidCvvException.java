package com.ekart.paymentms.exception;

public class InvalidCvvException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCvvException(String message) {
		super(message);
	}
}