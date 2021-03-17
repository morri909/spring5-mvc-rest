package com.spydrone.spring5mvcrest.controllers;

import com.spydrone.spring5mvcrest.domain.Customer;
import com.spydrone.spring5mvcrest.model.CustomerDTO;
import com.spydrone.spring5mvcrest.services.CustomerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest extends AbstractRestControllerTest {

	@Mock
	CustomerService customerService;

	@InjectMocks
	CustomerController customerController;

	MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	@Test
	public void getAll() throws Exception {
		CustomerDTO customerDTO1 = new CustomerDTO();
		customerDTO1.setFirstName("Rory");
		customerDTO1.setLastName("McIlroy");
		CustomerDTO customerDTO2 = new CustomerDTO();
		customerDTO2.setFirstName("Jordan");
		customerDTO2.setLastName("Spieth");
		Mockito.when(customerService.getAll()).thenReturn(Arrays.asList(customerDTO1, customerDTO2));

		mockMvc.perform(get("/api/v1/customers")
					.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.customers", Matchers.hasSize(2)));
	}

	@Test
	public void getById() throws Exception {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName("Justin");
		customerDTO.setLastName("Thomas");
		Mockito.when(customerService.getById(Mockito.anyLong())).thenReturn(Optional.of(customerDTO));

		mockMvc.perform(get("/api/v1/customers/1")
					.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("firstName", Matchers.equalTo(customerDTO.getFirstName())))
				.andExpect(jsonPath("lastName", Matchers.equalTo(customerDTO.getLastName())));
	}

	@Test
	public void create() throws Exception {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName("Justin");
		customerDTO.setLastName("Thomas");
		Mockito.when(customerService.save(Mockito.any())).thenReturn(customerDTO);

		mockMvc.perform(post("/api/v1/customers/")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(asJsonString(customerDTO)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.firstName", Matchers.equalTo(customerDTO.getFirstName())))
				.andExpect(jsonPath("$.lastName", Matchers.equalTo(customerDTO.getLastName())));
	}

	@Test
	public void update() throws Exception {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(1L);
		customerDTO.setFirstName("Justin");
		customerDTO.setLastName("Thomas");
		Mockito.when(customerService.save(Mockito.any())).thenReturn(customerDTO);

		mockMvc.perform(put("/api/v1/customers/" + customerDTO.getId())
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(asJsonString(customerDTO)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", Matchers.equalTo(customerDTO.getId().intValue())))
				.andExpect(jsonPath("$.firstName", Matchers.equalTo(customerDTO.getFirstName())))
				.andExpect(jsonPath("$.lastName", Matchers.equalTo(customerDTO.getLastName())));
	}
}