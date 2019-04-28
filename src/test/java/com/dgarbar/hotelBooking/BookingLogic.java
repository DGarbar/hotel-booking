package com.dgarbar.hotelBooking;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.nullValue;
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
	public void getAllUsersIsWithoutInner() throws Exception {
		mockMvc.perform(get("/users"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.[*].bookingDtos").value(everyItem(empty())))
			.andExpect(jsonPath("$.[*].totalPrice").value(everyItem(nullValue())));
	}

	@Test
	public void getAllUsers() throws Exception {
		mockMvc.perform(get("/users"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.[*].id").value(hasItems(1, 2, 3, 4, 5, 6)));
	}

	@Test
	public void getById() throws Exception {
		mockMvc.perform(get("/users/1"))
			.andExpect(status().isFound())
			.andExpect(jsonPath("$.id").value(1))
			.andExpect(jsonPath("$.login").value("Cade"))
			.andExpect(jsonPath("$.bookingDtos").isEmpty())
			.andExpect(jsonPath("$.totalPrice").isEmpty());
	}

	@Test
	public void getByIdWithBookingNotEmpty() throws Exception {
		mockMvc.perform(get("/users/1/booking"))
			.andExpect(status().isFound())
			.andExpect(jsonPath("$.id").value(1))
			.andExpect(jsonPath("$.login").value("Cade"))
			.andExpect(jsonPath("$.bookingDtos").isNotEmpty())
			.andExpect(jsonPath("$.totalPrice").value(3285.20));
	}

	@Test
	public void getByIdWithBookingContainsAllUserBooking() throws Exception {
		mockMvc.perform(get("/users/5/booking"))
			.andExpect(status().isFound())
			.andExpect(jsonPath("$.id").value(5))
			.andExpect(jsonPath("$.login").value("Uriel"))
			.andExpect(
				jsonPath("$.bookingDtos.[*].room.id").value(hasItems(3, 10)))
			.andExpect(jsonPath("$.totalPrice").value(4896.00));
	}

	@Test
	public void getByIdWithBookingWithNotExistingIdNotFound() throws Exception {
		mockMvc.perform(get("/users/50/booking"))
			.andExpect(status().isNotFound());
	}

	@Test
	public void getByIdWithNotExistingIdNotFound() throws Exception {
		this.mockMvc.perform(get("/users/50"))
			.andExpect(status().isNotFound());
	}

	@Test
	public void addUserWithLogin() throws Exception {
		mockMvc.perform(
			post("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"login\":\"Johnathan\"}"))
			.andExpect(jsonPath("$.id").value(7L))
			.andExpect(jsonPath("$.login").value("Johnathan"))
			.andExpect(jsonPath("$.bookingDtos").isEmpty())
			.andExpect(jsonPath("$.totalPrice").isEmpty());
	}

	@Test
	public void addUserWithNotValidSizeLoginBadRequest() throws Exception {
		mockMvc.perform(
			post("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"login\":\"Jo\"}"))
			.andExpect(status().isBadRequest());
	}

	@Test
	public void addUserWithNotValidEmptyLoginBadRequest() throws Exception {
		mockMvc.perform(
			post("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"login\":\"\"}"))
			.andExpect(status().isBadRequest());
	}

	@Test
	public void addUserWithoutBodyBadRequest() throws Exception {
		mockMvc.perform(
			post("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
			.andExpect(status().isBadRequest());
	}

	@Test
	public void addUserWithNotUniqueLoginConflict() throws Exception {
		mockMvc.perform(
			post("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"login\":\"Zeus\"}")).andDo(print())
			.andExpect(status().isConflict());
	}

	@Test
	public void addUserWithNotUniqueIdBadRequest() throws Exception {
		mockMvc.perform(
			post("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":\"1\",\"login\":\"Loura\"}")).andDo(print())
			.andExpect(status().isBadRequest());
	}

	@Test
	public void addUserWithUniqueId() throws Exception {
		mockMvc.perform(
			post("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":\"100\",\"login\":\"Sergi\"}")).andDo(print())
			.andExpect(status().isBadRequest());
	}

	@Test
	public void addUserAndHaveInGetAllUsersList() throws Exception {
		mockMvc.perform(get("/users"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.[*].id").value(hasItems(1, 2, 3, 4, 5, 6)));

		mockMvc.perform(
			post("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"login\":\"Johnathan\"}"));

		mockMvc.perform(get("/users"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.[*].id").value(hasItems(1, 2, 3, 4, 5, 6, 7)));
	}
}