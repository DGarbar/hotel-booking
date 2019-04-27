package com.dgarbar.hotelBooking.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@EqualsAndHashCode(of = {"login"})
@Setter
@Getter
@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NaturalId
	private String login;

	@JsonIgnore
	@OneToMany(mappedBy = "user", orphanRemoval = true)
	@Setter(AccessLevel.PRIVATE)
	private Set<Booking> bookings = new HashSet<>();

	public void addBooking(Booking booking) {
		bookings.add(booking);
		booking.setUser(this);
	}

	//TODO test
	public void removeBooking(Booking booking) {
		bookings.remove(booking);
		booking.setUser(null);

	}
}
