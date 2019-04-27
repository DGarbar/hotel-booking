package com.dgarbar.hotelBooking.controllers;

import com.dgarbar.hotelBooking.model.entity.User;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	@GetMapping
	public List<User> getAllUsers(){
		return null;
	}

	//UserDto have prices for all bookings
	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id){
		return null;
	}

	@PostMapping
	public void saveUser(User user){

	}
}
