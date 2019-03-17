package com.gdl.schedule.service.ex;

public class UsernameNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8741564349758147779L;

	public UsernameNotFoundException() {
		super();
	}

	public UsernameNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UsernameNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsernameNotFoundException(String message) {
		super(message);
	}

	public UsernameNotFoundException(Throwable cause) {
		super(cause);
	}

}
