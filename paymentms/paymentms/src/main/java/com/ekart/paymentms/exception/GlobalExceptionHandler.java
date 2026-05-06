package com.ekart.paymentms.exception;

import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidCvvException.class)
	public String handleInvalidCvv(InvalidCvvException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(Exception.class)
	public String handleGeneric(Exception ex) {
		return "Error: " + ex.getMessage();
	}
}