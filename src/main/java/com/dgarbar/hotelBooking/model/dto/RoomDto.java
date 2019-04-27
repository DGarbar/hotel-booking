package com.dgarbar.hotelBooking.model.dto;

import com.dgarbar.hotelBooking.model.entity.RoomCategory;
import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomDto {

	private Long id;

	private Long number;
	private RoomCategory category;
	private BigDecimal price;
	private List<RoomServiceDto> roomService;
}
