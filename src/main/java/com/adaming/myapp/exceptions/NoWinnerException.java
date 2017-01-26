package com.adaming.myapp.exceptions;

@SuppressWarnings("serial")
public class NoWinnerException extends Exception {

	public NoWinnerException() {
	}

	public NoWinnerException(String message) {
		super(message);
	}

	public NoWinnerException(Throwable cause) {
		super(cause);
	}

	public NoWinnerException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoWinnerException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
