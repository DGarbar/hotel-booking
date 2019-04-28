package com.dgarbar.hotelBooking.service;

import com.dgarbar.hotelBooking.model.dto.RoomDto;
import com.dgarbar.hotelBooking.model.entity.Room;
import com.dgarbar.hotelBooking.model.entity.RoomCategory;
import com.dgarbar.hotelBooking.model.mappers.InnerRoomMapper;
import com.dgarbar.hotelBooking.repo.RoomRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoomService {

	private RoomRepository roomRepository;
	private InnerRoomMapper innerRoomMapper;

	public RoomService(RoomRepository roomRepository,
		InnerRoomMapper innerRoomMapper) {
		this.roomRepository = roomRepository;
		this.innerRoomMapper = innerRoomMapper;
	}

	@Transactional(readOnly = true)
	public List<RoomDto> getRooms(RoomCategory category) {
		List<Room> roomsByCategory = roomRepository.getAllByCategory(category);
		return innerRoomMapper.toDtoList(roomsByCategory);
	}

	@Transactional(readOnly = true)
	public List<RoomDto> getRooms(LocalDate date) {
		List<Room> roomsByCategory = roomRepository.getRoomEagerlyThatNotBooked(date);
		return innerRoomMapper.toDtoList(roomsByCategory);
	}

	@Transactional(readOnly = true)
	public List<RoomDto> getRooms(LocalDate from, LocalDate to) {
		List<Room> roomsByCategory = roomRepository.getRoomEagerlyThatNotBooked(from, to);
		return innerRoomMapper.toDtoList(roomsByCategory);
	}

	@Transactional(readOnly = true)
	public List<RoomDto> getAllRooms() {
		List<Room> allRooms = roomRepository.findAll();
		return innerRoomMapper.toDtoList(allRooms);
	}
}
