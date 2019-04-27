package com.dgarbar.hotelBooking.service;

import com.dgarbar.hotelBooking.model.dto.BookingDto;
import com.dgarbar.hotelBooking.model.dto.RoomDto;
import com.dgarbar.hotelBooking.model.entity.Booking;
import com.dgarbar.hotelBooking.model.entity.Room;
import com.dgarbar.hotelBooking.model.entity.RoomCategory;
import com.dgarbar.hotelBooking.model.entity.User;
import com.dgarbar.hotelBooking.model.mappers.InnerBookingMapper;
import com.dgarbar.hotelBooking.model.mappers.InnerRoomMapper;
import com.dgarbar.hotelBooking.repo.BookingRepository;
import com.dgarbar.hotelBooking.repo.RoomRepository;
import com.dgarbar.hotelBooking.repo.UserRepository;
import com.dgarbar.hotelBooking.service.exception.DateIsOverlapException;
import com.dgarbar.hotelBooking.service.exception.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookingService {

	private BookingRepository bookingRepository;
	private RoomRepository roomRepository;
	private UserRepository userRepository;
	private InnerRoomMapper innerRoomMapper;
	private InnerBookingMapper bookingMapper;

	public BookingService(BookingRepository bookingRepository,
		RoomRepository roomRepository, UserRepository userRepository,
		InnerRoomMapper innerRoomMapper,
		InnerBookingMapper bookingMapper) {
		this.bookingRepository = bookingRepository;
		this.roomRepository = roomRepository;
		this.userRepository = userRepository;
		this.innerRoomMapper = innerRoomMapper;
		this.bookingMapper = bookingMapper;
	}

	public List<RoomDto> getRooms(RoomCategory category) {
		List<Room> roomsByCategory = roomRepository.getAllByCategory(category);
		return innerRoomMapper.toDtoList(roomsByCategory);
	}

	public List<RoomDto> getRooms(LocalDate date) {
		List<Room> roomsByCategory = roomRepository.getRoomThatNotBooked(date);
		return innerRoomMapper.toDtoList(roomsByCategory);
	}

	public List<RoomDto> getAllRooms() {
		List<Room> allRooms = roomRepository.findAll();
		return innerRoomMapper.toDtoList(allRooms);
	}

	public List<BookingDto> getAllBooking() {
		return bookingMapper.toDtoList(bookingRepository.getAllBookingEagerly());
	}

	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public void saveBooking(Long userId, Long roomId, LocalDate fromDate, LocalDate toDate)
		throws EntityNotFoundException, DateIsOverlapException {
		User user = userRepository.getOne(userId);
		Room room = roomRepository.getOne(roomId);
		validateDatesValue(fromDate, toDate);
		if (isOverlapForDates(fromDate, toDate, room)) {
			throw new DateIsOverlapException();
		}
		Booking booking = new Booking();
		booking.setStartDate(fromDate);
		booking.setFinishDate(toDate);
		user.addBooking(booking);
		room.addBooking(booking);
		bookingRepository.save(booking);
	}

	private boolean isOverlapForDates(LocalDate fromDate, LocalDate toDate, Room room) {
		return !bookingRepository
			.getBookingsByFinishDateGreaterThanEqualAndStartDateLessThanEqualAndRoom(fromDate,
				toDate, room).isEmpty();
	}

	private void validateDatesValue(LocalDate fromDate, LocalDate toDate)
		throws EntityNotFoundException {
		if (!toDate.isEqual(fromDate)) {
			if (!fromDate.isBefore(toDate)) {
				throw new EntityNotFoundException();
			}
		}
	}

}
