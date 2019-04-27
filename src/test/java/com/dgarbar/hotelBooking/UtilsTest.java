package com.dgarbar.hotelBooking;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.Assert.assertEquals;

import java.sql.Date;
import org.junit.Test;

public class UtilsTest {

	@Test
	public void testOneDayBeaten() {
		Date from = Date.valueOf("2018-01-23");
		Date to = Date.valueOf("2018-01-23");
		long days = DAYS.between(from.toLocalDate(), to.toLocalDate());
		assertEquals(1,days);
	}


	@Test
	public void testTwoDaysBeaten() {
		Date from = Date.valueOf("2018-02-23");
		Date to = Date.valueOf("2018-02-24");

		long days = DAYS.between(from.toLocalDate(), to.toLocalDate());
		assertEquals(2,days);
	}
}
