package com.dgarbar.hotelBooking.model.entity;

import java.math.BigDecimal;
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
@EqualsAndHashCode(of = "number")
@Setter
@Getter
@Entity
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NaturalId
	private Long number;

	@Enumerated(EnumType.STRING)
	@Column(length = 15, columnDefinition = "varchar(15) default 'ORDINARY'")
	private RoomCategory category = RoomCategory.ORDINARY;

	private BigDecimal price;

	@OneToMany(mappedBy = "room", orphanRemoval = true)
	@Setter(AccessLevel.PRIVATE)
	private List<Booking> bookings = new ArrayList<>();


	@OneToMany(mappedBy = "room", orphanRemoval = true)
	@Setter(AccessLevel.PRIVATE)
	private List<RoomService> roomServices = new ArrayList<>();

}
