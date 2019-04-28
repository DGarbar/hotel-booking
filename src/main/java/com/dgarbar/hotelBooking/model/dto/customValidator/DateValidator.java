package com.dgarbar.hotelBooking.model.dto.customValidator;

import com.dgarbar.hotelBooking.model.dto.BookingOrder;
import com.dgarbar.hotelBooking.service.exception.DateValidationException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<CheckDate, BookingOrder> {

	@Override
	public void initialize(CheckDate constraintAnnotation) {
	}

	@Override
	public boolean isValid(BookingOrder value, ConstraintValidatorContext context) {
		try {
			com.dgarbar.hotelBooking.service.DateValidator
				.validateDatesValue(value.getFromDate(), value.getToDate());
			return true;
		} catch (DateValidationException e) {
			return false;
		}
	}
}
