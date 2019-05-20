package com.dgarbar.hotelBooking.service;

import com.dgarbar.hotelBooking.model.dto.RoomDto;
import com.dgarbar.hotelBooking.model.entity.RoomCategory;
import com.dgarbar.hotelBooking.service.exception.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface RoomService {

	//Smelly better use criteria
	@Transactional(readOnly = true)
	List<RoomDto> getRooms(RoomCategory category, LocalDate from,
		LocalDate to) throws  IllegalArgumentException;

	@Transactional(readOnly = true)
	RoomDto getRoomById(Long id) throws EntityNotFoundException;

	@Transactional(readOnly = true)
	List<RoomDto> getRooms(RoomCategory category);

	@Transactional(readOnly = true)
	List<RoomDto> getRooms(LocalDate date);

	@Transactional(readOnly = true)
	List<RoomDto> getRooms(LocalDate from, LocalDate to) throws  IllegalArgumentException;

	@Transactional(readOnly = true)
	List<RoomDto> getAllRooms();
}
