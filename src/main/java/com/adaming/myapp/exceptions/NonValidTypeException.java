package com.adaming.myapp.exceptions;

@SuppressWarnings("serial")
public class NonValidTypeException extends Exception {

	public NonValidTypeException() {
	}

	public NonValidTypeException(String message) {
		super(message);
	}

	public NonValidTypeException(Throwable cause) {
		super(cause);
	}

	public NonValidTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public NonValidTypeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
