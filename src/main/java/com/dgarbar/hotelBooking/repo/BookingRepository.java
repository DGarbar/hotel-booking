package com.dgarbar.hotelBooking.repo;

import com.dgarbar.hotelBooking.model.entity.Booking;
import com.dgarbar.hotelBooking.model.entity.Room;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BookingRepository extends JpaRepository<Booking, Long> {

	@Query("SELECT b FROM Booking b "
		+ "LEFT JOIN FETCH b.room r "
		+ "LEFT JOIN FETCH r.roomServices s "
		+ "LEFT JOIN FETCH s.service ")
	List<Booking> getAllBookingEagerly();

	//LOLOLOL I can change to jpql, but why ???
	List<Booking> getBookingsByFinishDateGreaterThanEqualAndStartDateLessThanEqualAndRoom(
		LocalDate start, LocalDate finish, Room room);

	@Query("select b from Booking b where b.finishDate >= :start And b.startDate <= :finish AND b.room = :room")
	List<Booking> getBookingsThatOverlapWithDatesByRoom(
		@Param("start") LocalDate start,
		@Param("finish") LocalDate finish,
		@Param("room") Room room);
}

