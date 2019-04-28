package com.dgarbar.hotelBooking.model.dto;

import com.dgarbar.hotelBooking.model.dto.customValidator.CheckDate;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@CheckDate
@Data
public class BookingOrder {

	@NotNull
	private Long userId;
	@NotNull
	private Long roomId;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fromDate;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate toDate;
}
