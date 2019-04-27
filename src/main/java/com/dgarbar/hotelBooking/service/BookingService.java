package com.dgarbar.hotelBooking.service;

import com.dgarbar.hotelBooking.model.dto.RoomDto;
import com.dgarbar.hotelBooking.model.entity.Room;
import com.dgarbar.hotelBooking.model.entity.RoomCategory;
import com.dgarbar.hotelBooking.model.mappers.InnerRoomMapper;
import com.dgarbar.hotelBooking.repo.RoomRepository;
import java.sql.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookingService {

	private RoomRepository roomRepository;
	private InnerRoomMapper innerRoomMapper;

	public BookingService(RoomRepository roomRepository, InnerRoomMapper innerRoomMapper) {
		this.roomRepository = roomRepository;
		this.innerRoomMapper = innerRoomMapper;
	}

	public List<RoomDto> getRooms(RoomCategory category) {
		List<Room> roomsByCategory = roomRepository.getAllByCategory(category);
		return innerRoomMapper.toDtoList(roomsByCategory);
	}

	public List<RoomDto> getRooms(Date date) {
		List<Room> roomsByCategory = roomRepository.getRoomThatNotBooked(date);
		return innerRoomMapper.toDtoList(roomsByCategory);
	}

	public List<RoomDto> getAllRooms() {
		List<Room> allRooms = roomRepository.findAll();
		return innerRoomMapper.toDtoList(allRooms);
	}


}
