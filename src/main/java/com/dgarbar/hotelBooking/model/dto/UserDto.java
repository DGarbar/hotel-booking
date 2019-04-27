package com.dgarbar.hotelBooking.model.dto;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {

	@NotNull
	private String login;
	private List<BookingDto> bookingDtos;
	private BigDecimal totalPrice;
}
