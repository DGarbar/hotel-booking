package com.dgarbar.hotelBooking.model.entity;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class RoomServiceId implements Serializable {

	private Long roomId;
	private Long serviceId;
}
