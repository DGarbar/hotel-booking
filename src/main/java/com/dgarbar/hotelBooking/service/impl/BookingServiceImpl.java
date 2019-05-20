package com.dgarbar.hotelBooking.service.impl;

import com.dgarbar.hotelBooking.model.dto.BookingDto;
import com.dgarbar.hotelBooking.model.dto.BookingOrder;
import com.dgarbar.hotelBooking.model.entity.Booking;
import com.dgarbar.hotelBooking.model.entity.Room;
import com.dgarbar.hotelBooking.model.entity.User;
import com.dgarbar.hotelBooking.model.mappers.InnerBookingMapper;
import com.dgarbar.hotelBooking.repo.BookingRepository;
import com.dgarbar.hotelBooking.repo.RoomRepository;
import com.dgarbar.hotelBooking.repo.UserRepository;
import com.dgarbar.hotelBooking.service.BookingService;
import com.dgarbar.hotelBooking.service.exception.DateIsOverlapException;
import com.dgarbar.hotelBooking.service.exception.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

	private BookingRepository bookingRepository;
	private UserRepository userRepository;
	private RoomRepository roomRepository;
	private InnerBookingMapper bookingMapper;

	public BookingServiceImpl(BookingRepository bookingRepository,
		UserRepository userRepository,
		RoomRepository roomRepository,
		InnerBookingMapper bookingMapper) {
		this.bookingRepository = bookingRepository;
		this.userRepository = userRepository;
		this.roomRepository = roomRepository;
		this.bookingMapper = bookingMapper;
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookingDto> getAllBooking() {
		return bookingMapper.toDtoList(bookingRepository.getAllBookingEagerly());
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public BookingDto saveBooking(BookingOrder bookingOrder)
		throws DateIsOverlapException, EntityNotFoundException, PersistenceException {
		System.out.println(bookingOrder.toString());
		User user = userRepository.getOne(bookingOrder.getUserId());
		Room room = roomRepository.getRoomByIdEagerly(bookingOrder.getRoomId())
			.orElseThrow(EntityNotFoundException::new);
		LocalDate fromDate = bookingOrder.getFromDate();
		LocalDate toDate = bookingOrder.getToDate();
		if (isOverlapForDates(fromDate, toDate, room)) {
			throw new DateIsOverlapException();
		}
		Booking booking = new Booking();
		booking.setStartDate(fromDate);
		booking.setFinishDate(toDate);
		user.addBooking(booking);
		room.addBooking(booking);
		Booking savedBooking = bookingRepository.save(booking);
		return bookingMapper.toDto(savedBooking);
	}

	private boolean isOverlapForDates(LocalDate fromDate, LocalDate toDate, Room room) {
		return !bookingRepository
			.getBookingsThatOverlapWithDatesByRoom(fromDate,
				toDate, room).isEmpty();
	}

}
