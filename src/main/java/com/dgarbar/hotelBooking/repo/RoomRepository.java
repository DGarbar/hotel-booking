package com.dgarbar.hotelBooking.repo;

import com.dgarbar.hotelBooking.model.entity.Room;
import com.dgarbar.hotelBooking.model.entity.RoomCategory;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RoomRepository extends JpaRepository<Room, Long> {


	List<Room> getAllByCategory(RoomCategory category);

//Original
//Select * from room where room.id not in (SELECT DISTINCT room_id FROM booking Where '2018-04-23' BETWEEN start_date AND finish_date)
	@Query("SELECT r FROM Room r WHERE r.id NOT IN ("
		+ "SELECT b.room.id FROM Booking b WHERE :date BETWEEN b.startDate AND b.finishDate)")
	List<Room> getRoomThatNotBooked(@Param("date") LocalDate date);
}

