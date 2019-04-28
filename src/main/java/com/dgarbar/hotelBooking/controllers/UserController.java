package com.dgarbar.hotelBooking.controllers;

import com.dgarbar.hotelBooking.model.dto.UserDto;
import com.dgarbar.hotelBooking.service.UserService;
import com.dgarbar.hotelBooking.service.exception.RepositoryOperationException;
import com.dgarbar.hotelBooking.service.exception.UserNotFoundException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@GetMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.FOUND)
	public UserDto getUserById(@PathVariable Long id) throws UserNotFoundException {
		return userService.getUserById(id);
	}

	@GetMapping(path = "/{id}/booking")
	@ResponseStatus(HttpStatus.FOUND)
	public UserDto getUserWithBookingById(@PathVariable Long id) throws UserNotFoundException {
		return userService.getUserInfo(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserDto saveUser(@Valid @RequestBody UserDto user) throws RepositoryOperationException {
		return userService.save(user);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void saveUser(@PathVariable Long id) {
		userService.remove(id);
	}


}
