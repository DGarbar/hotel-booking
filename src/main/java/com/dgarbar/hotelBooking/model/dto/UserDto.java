package com.dgarbar.hotelBooking.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private Long id;

	private String login;
	@Builder.Default
	private List<BookingDto> bookingDtos = new ArrayList<>();
	private BigDecimal totalPrice;
}
