package com.dgarbar.hotelBooking.service;

import static java.time.temporal.ChronoUnit.DAYS;

import com.dgarbar.hotelBooking.model.dto.BookingDto;
import com.dgarbar.hotelBooking.model.dto.RoomDto;
import com.dgarbar.hotelBooking.model.dto.RoomServiceDto;
import com.dgarbar.hotelBooking.model.dto.UserDto;
import java.math.BigDecimal;
import java.sql.Date;
import org.springframework.stereotype.Component;

@Component
public class PriceCalculator {

	public BigDecimal recalculateUserPrice(UserDto userDto) {
		BigDecimal totalPrice = userDto.getBookingDtos()
			.stream()
			.map(this::recalculateBookingPrice)
			.reduce(BigDecimal.ZERO, BigDecimal::add);
		userDto.setTotalPrice(totalPrice);
		return totalPrice;
	}

	public BigDecimal recalculateBookingPrice(BookingDto bookingDto) {
		Date startDate = bookingDto.getStartDate();
		long duration = DAYS.between(startDate.toLocalDate(), startDate.toLocalDate()) + 1;
		RoomDto room = bookingDto.getRoom();
		BigDecimal bookingPrice = recalculateRoomPrice(room).multiply(BigDecimal.valueOf(duration));
		bookingDto.setPrice(bookingPrice);
		return bookingPrice;
	}

	public BigDecimal recalculateRoomPrice(RoomDto roomDto) {
		BigDecimal price = roomDto.getPrice();
		BigDecimal roomPrice = roomDto.getRoomService()
			.stream()
			.map(RoomServiceDto::getPrice)
			.reduce(price, BigDecimal::add);
		roomDto.setPrice(roomPrice);
		return roomPrice;
	}
}
