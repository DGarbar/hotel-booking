package com.dgarbar.hotelBooking.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DateIsOverlapException extends Exception {

	public DateIsOverlapException() {
	}

	public DateIsOverlapException(String message) {
		super(message);
	}

	public DateIsOverlapException(String message, Throwable cause) {
		super(message, cause);
	}

	public DateIsOverlapException(Throwable cause) {
		super(cause);
	}

	public DateIsOverlapException(String message, Throwable cause, boolean enableSuppression,
		boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
