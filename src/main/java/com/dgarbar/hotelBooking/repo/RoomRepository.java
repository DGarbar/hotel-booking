package com.dgarbar.hotelBooking.repo;

import com.dgarbar.hotelBooking.model.entity.Room;
import com.dgarbar.hotelBooking.model.entity.RoomCategory;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RoomRepository extends JpaRepository<Room, Long> {

	@Query("SELECT DISTINCT r FROM Room r "
		+ "LEFT JOIN FETCH r.roomServices s "
		+ "LEFT JOIN FETCH s.service "
		+ "WHERE r.category = :category")
	Set<Room> getRoomByCategoryEagerly(@Param("category") RoomCategory category);

	@Query("SELECT r FROM Room r "
		+ "LEFT JOIN FETCH r.roomServices s "
		+ "LEFT JOIN FETCH s.service "
		+ "WHERE r.id = :id")
	Optional<Room> getRoomByIdEagerly(@Param("id") Long id);
	//Original
//Select * from room where room.id not in (SELECT DISTINCT room_id FROM booking Where '2018-04-23' BETWEEN start_date AND finish_date)

	@Query("SELECT DISTINCT r FROM Room r "
		+ "LEFT JOIN FETCH r.roomServices s "
		+ "LEFT JOIN FETCH s.service "
		+ "WHERE r.id NOT IN ("
		+ "SELECT b.room.id FROM Booking b WHERE :date BETWEEN b.startDate AND b.finishDate)")
	Set<Room> getRoomEagerlyThatNotBooked(@Param("date") LocalDate date);

	@Query("SELECT DISTINCT r FROM Room r "
		+ "LEFT JOIN FETCH r.roomServices s "
		+ "LEFT JOIN FETCH s.service "
		+ "WHERE r.id NOT IN ("
		+ "SELECT b.room.id FROM Booking b WHERE b.finishDate >= :start AND b.startDate <= :finish)")
	Set<Room> getRoomEagerlyThatNotBooked(@Param("start") LocalDate start,
		@Param("finish") LocalDate finish);

	@Query("SELECT DISTINCT r FROM Room r "
		+ "LEFT JOIN FETCH r.roomServices s "
		+ "LEFT JOIN FETCH s.service ")
	Set<Room> getRoomEagerly();
}

