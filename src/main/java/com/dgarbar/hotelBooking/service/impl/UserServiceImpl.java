package com.dgarbar.hotelBooking.service.impl;

import com.dgarbar.hotelBooking.model.dto.UserDto;
import com.dgarbar.hotelBooking.model.entity.User;
import com.dgarbar.hotelBooking.model.mappers.InnerUserMapper;
import com.dgarbar.hotelBooking.model.mappers.SimpleUserMapper;
import com.dgarbar.hotelBooking.repo.UserRepository;
import com.dgarbar.hotelBooking.service.PriceCalculator;
import com.dgarbar.hotelBooking.service.UserService;
import com.dgarbar.hotelBooking.service.exception.RepositoryOperationException;
import com.dgarbar.hotelBooking.service.exception.UserNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private SimpleUserMapper simpleUserMapper;
	private InnerUserMapper innerUserMapper;
	private PriceCalculator priceCalculator;

	public UserServiceImpl(UserRepository userRepository,
		SimpleUserMapper simpleUserMapper,
		InnerUserMapper innerUserMapper,
		PriceCalculator priceCalculator) {
		this.userRepository = userRepository;
		this.simpleUserMapper = simpleUserMapper;
		this.innerUserMapper = innerUserMapper;
		this.priceCalculator = priceCalculator;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public UserDto save(UserDto userDto) throws RepositoryOperationException {
		try {
			User user = simpleUserMapper.toEntity(userDto);
			User savedUser = userRepository.save(user);
			return simpleUserMapper.toDto(savedUser);
		} catch (Exception e) {
			throw new RepositoryOperationException(e);
		}
	}

	@Override
	public void remove(Long id) {
			userRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public UserDto getUserInfo(Long id) throws UserNotFoundException {
		return userRepository.getUserByIdEagerly(id)
			.map(user -> {
				UserDto userDto = innerUserMapper.toDto(user);
				priceCalculator.recalculateUserPrice(userDto);
				return userDto;
			}).orElseThrow(
				() -> new UserNotFoundException(String.format("user with, %d is not exist", id)));
	}

	@Override
	@Transactional(readOnly = true)
	public UserDto getUserById(Long id) throws UserNotFoundException {
		return userRepository.findById(id)
			.map(simpleUserMapper::toDto)
			.orElseThrow(
				() -> new UserNotFoundException(String.format("user with, %d is not exist", id)));
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserDto> getAllUser() {
		return simpleUserMapper.toDtoList(userRepository.findAll());
	}

}
