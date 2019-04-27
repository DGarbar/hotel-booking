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
@EqualsAndHashCode(of = "name")
@Setter
@Getter
@Entity
public class Service {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NaturalId
	private String name;

	private BigDecimal price;


	@OneToMany(mappedBy = "room", orphanRemoval = true)
	@Setter(AccessLevel.PRIVATE)
	private List<RoomService> roomServices = new ArrayList<>();

}
