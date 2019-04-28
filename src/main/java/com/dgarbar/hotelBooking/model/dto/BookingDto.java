package com.dgarbar.hotelBooking.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class BookingDto {

	private Long id;

	@NotNull
	private RoomDto room;
	@NotNull
	private LocalDate startDate;
	@NotNull
	private LocalDate finishDate;

	private BigDecimal price;
}
