package com.dgarbar.hotelBooking.controllers;

import com.dgarbar.hotelBooking.model.dto.UserDto;
import com.dgarbar.hotelBooking.service.UserService;
import com.dgarbar.hotelBooking.service.exception.UserNotFoundException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@GetMapping(path = "/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
		try {
			UserDto userInfo = userService.getUserById(id);
			return new ResponseEntity<>(userInfo, HttpStatus.FOUND);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/{id}/booking")
	public ResponseEntity<UserDto> getUserWithBookingById(@PathVariable Long id) {
		try {
			UserDto userInfo = userService.getUserInfo(id);
			return new ResponseEntity<>(userInfo, HttpStatus.FOUND);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto user) {
		try {
			UserDto userDto = userService.save(user);
			return new ResponseEntity<>(userDto, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
}
