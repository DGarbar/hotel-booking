package com.dgarbar.hotelBooking.service.Util;

import com.dgarbar.hotelBooking.service.exception.DateValidationException;
import java.time.LocalDate;

public class DateValidator {

	public static void validateDatesValue(LocalDate fromDate, LocalDate toDate)
		throws DateValidationException {
		if(toDate != null && fromDate != null)
		if (!toDate.isEqual(fromDate)) {
			if (!fromDate.isBefore(toDate)) {
				throw new DateValidationException();
			}
		}
	}

}
