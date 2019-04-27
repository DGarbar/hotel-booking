package com.dgarbar.hotelBooking.controllers;

import com.dgarbar.hotelBooking.model.dto.BookingDto;
import com.dgarbar.hotelBooking.model.dto.RoomDto;
import com.dgarbar.hotelBooking.model.entity.Booking;
import com.dgarbar.hotelBooking.model.entity.Room;
import com.dgarbar.hotelBooking.model.entity.RoomCategory;
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

	//Get all rooms with possible dates to book
	//Or use Specification<Room>
	@GetMapping
	public List<RoomDto> getRooms(
		@RequestParam(value = "category",required = false) RoomCategory category,
		@RequestParam(value = "date", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date date){
		System.out.println(category);
		return null;
	}

	@GetMapping("/bookings")
	public List<BookingDto> getAllBooking(){
		return null;
	}

	//Use Specification<Room>
	@PostMapping("/bookings")
	@ResponseStatus(HttpStatus.CREATED)
	public void addBooking(
		@RequestParam("userId") String userId,
		@RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate,
		@RequestParam("to") @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate){

	}

}
