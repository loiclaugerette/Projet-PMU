package com.adaming.myapp.exceptions;

@SuppressWarnings("serial")
public class CourseAlreadyRunException extends Exception {

	public CourseAlreadyRunException() {
		super();
	}

	public CourseAlreadyRunException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CourseAlreadyRunException(String message, Throwable cause) {
		super(message, cause);
	}

	public CourseAlreadyRunException(String message) {
		super(message);
	}

	public CourseAlreadyRunException(Throwable cause) {
		super(cause);
	}


}
