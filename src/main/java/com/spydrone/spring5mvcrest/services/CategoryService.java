package com.spydrone.spring5mvcrest.services;

import com.spydrone.spring5mvcrest.model.CategoryDTO;

import java.util.List;

public interface CategoryService {
	List<CategoryDTO> getAll();
	CategoryDTO getByName(String name);
}
