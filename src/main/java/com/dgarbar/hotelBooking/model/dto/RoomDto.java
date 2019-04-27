package com.dgarbar.hotelBooking.model.dto;

import com.dgarbar.hotelBooking.model.entity.RoomCategory;
import java.math.BigDecimal;
import lombok.Builder;

@Builder
public class RoomDto {

	private Long number;
	private RoomCategory category;
	private BigDecimal price;
}
