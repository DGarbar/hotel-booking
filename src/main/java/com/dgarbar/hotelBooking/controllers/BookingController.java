package com.dgarbar.hotelBooking.controllers;

import com.dgarbar.hotelBooking.model.dto.BookingDto;
import com.dgarbar.hotelBooking.model.dto.BookingOrder;
import com.dgarbar.hotelBooking.model.dto.RoomDto;
import com.dgarbar.hotelBooking.model.entity.RoomCategory;
import com.dgarbar.hotelBooking.service.BookingService;
import com.dgarbar.hotelBooking.service.DateValidator;
import com.dgarbar.hotelBooking.service.RoomService;
import com.dgarbar.hotelBooking.service.exception.DateIsOverlapException;
import com.dgarbar.hotelBooking.service.exception.DateValidationException;
import com.dgarbar.hotelBooking.service.exception.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	@ResponseStatus(HttpStatus.FOUND)
	public List<RoomDto> getRooms(
		@RequestParam(value = "category", required = false) RoomCategory category,
		@RequestParam(value = "from", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
		@RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to)
		throws DateValidationException {
		DateValidator.validateDatesValue(from, to);
		return roomService.getRooms(category, from, to);
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.FOUND)
	public RoomDto getRoomById(@PathVariable Long id) throws EntityNotFoundException {
		return roomService.getRoomById(id);
	}

	@GetMapping("/bookings")
	public List<BookingDto> getAllBooking() {
		return bookingService.getAllBooking();
	}

	@PostMapping("/bookings")
	@ResponseStatus(HttpStatus.CREATED)
	public BookingDto addBooking(@Valid @RequestBody BookingOrder bookingOrder)
		throws DateIsOverlapException, EntityNotFoundException {
		return bookingService.saveBooking(bookingOrder);
	}

}
