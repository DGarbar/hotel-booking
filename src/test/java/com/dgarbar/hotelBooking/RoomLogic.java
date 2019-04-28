package com.dgarbar.hotelBooking;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasValue;
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
public class RoomLogic {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getAllRooms() throws Exception {
		mockMvc.perform(get("/hotel"))
			.andExpect(status().isFound())
			.andExpect(jsonPath("$.[*].id").value(hasItems(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)))
			.andExpect(jsonPath("$.length()").value(10));
	}

	@Test
	public void getRoomById() throws Exception {
		mockMvc.perform(get("/hotel/1"))
			.andExpect(status().isFound())
			.andExpect(jsonPath("$.id").value(1))
			.andExpect(jsonPath("$.number").value(1))
			.andExpect(jsonPath("$.category").value("VIP"))
			.andExpect(jsonPath("$.price").value(199.0))
			.andExpect(jsonPath("$.roomService.[*].name").value(hasItems("breakfast","smiling")));
	}

	@Test
	public void getRoomByNotExistingIdNotFound() throws Exception {
		mockMvc.perform(get("/hotel/100"))
			.andExpect(status().isNotFound());
	}

	@Test
	public void getRoomsByCategory() throws Exception {
		mockMvc.perform(get("/hotel/?category=VIP"))
			.andExpect(status().isFound()).andDo(print())
			.andExpect(jsonPath("$.[*].id").value(hasItems(1,3,4,5,7,9,10)))
			.andExpect(jsonPath("$.length()").value(7));
	}

	@Test
	public void getRoomsExceptionByCategoryThatNotExist() throws Exception {
		mockMvc.perform(get("/hotel/?category=SMALL"))
			.andExpect(status().isBadRequest());
	}

	@Test
	public void getRoomsByDates() throws Exception {
		mockMvc.perform(get("/hotel/?from=2018-04-23&to=2018-04-25"))
			.andExpect(status().isFound()).andDo(print())
			.andExpect(jsonPath("$.[*].id").value(hasItems(2,5,8,9)))
			.andExpect(jsonPath("$.length()").value(4));
	}

	@Test
	public void getExceptionWhenNotValidDatesByDates() throws Exception {
		mockMvc.perform(get("/hotel/?from=2018-04-03&to=2017-04-25"))
			.andExpect(status().isBadRequest()).andDo(print());
	}

	@Test
	public void getByOneDate() throws Exception {
		mockMvc.perform(get("/hotel/?from=2018-04-25&to=2018-04-25"))
			.andExpect(status().isFound()).andDo(print())
			.andExpect(jsonPath("$.[*].id").value(hasItems(2,5,8,9)))
			.andExpect(jsonPath("$.length()").value(4));
	}

}