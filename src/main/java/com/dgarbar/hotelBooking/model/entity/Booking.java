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
	public Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "booking_user_fk", nullable = false)
	public User user;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "booking_room_fk", nullable = false)
	public Room room;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	public Date startDate;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	public Date finishDate;

}
