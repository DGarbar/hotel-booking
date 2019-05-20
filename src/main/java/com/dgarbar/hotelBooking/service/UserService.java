package com.dgarbar.hotelBooking.service;

import com.dgarbar.hotelBooking.model.dto.UserDto;
import com.dgarbar.hotelBooking.service.exception.RepositoryOperationException;
import com.dgarbar.hotelBooking.service.exception.UserNotFoundException;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

	@Transactional(rollbackFor = Exception.class)
	UserDto save(UserDto userDto) throws RepositoryOperationException;

	void remove(Long id);

	@Transactional(readOnly = true)
	UserDto getUserInfo(Long id) throws UserNotFoundException;

	@Transactional(readOnly = true)
	UserDto getUserById(Long id) throws UserNotFoundException;

	@Transactional(readOnly = true)
	List<UserDto> getAllUser();
}
