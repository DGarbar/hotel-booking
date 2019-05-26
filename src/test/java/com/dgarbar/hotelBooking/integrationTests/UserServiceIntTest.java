package com.dgarbar.hotelBooking.integrationTests;

import static org.junit.Assert.assertTrue;

import com.dgarbar.hotelBooking.integrationTests.myContainer.MyPostgreSqlContainer;
import com.dgarbar.hotelBooking.model.entity.User;
import com.dgarbar.hotelBooking.repo.UserRepository;
import java.util.Optional;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("integrationTest")
public class UserServiceIntTest extends PostgreSQLContainer {

	@ClassRule
	public static MyPostgreSqlContainer myPostgreSqlContainer = MyPostgreSqlContainer.getInstance();

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testUserByIdExist() {
		Optional<User> userByIdEagerly = userRepository.getUserByIdEagerly(1L);
		assertTrue(userByIdEagerly.isPresent());
	}

}


