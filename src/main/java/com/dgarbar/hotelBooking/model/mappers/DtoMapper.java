package com.dgarbar.hotelBooking.model.mappers;

import com.dgarbar.hotelBooking.model.dto.RoomServiceDto;
import com.dgarbar.hotelBooking.model.entity.RoomService;
import com.dgarbar.hotelBooking.model.entity.Service;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface DtoMapper<DTO,ENTITY> {
	DTO toDto(ENTITY roomService);

	default List<DTO> toDtoList(Collection<ENTITY> entities) {
		return entities.stream()
			.map(this::toDto)
			.collect(Collectors.toList());
	}
}
