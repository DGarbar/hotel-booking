package com.dgarbar.hotelBooking.model.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

@NoArgsConstructor
@EqualsAndHashCode(of = "name")
@Setter
@Getter
@Entity
public class Service {

	@Id
	//Prefer SEQUENCE but we dont save to much information
	//If you need SEQUENCE, set sequence name explicitly
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NaturalId
	private String name;

	private BigDecimal price;

	@OneToMany(mappedBy = "room", orphanRemoval = true)
	@Setter(AccessLevel.PRIVATE)
	private Set<RoomService> roomServices = new HashSet<>();

}
