package com.dgarbar.hotelBooking.model.mappers;

import com.dgarbar.hotelBooking.model.dto.RoomDto;
import com.dgarbar.hotelBooking.model.dto.UserDto;
import com.dgarbar.hotelBooking.model.entity.Room;
import com.dgarbar.hotelBooking.model.entity.User;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class SimpleUserMapper implements DtoMapper<UserDto, User> {

	public UserDto toDto(User user) {
		return UserDto.builder()
			.id(user.getId())
			.login(user.getLogin())
			.build();
	}

	public User toEntity(UserDto userDto) {
		User user = new User();
		user.setLogin(userDto.getLogin());
		return user;
	}
}
