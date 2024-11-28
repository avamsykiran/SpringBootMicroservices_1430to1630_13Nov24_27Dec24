package com.cts.adb.exception;

public class AdbException extends Exception {

	private static final long serialVersionUID = 1L;

	public AdbException() {
		super();
	}

	public AdbException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AdbException(String message, Throwable cause) {
		super(message, cause);
	}

	public AdbException(String message) {
		super(message);
	}

	public AdbException(Throwable cause) {
		super(cause);
	}

}
