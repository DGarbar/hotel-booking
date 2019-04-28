package com.dgarbar.hotelBooking.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.dgarbar.hotelBooking.model.entity.Room;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomRepositoryTest {

	@Autowired
	private RoomRepository roomRepository;

	@PersistenceContext
	EntityManager entityManager;


	@Test
	public void getRoomsThatNotInBookedListSize() {
		LocalDate date = LocalDate.of(2018, 4, 23);
		List<Room> roomThatNotBooked = roomRepository.getRoomEagerlyThatNotBooked(date);
		assertEquals(6,roomThatNotBooked.size());
	}

	@Test
	public void getAllRoomsWhenInDateAllRoomsFree() {
		LocalDate date = LocalDate.of(2018, 4, 23);
		List<Room> roomThatNotBooked = roomRepository.getRoomEagerlyThatNotBooked(date);
		assertEquals(10,roomThatNotBooked.size());
	}
}