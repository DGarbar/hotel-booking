package com.dgarbar.hotelBooking;

import static org.hamcrest.Matchers.hasItems;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookingLogic {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getAllBooking() throws Exception {
		mockMvc.perform(get("/hotel/bookings"))
			.andExpect(status().isOk()).andDo(print())
			.andExpect(jsonPath("$.[*].id").value(hasItems(1, 2, 3, 4, 5, 6, 7, 8, 9)))
			.andExpect(jsonPath("$.length()").value(10));
	}

	@Test
	public void addBookingWithNotValidDateBadRequest() throws Exception {
		mockMvc.perform(
			post("/hotel/bookings")
				.contentType(MediaType.APPLICATION_JSON)
				.content(
					"{\"userId\":\"1\",\"roomId\":\"3\",\"fromDate\":\"2018-01-01\",\"toDate\":\"2017-01-01\"}"))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}

	@Test
	public void addBookingWithoutIdNotValidBadRequest() throws Exception {
		mockMvc.perform(
			post("/hotel/bookings")
				.contentType(MediaType.APPLICATION_JSON)
				.content(
					"{\"userId\":\"\",\"roomId\":\"3\",\"fromDate\":\"2018-01-01\",\"toDate\":\"2018-01-02\"}"))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}

	@Test
	public void addBookingWhenDateIsOverlapConflict() throws Exception {
		mockMvc.perform(
			post("/hotel/bookings")
				.contentType(MediaType.APPLICATION_JSON)
				.content(
					"{\"userId\":\"1\",\"roomId\":\"4\",\"fromDate\":\"2018-04-23\",\"toDate\":\"2018-04-24\"}"))
			.andDo(print())
			.andExpect(status().isConflict());
	}

	@Test
	public void addBookingWhenDateIsOverlapOnOneDayConflict() throws Exception {
		mockMvc.perform(
			post("/hotel/bookings")
				.contentType(MediaType.APPLICATION_JSON)
				.content(
					"{\"userId\":\"1\",\"roomId\":\"4\",\"fromDate\":\"2018-04-23\",\"toDate\":\"2018-04-23\"}"))
			.andDo(print())
			.andExpect(status().isConflict());
	}

	@Test
	public void addBookingWitNormalDate() throws Exception {
		mockMvc.perform(
			post("/hotel/bookings")
				.contentType(MediaType.APPLICATION_JSON)
				.content(
					"{\"userId\":\"1\",\"roomId\":\"5\",\"fromDate\":\"2018-04-20\",\"toDate\":\"2018-04-21\"}"))
			.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.room").isNotEmpty());
	}


	@Test
	public void addBookingWithNotUniqueLoginBadRequest() throws Exception {
		mockMvc.perform(
			post("/hotel/bookings")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"login\":\"Zeus\"}")).andDo(print())
			.andExpect(status().isBadRequest());
	}

	@Test
	public void addBookingWithNotUniqueIdBadRequest() throws Exception {
		mockMvc.perform(
			post("/hotel/bookings")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":\"1\",\"login\":\"Loura\"}")).andDo(print())
			.andExpect(status().isBadRequest());
	}

	@Test
	public void addBookingWithUniqueIdBadRequest() throws Exception {
		mockMvc.perform(
			post("/hotel/bookings")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":\"100\",\"login\":\"Sergi\"}")).andDo(print())
			.andExpect(status().isBadRequest());
	}

	@Test
	public void addBookingAndHaveInGetAllBookingsList() throws Exception {
		mockMvc.perform(
			post("/hotel/bookings")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"userId\":\"1\",\"roomId\":\"2\",\"fromDate\":\"2018-04-20\",\"toDate\":\"2018-04-21\"}"))
			.andDo(print())
			.andExpect(status().isCreated());
	}


}