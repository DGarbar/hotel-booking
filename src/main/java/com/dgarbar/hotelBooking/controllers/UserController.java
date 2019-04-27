package com.dgarbar.hotelBooking.controllers;

import com.dgarbar.hotelBooking.model.dto.UserDto;
import com.dgarbar.hotelBooking.repo.BookingRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	private BookingRepository userRepository;

	public UserController(BookingRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping
	public List<UserDto> getAllUsers(){
		return null;
	}

	//UserDto have prices for all bookings
	@GetMapping(path = "/{id}", produces = "application/json")
	public UserDto getUserById(@PathVariable Long id){
		return null;
	}

	@PostMapping
	public void saveUser(UserDto user){

	}
}
