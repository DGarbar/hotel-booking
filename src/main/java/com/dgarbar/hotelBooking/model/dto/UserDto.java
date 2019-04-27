package com.dgarbar.hotelBooking.model.dto;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDto {

	private Long id;

	@NotNull
	private String login;
	private List<BookingDto> bookingDtos;
	private BigDecimal totalPrice;
}
