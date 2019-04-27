package com.dgarbar.hotelBooking.service;

import com.dgarbar.hotelBooking.model.dto.UserDto;
import com.dgarbar.hotelBooking.model.entity.User;
import com.dgarbar.hotelBooking.model.mappers.InnerUserMapper;
import com.dgarbar.hotelBooking.model.mappers.SimpleUserMapper;
import com.dgarbar.hotelBooking.repo.UserRepository;
import com.dgarbar.hotelBooking.service.exception.UserNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

	private UserRepository userRepository;
	private SimpleUserMapper simpleUserMapper;
	private InnerUserMapper innerUserMapper;
	private PriceCalculator priceCalculator;

	public UserService(UserRepository userRepository,
		SimpleUserMapper simpleUserMapper,
		InnerUserMapper innerUserMapper) {
		this.userRepository = userRepository;
		this.simpleUserMapper = simpleUserMapper;
		this.innerUserMapper = innerUserMapper;
	}

	public UserDto save(UserDto userDto) {
		User user = simpleUserMapper.toEntity(userDto);
		User savedUser = userRepository.save(user);
		return simpleUserMapper.toDto(savedUser);
	}

	public UserDto getUserInfo(Long id) throws UserNotFoundException {
		User user = userRepository.getUserByIdEagerly(id)
			.orElseThrow(
				() -> new UserNotFoundException(String.format("user with, %d is not exist", id)));
		UserDto userDto = innerUserMapper.toDto(user);
		priceCalculator.recalculateUserPrice(userDto);
		return userDto;
	}

	public List<UserDto> getAllUser(){
		return simpleUserMapper.toDtoList(userRepository.findAll());
	}


}
