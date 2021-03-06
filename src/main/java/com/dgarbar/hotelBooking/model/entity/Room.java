package com.dgarbar.hotelBooking.model.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

@NoArgsConstructor
@EqualsAndHashCode(of = "number")
@Setter
@Getter
@Entity
public class Room {

	@Id
	//Prefer SEQUENCE but we dont save to much information
	//If you need SEQUENCE, set sequence name explicitly
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NaturalId
	private Long number;

	@Enumerated(EnumType.STRING)
	@Column(length = 15, columnDefinition = "varchar(15) default 'ORDINARY'")
	private RoomCategory category = RoomCategory.ORDINARY;

	private BigDecimal price;

	@OneToMany(mappedBy = "room", orphanRemoval = true)
	@Setter(AccessLevel.PRIVATE)
	private Set<Booking> bookings = new HashSet<>();


	@OneToMany(mappedBy = "room", orphanRemoval = true)
	@Setter(AccessLevel.PRIVATE)
	private Set<RoomService> roomServices = new HashSet<>();

	public void addBooking(Booking booking) {
		bookings.add(booking);
		booking.setRoom(this);
	}

	//TODO test
	public void removeBooking(Booking booking) {
		bookings.remove(booking);
		booking.setRoom(null);
	}
}
