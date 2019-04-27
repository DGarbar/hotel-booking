package com.dgarbar.hotelBooking.service;

import static org.junit.Assert.*;

import com.dgarbar.hotelBooking.model.dto.UserDto;
import com.dgarbar.hotelBooking.service.exception.UserNotFoundException;
import java.math.BigDecimal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	public void checkTotalPriceOnExistingUser() throws UserNotFoundException {
		UserDto userInfo = userService.getUserInfo(2L);

		assertFalse(userInfo.getBookingDtos().isEmpty());
	}

	@Test(expected = UserNotFoundException.class)
	public void getExceptionOnNotExcitingUser() throws UserNotFoundException {
		UserDto userInfo = userService.getUserInfo(200L);
	}
}