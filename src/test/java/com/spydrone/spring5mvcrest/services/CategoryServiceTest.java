package com.spydrone.spring5mvcrest.services;

import com.spydrone.spring5mvcrest.domain.Category;
import com.spydrone.spring5mvcrest.mapper.CategoryMapper;
import com.spydrone.spring5mvcrest.model.CategoryDTO;
import com.spydrone.spring5mvcrest.repositories.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.Table;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

	@Mock
	CategoryRepository categoryRepository;

	CategoryService sut;

	@BeforeEach
	void setUp() {
		sut = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
	}

	@Test
	public void getAll() {
		Mockito.when(categoryRepository.findAll()).thenReturn(Arrays.asList(new Category(), new Category()));

		List<CategoryDTO> result = sut.getAll();

		Assertions.assertNotNull(result);
		Assertions.assertEquals(2, result.size());
	}

	@Test
	public void getByName() {
		Category category = new Category();
		category.setId(1L);
		category.setName("test");
		Mockito.when(categoryRepository.findByName(Mockito.anyString())).thenReturn(category);

		CategoryDTO result = sut.getByName(category.getName());

		Assertions.assertNotNull(result);
		Assertions.assertEquals(category.getId(), result.getId());
		Assertions.assertEquals(category.getName(), result.getName());
	}
}