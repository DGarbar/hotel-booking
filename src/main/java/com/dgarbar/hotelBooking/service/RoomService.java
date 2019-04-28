package com.dgarbar.hotelBooking.service;

import com.dgarbar.hotelBooking.model.dto.RoomDto;
import com.dgarbar.hotelBooking.model.entity.Room;
import com.dgarbar.hotelBooking.model.entity.RoomCategory;
import com.dgarbar.hotelBooking.model.mappers.InnerRoomMapper;
import com.dgarbar.hotelBooking.repo.RoomRepository;
import com.dgarbar.hotelBooking.service.exception.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
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

	//Smelly better use criteria
	@Transactional(readOnly = true)
	public List<RoomDto> getRooms(RoomCategory category, LocalDate from, LocalDate to) throws  IllegalArgumentException {
		if (from == null || to == null) {
			if (category != null) {
				return getRooms(category);
			} else {
				return getAllRooms();
			}
		} else {
			return getRooms(from, to);
		}
	}

	@Transactional(readOnly = true)
	public RoomDto getRoomById(Long id) throws EntityNotFoundException {
		return roomRepository.getRoomByIdEagerly(id)
			.map(innerRoomMapper::toDto)
			.orElseThrow(EntityNotFoundException::new);
	}

	@Transactional(readOnly = true)
	public List<RoomDto> getRooms(RoomCategory category) {
		Set<Room> roomsByCategory = roomRepository.getRoomByCategoryEagerly(category);
		return innerRoomMapper.toDtoList(roomsByCategory);
	}

	@Transactional(readOnly = true)
	public List<RoomDto> getRooms(LocalDate date) {
		Set<Room> roomsByCategory = roomRepository.getRoomEagerlyThatNotBooked(date);
		return innerRoomMapper.toDtoList(roomsByCategory);
	}

	@Transactional(readOnly = true)
	public List<RoomDto> getRooms(LocalDate from, LocalDate to) throws  IllegalArgumentException {
		Set<Room> roomsByCategory = roomRepository.getRoomEagerlyThatNotBooked(from, to);
		return innerRoomMapper.toDtoList(roomsByCategory);
	}

	@Transactional(readOnly = true)
	public List<RoomDto> getAllRooms() {
		Set<Room> allRooms = roomRepository.getRoomEagerly();
		return innerRoomMapper.toDtoList(allRooms);
	}

}
