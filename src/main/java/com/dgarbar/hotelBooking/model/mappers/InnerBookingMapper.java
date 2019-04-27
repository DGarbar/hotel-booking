package com.dgarbar.hotelBooking.model.mappers;

import com.dgarbar.hotelBooking.model.dto.BookingDto;
import com.dgarbar.hotelBooking.model.entity.Booking;
import org.springframework.stereotype.Component;

@Component
public class InnerBookingMapper implements DtoMapper<BookingDto, Booking> {

	private InnerRoomMapper roomMapper;

	public InnerBookingMapper(
		InnerRoomMapper roomMapper) {
		this.roomMapper = roomMapper;
	}

	@Override
	public BookingDto toDto(Booking booking) {
		return BookingDto.builder()
			.id(booking.getId())
			.startDate(booking.getStartDate())
			.finishDate(booking.getFinishDate())
			.room(roomMapper.toDto(booking.getRoom()))
			.build();
	}
}
