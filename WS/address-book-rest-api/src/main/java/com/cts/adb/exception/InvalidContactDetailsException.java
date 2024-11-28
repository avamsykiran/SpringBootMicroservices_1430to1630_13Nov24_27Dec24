package com.cts.adb.exception;

public class InvalidContactDetailsException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidContactDetailsException() {
		super();
	}

	public InvalidContactDetailsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidContactDetailsException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidContactDetailsException(String message) {
		super(message);
	}

	public InvalidContactDetailsException(Throwable cause) {
		super(cause);
	}

}
