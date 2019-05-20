package com.dgarbar.hotelBooking.service;

import com.dgarbar.hotelBooking.model.dto.BookingDto;
import com.dgarbar.hotelBooking.model.dto.RoomDto;
import com.dgarbar.hotelBooking.model.dto.RoomServiceDto;
import com.dgarbar.hotelBooking.model.dto.UserDto;
import java.math.BigDecimal;
import java.util.List;

public interface PriceCalculator {

	BigDecimal recalculateUserPrice(UserDto userDto);

	BigDecimal recalculateBookingPrice(BookingDto bookingDto);

	BigDecimal fullRoomPrice(RoomDto roomDto);

	BigDecimal getRoomServicePrice(List<RoomServiceDto> roomServiceDtos);
}
