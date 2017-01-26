package com.adaming.myapp.exceptions;

@SuppressWarnings("serial")
public class NotEnoughSoldeException extends Exception {

	public NotEnoughSoldeException() {
	}

	public NotEnoughSoldeException(String message) {
		super(message);
	}

	public NotEnoughSoldeException(Throwable cause) {
		super(cause);
	}

	public NotEnoughSoldeException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotEnoughSoldeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
