package com.dgarbar.hotelBooking.controllers;

import com.dgarbar.hotelBooking.model.dto.UserDto;
import com.dgarbar.hotelBooking.service.UserService;
import com.dgarbar.hotelBooking.service.exception.UserNotFoundException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<UserDto> getAllUsers() {
		return userService.getAllUser();
	}

	//UserDto have prices for all bookings
	@GetMapping(path = "/{id}", produces = "application/json")
	public UserDto getUserById(@PathVariable Long id) {
		try {
			return userService.getUserInfo(id);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void saveUser(UserDto user) {
		userService.save(user);
	}
}
