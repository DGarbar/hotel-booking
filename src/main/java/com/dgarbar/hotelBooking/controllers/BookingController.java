package com.dgarbar.hotelBooking.controllers;

import com.dgarbar.hotelBooking.model.dto.BookingDto;
import com.dgarbar.hotelBooking.model.dto.RoomDto;
import com.dgarbar.hotelBooking.model.entity.RoomCategory;
import com.dgarbar.hotelBooking.service.BookingService;
import com.dgarbar.hotelBooking.service.exception.DateIsOverlapException;
import com.dgarbar.hotelBooking.service.exception.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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

	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	//Get all rooms with possible dates to book
	//Or use Specification<Room>
	@GetMapping
	public List<RoomDto> getRooms(
		@RequestParam(value = "category", required = false) RoomCategory category,
		@RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

		if (category != null) {
			return bookingService.getRooms(category);
		}
		if (date != null) {
			return bookingService.getRooms(date);
		}
		return bookingService.getAllRooms();
	}

	@GetMapping("/bookings")
	public List<BookingDto> getAllBooking() {
		return bookingService.getAllBooking();
	}

	//Use Specification<Room>
	@PostMapping("/bookings")
	@ResponseStatus(HttpStatus.CREATED)
	public void addBooking(
		@RequestParam("userId") Long userId,
		@RequestParam("roomId") Long roomId,
		@RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
		@RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate)
		throws DateIsOverlapException, EntityNotFoundException {
		bookingService.saveBooking(userId, roomId, fromDate, toDate);
	}

}
