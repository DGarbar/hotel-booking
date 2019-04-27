package com.dgarbar.hotelBooking.repo;

import com.dgarbar.hotelBooking.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
