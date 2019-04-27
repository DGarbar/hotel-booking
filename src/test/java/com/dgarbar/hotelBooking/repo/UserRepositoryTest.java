package com.dgarbar.hotelBooking.repo;

import static org.junit.Assert.*;

import com.dgarbar.hotelBooking.model.entity.User;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
	@Autowired
	private UserRepository userRepository;

	@Test
	public void testUserByIdExist() {
		Optional<User> userByIdEagerly = userRepository.getUserByIdEagerly(1L);
		assertTrue(userByIdEagerly.isPresent());
	}
	@Test
	public void testUserByIdNotExist() {
		Optional<User> userByIdEagerly = userRepository.getUserByIdEagerly(100L);
		assertTrue(userByIdEagerly.isEmpty());
	}
}