package com.dgarbar.hotelBooking.model.mappers;

import com.dgarbar.hotelBooking.model.dto.UserDto;
import com.dgarbar.hotelBooking.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class InnerUserMapper implements DtoMapper<UserDto, User> {

	private InnerBookingMapper bookingMapper;

	public InnerUserMapper(InnerBookingMapper bookingMapper) {
		this.bookingMapper = bookingMapper;
	}

	public UserDto toDto(User user) {
		return UserDto.builder()
			.id(user.getId())
			.login(user.getLogin())
			.bookingDtos(bookingMapper.toDtoList(user.getBookings()))
			.build();
	}
}
