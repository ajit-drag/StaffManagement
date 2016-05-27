package com.mindtree.coe.staffmanagement.exceptions;

public class NoResultException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4192054718502509257L;

	public NoResultException() {

	}

	public NoResultException(String message) {
		super(message);

	}

	public NoResultException(Throwable cause) {
		super(cause);

	}

	public NoResultException(String message, Throwable cause) {
		super(message, cause);

	}

	public NoResultException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
