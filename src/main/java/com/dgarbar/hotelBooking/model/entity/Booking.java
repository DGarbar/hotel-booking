package com.dgarbar.hotelBooking.model.entity;

import java.time.LocalDate;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
//@EqualsAndHashCode(of = {"login"})
@Setter
@Getter
@Entity
public class Booking {

	@Id
	//Prefer SEQUENCE but we dont save to much information
	//If you need SEQUENCE, set sequence name explicitly
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "room_id", nullable = false)
	private Room room;

	@Column(nullable = false)
	private LocalDate startDate;

	@Column(nullable = false)
	private LocalDate finishDate;


}
