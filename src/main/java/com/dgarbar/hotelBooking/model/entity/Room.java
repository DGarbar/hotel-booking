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
	public Long id;

	@NaturalId
	public Long number;

	@Enumerated(EnumType.STRING)
	@Column(length = 15, columnDefinition = "varchar(15) default 'ORDINARY'")
	public RoomCategory category;

	public BigDecimal price;

	@OneToMany(mappedBy = "room", orphanRemoval = true)
	@Setter(AccessLevel.PRIVATE)
	public List<Booking> booking = new ArrayList<>();


	@OneToMany(mappedBy = "room", orphanRemoval = true)
	@Setter(AccessLevel.PRIVATE)
	public List<RoomService> roomServices = new ArrayList<>();

}
