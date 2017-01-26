package com.adaming.myapp.exceptions;

@SuppressWarnings("serial")
public class CourseNotRunException extends Exception {

	public CourseNotRunException() {
	}

	public CourseNotRunException(String message) {
		super(message);
	}

	public CourseNotRunException(Throwable cause) {
		super(cause);
	}

	public CourseNotRunException(String message, Throwable cause) {
		super(message, cause);
	}

	public CourseNotRunException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
