package com.spydrone.spring5mvcrest.controllers;

import com.spydrone.spring5mvcrest.model.CategoryDTO;
import com.spydrone.spring5mvcrest.services.CategoryService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

	@Mock
	CategoryService categoryService;

	@InjectMocks
	CategoryController categoryController;

	MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
	}

	@Test
	public void listCategories() throws Exception {
		CategoryDTO categoryDTO1 = new CategoryDTO();
		categoryDTO1.setId(1L);
		categoryDTO1.setName("test1");
		CategoryDTO categoryDTO2 = new CategoryDTO();
		categoryDTO2.setId(2L);
		categoryDTO2.setName("test2");
		Mockito.when(categoryService.getAll()).thenReturn(Arrays.asList(categoryDTO1, categoryDTO2));

		mockMvc.perform(get("/api/v1/categories")
					.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.categories", Matchers.hasSize(2)));
	}

	@Test
	public void getCategory() throws Exception {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(1L);
		categoryDTO.setName("test1");
		Mockito.when(categoryService.getByName(Mockito.anyString())).thenReturn(categoryDTO);

		mockMvc.perform(get("/api/v1/categories/" + categoryDTO.getName())
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", Matchers.equalTo(categoryDTO.getName())));
	}
}