package com.dgarbar.hotelBooking.service;

import com.dgarbar.hotelBooking.model.dto.RoomDto;
import com.dgarbar.hotelBooking.model.entity.Room;
import com.dgarbar.hotelBooking.model.entity.RoomCategory;
import com.dgarbar.hotelBooking.model.mappers.RoomMapper;
import com.dgarbar.hotelBooking.repo.RoomRepository;
import com.dgarbar.hotelBooking.repo.BookingRepository;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookingService {

	private BookingRepository bookingRepository;
	private RoomRepository roomRepository;
	private RoomMapper roomMapper;

	public BookingService(BookingRepository bookingRepository,
		RoomRepository roomRepository, RoomMapper roomMapper) {
		this.bookingRepository = bookingRepository;
		this.roomRepository = roomRepository;
		this.roomMapper = roomMapper;
	}

	public List<RoomDto> getRooms(RoomCategory category, Date date) {
		List<Room> roomsByCategory = roomRepository.findAllByCategoryIs(category);
		return roomsByCategory.stream()
			.map(roomMapper::toDto)
			.collect(Collectors.toList());
	}

}
