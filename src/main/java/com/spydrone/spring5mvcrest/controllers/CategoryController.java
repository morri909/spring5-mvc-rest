package com.spydrone.spring5mvcrest.controllers;

import com.spydrone.spring5mvcrest.model.CategoryDTO;
import com.spydrone.spring5mvcrest.model.CategoryListDTO;
import com.spydrone.spring5mvcrest.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CategoryListDTO getAll() {
		return new CategoryListDTO(categoryService.getAll());
	}

	@GetMapping("{name}")
	@ResponseStatus(HttpStatus.OK)
	public CategoryDTO getByName(@PathVariable String name) {
		return categoryService.getByName(name);
	}
}
