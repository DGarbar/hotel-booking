package com.dgarbar.hotelBooking.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.dgarbar.hotelBooking.model.dto.BookingDto;
import com.dgarbar.hotelBooking.model.dto.RoomDto;
import com.dgarbar.hotelBooking.model.dto.RoomServiceDto;
import com.dgarbar.hotelBooking.model.dto.UserDto;
import com.dgarbar.hotelBooking.model.entity.User;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import org.junit.Test;

public class PriceCalculatorTest {

	private PriceCalculator priceCalculator = new PriceCalculator();
	private RoomDto roomWithoutServiceDto = RoomDto.builder()
		.price(new BigDecimal(210.50))
		.build();

	private RoomServiceDto roomServiceDto1 = RoomServiceDto
		.builder()
		.price(new BigDecimal(10.3))
		.build();

	private RoomServiceDto roomServiceDto2 = RoomServiceDto
		.builder()
		.price(new BigDecimal(100.1))
		.build();

	private RoomDto roomWithService = RoomDto.builder()
		.price(BigDecimal.valueOf(201.3))
		.roomService(List.of(roomServiceDto1, roomServiceDto2))
		.build();

	private BookingDto bookingWithRoomWithoutServicesOneDay = BookingDto.builder()
		.price(BigDecimal.valueOf(1200))
		.startDate(LocalDate.of(2018, 10, 10))
		.finishDate(LocalDate.of(2018, 10, 10))
		.room(roomWithoutServiceDto)
		.build();


	private BookingDto bookingWithRoomWithServicesThreeDay = BookingDto.builder()
		.price(BigDecimal.valueOf(10))
		.startDate(LocalDate.of(2018, 10, 10))
		.finishDate(LocalDate.of(2018, 10, 12))
		.room(roomWithService)
		.build();

	private UserDto userWithBooking = UserDto.builder()
		.bookingDtos(List.of(bookingWithRoomWithoutServicesOneDay,bookingWithRoomWithServicesThreeDay))
		.totalPrice(BigDecimal.valueOf(1010,23))
		.build();

private UserDto userWithoutBooking = UserDto.builder()
		.totalPrice(BigDecimal.valueOf(1010,23))
		.build();

	@Test
	public void testRoomWithoutServiceCost() {
		BigDecimal expected = BigDecimal.valueOf(210.50);
		BigDecimal result = priceCalculator.fullRoomPrice(roomWithoutServiceDto);

		assertEquals(expected, result);
	}

	@Test
	public void testRoomWithServicesCost() {
		BigDecimal expected = BigDecimal.valueOf(311.7);
		BigDecimal result = priceCalculator.fullRoomPrice(roomWithService);

		assertEquals(expected.doubleValue(), result.doubleValue(),0.0001);
	}

	@Test
	public void testRoomCostNotChangeAfterServicesCost() {
		BigDecimal expected = roomWithService.getPrice();
		priceCalculator.fullRoomPrice(roomWithService);
		BigDecimal result = roomWithService.getPrice();
		assertEquals(expected, result);
	}


	@Test
	public void testBookingWithRoomWithoutServicesOneDay() {
		BigDecimal expected = BigDecimal.valueOf(210.5);
		BigDecimal result = priceCalculator.recalculateBookingPrice(bookingWithRoomWithoutServicesOneDay);

		assertEquals(expected, result);
	}

	@Test
	public void testBookingWithRoomWithoutServicesPriceChange() {
		BigDecimal notExpected = bookingWithRoomWithoutServicesOneDay.getPrice();
		priceCalculator.recalculateBookingPrice(bookingWithRoomWithoutServicesOneDay);
		BigDecimal result = bookingWithRoomWithoutServicesOneDay.getPrice();
		assertNotEquals(notExpected, result);
	}

	@Test
	public void testBookingWithRoomWithServices() {
		BigDecimal expected = BigDecimal.valueOf(935.1);
		BigDecimal result = priceCalculator.recalculateBookingPrice(bookingWithRoomWithServicesThreeDay);

		assertEquals(expected.doubleValue(), result.doubleValue(),0.00001);
	}

	@Test
	public void testUserTotalPriceWithBookings() {
		BigDecimal expected = BigDecimal.valueOf(1145.6);
		BigDecimal result = priceCalculator.recalculateUserPrice(userWithBooking);

		assertNotEquals(expected, result);
	}

	@Test
	public void testUserWithEmptyBookings(){
		BigDecimal expected = BigDecimal.ZERO;
		BigDecimal result = priceCalculator.recalculateUserPrice(userWithoutBooking);

		assertEquals(expected, result);
	}
}