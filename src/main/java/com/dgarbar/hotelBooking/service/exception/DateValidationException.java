package com.dgarbar.hotelBooking.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DateValidationException extends Exception {

	public DateValidationException() {
	}

	public DateValidationException(String message) {
		super(message);
	}

	public DateValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DateValidationException(Throwable cause) {
		super(cause);
	}

	public DateValidationException(String message, Throwable cause, boolean enableSuppression,
		boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
