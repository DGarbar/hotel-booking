package com.dgarbar.hotelBooking.controllers;

import com.dgarbar.hotelBooking.model.dto.BookingDto;
import com.dgarbar.hotelBooking.model.dto.RoomDto;
import com.dgarbar.hotelBooking.model.entity.RoomCategory;
import com.dgarbar.hotelBooking.service.BookingService;
import com.dgarbar.hotelBooking.service.RoomService;
import com.dgarbar.hotelBooking.service.exception.DateIsOverlapException;
import com.dgarbar.hotelBooking.service.exception.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel")
public class BookingController {

	private BookingService bookingService;
	private RoomService roomService;

	public BookingController(BookingService bookingService,
		RoomService roomService) {
		this.bookingService = bookingService;
		this.roomService = roomService;
	}

	//Get all rooms with possible dates to book
	//Or use Specification<Room>
	@GetMapping
	public List<RoomDto> getRooms(
		@RequestParam(value = "category", required = false) RoomCategory category,
		@RequestParam(value = "from", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
		@RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to) {
		if (category != null) {
			return roomService.getRooms(category);
		}
		if (from != null && to != null) {
			return roomService.getRooms(from, to);
		}
		return roomService.getAllRooms();
	}

	@GetMapping("/bookings")
	public List<BookingDto> getAllBooking() {
		return bookingService.getAllBooking();
	}

	//Use Specification<Room>
	@PostMapping("/bookings")
	public ResponseEntity<BookingDto> addBooking(
		@RequestParam("userId") Long userId,
		@RequestParam("roomId") Long roomId,
		@RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
		@RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate) {
		try {
			BookingDto bookingDto = bookingService.saveBooking(userId, roomId, fromDate, toDate);
			return new ResponseEntity<>(bookingDto, HttpStatus.CREATED);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (DateIsOverlapException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

}
