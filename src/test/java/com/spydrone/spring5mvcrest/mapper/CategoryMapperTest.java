package com.spydrone.spring5mvcrest.mapper;

import com.spydrone.spring5mvcrest.domain.Category;
import com.spydrone.spring5mvcrest.model.CategoryDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CategoryMapperTest {

	CategoryMapper sut = CategoryMapper.INSTANCE;

	@Test
	public void categoryToCategoryDTO() {
		Category test = new Category();
		test.setId(1L);
		test.setName("test");

		CategoryDTO result = sut.categoryToCategoryDTO(test);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(test.getId(), result.getId());
		Assertions.assertEquals(test.getName(), result.getName());
	}
}