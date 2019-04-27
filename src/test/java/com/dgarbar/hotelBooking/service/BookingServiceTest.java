package com.dgarbar.hotelBooking.service;

import com.dgarbar.hotelBooking.model.dto.RoomDto;
import com.dgarbar.hotelBooking.model.entity.RoomCategory;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingServiceTest {

	@Autowired
	private BookingService bookingService;

	@Test
	public void getRooms() {
		List<RoomDto> vip = bookingService.getRooms(RoomCategory.VIP);
		System.out.println(vip);
	}
}