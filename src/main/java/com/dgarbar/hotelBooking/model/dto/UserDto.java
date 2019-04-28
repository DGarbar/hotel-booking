package com.dgarbar.hotelBooking.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	@Null
	private Long id;

	@NotBlank
	@Size(min = 3, message = "login must be more than 3 symb")
	private String login;
	@Builder.Default
	private List<BookingDto> bookingDtos = new ArrayList<>();
	private BigDecimal totalPrice;
}
