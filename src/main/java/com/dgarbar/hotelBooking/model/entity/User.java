package com.dgarbar.hotelBooking.model.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
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
	private List<Booking> booking = new ArrayList<>();

	@JsonIgnore
	public List<Booking> getBooking() {
		return booking;
	}
}
