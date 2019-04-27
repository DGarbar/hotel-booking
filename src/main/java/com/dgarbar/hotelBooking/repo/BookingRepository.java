package com.dgarbar.hotelBooking.repo;

import com.dgarbar.hotelBooking.model.entity.Booking;
import com.dgarbar.hotelBooking.model.entity.Room;
import com.dgarbar.hotelBooking.model.entity.User;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BookingRepository extends JpaRepository<Booking,Long> {


}
