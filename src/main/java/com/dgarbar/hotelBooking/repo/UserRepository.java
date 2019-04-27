package com.dgarbar.hotelBooking.repo;

import com.dgarbar.hotelBooking.model.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u "
		+ "LEFT JOIN FETCH u.bookings b "
		+ "LEFT JOIN FETCH b.room r "
		+ "LEFT JOIN FETCH r.roomServices s "
		+ "WHERE u.id = :id")
	Optional<User> getUserByIdEagerly(@Param("id") Long id);
}

