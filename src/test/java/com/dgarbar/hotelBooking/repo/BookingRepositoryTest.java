package com.dgarbar.hotelBooking.repo;

import static org.junit.Assert.assertEquals;

import com.dgarbar.hotelBooking.model.entity.Booking;
import com.dgarbar.hotelBooking.model.entity.Room;
import java.time.LocalDate;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingRepositoryTest {

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	RoomRepository roomRepository;

	@Test
	public void getOverlapDates() {
		Room room = roomRepository.getOne(2L);
		List<Booking> overlaps = bookingRepository
			.getBookingsByFinishDateGreaterThanEqualAndStartDateLessThanEqualAndRoom(
				LocalDate.of(2018, 4, 24),
				LocalDate.of(2018, 4, 27), room);

		assertEquals(6, overlaps.size());
	}
}