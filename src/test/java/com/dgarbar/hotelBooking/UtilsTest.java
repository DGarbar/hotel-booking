package com.dgarbar.hotelBooking;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.time.LocalDate;
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
		LocalDate from = LocalDate.of(2018, 10, 23);
		LocalDate to = LocalDate.of(2018, 10, 24);

		long days = DAYS.between(from, to);
		assertEquals(2,days);
	}

	@Test
	public void testAfterDate() {
		Date from = Date.valueOf("2018-01-23");
		Date to = Date.valueOf("2018-01-23");
		assertTrue(to.after(from));
	}
}
