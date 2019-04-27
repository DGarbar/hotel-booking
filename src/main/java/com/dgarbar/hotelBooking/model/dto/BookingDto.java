package com.dgarbar.hotelBooking.model.dto;

import com.dgarbar.hotelBooking.model.entity.Room;
import com.dgarbar.hotelBooking.model.entity.User;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
public class BookingDto {

	private RoomDto room;
	private Date startDate;
	private Date finishDate;
}
