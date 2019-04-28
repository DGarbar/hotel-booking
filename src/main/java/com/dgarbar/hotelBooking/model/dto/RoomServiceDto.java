package com.dgarbar.hotelBooking.model.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomServiceDto {

	private String name;
	private BigDecimal price;
}
