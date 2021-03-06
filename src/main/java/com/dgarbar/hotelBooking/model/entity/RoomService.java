package com.dgarbar.hotelBooking.model.entity;


import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode(of = {"room","service"})
@Setter
@Getter
@Entity
public class RoomService {

	@EmbeddedId
	private RoomServiceId roomServiceId;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@MapsId("roomId")
	private Room room;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@MapsId("serviceId")
	private Service service;
}
