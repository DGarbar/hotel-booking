package com.dgarbar.hotelBooking.model.mappers;

import com.dgarbar.hotelBooking.model.dto.RoomDto;
import com.dgarbar.hotelBooking.model.entity.Room;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {

	public RoomDto toDto(Room room) {
		return RoomDto.builder()
			.category(room.getCategory())
			.number(room.getNumber())
			.price(room.getPrice())
			.build();
	}
}
