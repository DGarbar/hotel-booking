package com.dgarbar.hotelBooking.model.entity;

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
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Long id;

	@NaturalId
	public String login;

	@OneToMany(mappedBy = "user", orphanRemoval = true)
	@Setter(AccessLevel.PRIVATE)
	public List<Booking> booking = new ArrayList<>();

}
