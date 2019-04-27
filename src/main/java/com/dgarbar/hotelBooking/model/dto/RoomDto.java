package com.dgarbar.hotelBooking.model.dto;

import com.dgarbar.hotelBooking.model.entity.RoomCategory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {

	private Long id;

	private Long number;
	private RoomCategory category;
	private BigDecimal price;
	@Builder.Default
	private List<RoomServiceDto> roomService = new ArrayList<>();
}
