package com.dgarbar.hotelBooking.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RepositoryOperationException extends Exception {

	public RepositoryOperationException() {
	}

	public RepositoryOperationException(String message) {
		super(message);
	}

	public RepositoryOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepositoryOperationException(Throwable cause) {
		super(cause);
	}

	public RepositoryOperationException(String message, Throwable cause, boolean enableSuppression,
		boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
