package com.dgarbar.hotelBooking.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.dgarbar.hotelBooking.model.entity.Booking;
import com.dgarbar.hotelBooking.model.entity.Room;
import java.sql.Date;
import java.util.List;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.BootstrapContext;
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
		Date date = Date.valueOf("2018-04-23");
		List<Room> roomThatNotBooked = roomRepository.getRoomThatNotBooked(date);
		assertEquals(6,roomThatNotBooked.size());
	}

	@Test
	public void getAllRoomsWhenInDateAllRoomsFree() {
		Date date = Date.valueOf("2017-04-23");
		List<Room> roomThatNotBooked = roomRepository.getRoomThatNotBooked(date);
		assertEquals(10,roomThatNotBooked.size());
	}
}