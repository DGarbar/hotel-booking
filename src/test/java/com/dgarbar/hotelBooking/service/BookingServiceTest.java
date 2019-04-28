package com.dgarbar.hotelBooking.service;

import com.dgarbar.hotelBooking.model.dto.BookingOrder;
import com.dgarbar.hotelBooking.model.dto.RoomDto;
import com.dgarbar.hotelBooking.model.entity.RoomCategory;
import com.dgarbar.hotelBooking.service.exception.DateIsOverlapException;
import com.dgarbar.hotelBooking.service.exception.EntityNotFoundException;
import java.time.LocalDate;
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
	public void getRooms() throws EntityNotFoundException, DateIsOverlapException {

		BookingOrder bookingOrder = new BookingOrder();
		bookingOrder.setRoomId(2L);
		bookingOrder.setUserId(1L);
		bookingOrder.setFromDate(LocalDate.of(2018,4,23));
		bookingOrder.setToDate(LocalDate.of(2018,4,24));
		bookingService.saveBooking(bookingOrder);
	}
}