package com.cts.adb.exception;

public class ContactNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public ContactNotFoundException() {
		super();
	}

	public ContactNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ContactNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ContactNotFoundException(String message) {
		super(message);
	}

	public ContactNotFoundException(Throwable cause) {
		super(cause);
	}

}
