package com.dgarbar.hotelBooking.model.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

@Data
@Builder
public class RoomServiceDto {

	private String name;
	private BigDecimal price;
}
