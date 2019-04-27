package com.dgarbar.hotelBooking.service;

import static java.time.temporal.ChronoUnit.DAYS;

import com.dgarbar.hotelBooking.model.dto.BookingDto;
import com.dgarbar.hotelBooking.model.dto.RoomDto;
import com.dgarbar.hotelBooking.model.dto.RoomServiceDto;
import com.dgarbar.hotelBooking.model.dto.UserDto;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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
		LocalDate startDate = bookingDto.getStartDate();
		LocalDate finishDate = bookingDto.getFinishDate();

		long duration = DAYS.between(startDate, finishDate) + 1;
		RoomDto room = bookingDto.getRoom();
		BigDecimal bookingPrice = fullRoomPrice(room).multiply(BigDecimal.valueOf(duration));
		bookingDto.setPrice(bookingPrice);
		return bookingPrice;
	}

	public BigDecimal fullRoomPrice(RoomDto roomDto) {
		BigDecimal price = roomDto.getPrice();
		List<RoomServiceDto> roomService = roomDto.getRoomService();
		BigDecimal roomServicePrice = getRoomServicePrice(roomService);
		return price.add(roomServicePrice);
	}

	public BigDecimal getRoomServicePrice(List<RoomServiceDto> roomServiceDtos) {
		return roomServiceDtos
			.stream()
			.map(RoomServiceDto::getPrice)
			.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}
