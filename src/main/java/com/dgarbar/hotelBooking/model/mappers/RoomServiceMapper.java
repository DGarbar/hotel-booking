package com.dgarbar.hotelBooking.model.mappers;

import com.dgarbar.hotelBooking.model.dto.RoomDto;
import com.dgarbar.hotelBooking.model.dto.RoomServiceDto;
import com.dgarbar.hotelBooking.model.entity.Room;
import com.dgarbar.hotelBooking.model.entity.RoomService;
import com.dgarbar.hotelBooking.model.entity.Service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class RoomServiceMapper implements DtoMapper<RoomServiceDto, RoomService> {

	public RoomServiceDto toDto(RoomService roomService) {
		Service service = roomService.getService();
		return RoomServiceDto.builder()
			.name(service.getName())
			.price(service.getPrice())
			.build();
	}
}
