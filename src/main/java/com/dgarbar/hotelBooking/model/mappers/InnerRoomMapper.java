package com.dgarbar.hotelBooking.model.mappers;

import com.dgarbar.hotelBooking.model.dto.RoomDto;
import com.dgarbar.hotelBooking.model.entity.Room;
import org.springframework.stereotype.Component;

@Component
public class InnerRoomMapper implements DtoMapper<RoomDto, Room> {

	private RoomServiceMapper roomServiceMapper;

	public InnerRoomMapper(RoomServiceMapper roomServiceMapper) {
		this.roomServiceMapper = roomServiceMapper;
	}

	public RoomDto toDto(Room room) {
		return RoomDto.builder()
			.id(room.getId())
			.category(room.getCategory())
			.number(room.getNumber())
			.price(room.getPrice())
			.roomService(roomServiceMapper.toDtoList(room.getRoomServices()))
			.build();
	}

}
