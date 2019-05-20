package com.dgarbar.hotelBooking.service;

import com.dgarbar.hotelBooking.model.dto.BookingDto;
import com.dgarbar.hotelBooking.model.dto.BookingOrder;
import com.dgarbar.hotelBooking.service.exception.DateIsOverlapException;
import com.dgarbar.hotelBooking.service.exception.EntityNotFoundException;
import java.util.List;
import javax.persistence.*;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

public interface BookingService {

	@Transactional(readOnly = true)
	List<BookingDto> getAllBooking();

	@Transactional(isolation = Isolation.REPEATABLE_READ)
	BookingDto saveBooking(BookingOrder bookingOrder)
		throws DateIsOverlapException, EntityNotFoundException, PersistenceException;
}
