package com.dgarbar.hotelBooking.model.dto;

import com.dgarbar.hotelBooking.model.entity.Room;
import com.dgarbar.hotelBooking.model.entity.User;
import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingDto {

	private Long id;

	private RoomDto room;
	private Date startDate;
	private Date finishDate;
	private BigDecimal price;
}
