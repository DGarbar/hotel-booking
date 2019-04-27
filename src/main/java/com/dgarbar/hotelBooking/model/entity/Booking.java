package com.dgarbar.hotelBooking.model.entity;

import java.util.Calendar;
import java.util.Date;
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
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "booking_user_fk", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "booking_room_fk", nullable = false)
	private Room room;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date finishDate;

}
