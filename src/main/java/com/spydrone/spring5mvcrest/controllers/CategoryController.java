package com.spydrone.spring5mvcrest.controllers;

import com.spydrone.spring5mvcrest.model.CategoryDTO;
import com.spydrone.spring5mvcrest.model.CategoryListDTO;
import com.spydrone.spring5mvcrest.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/categories")
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	public ResponseEntity<CategoryListDTO> getAll() {
		return new ResponseEntity<CategoryListDTO>(new CategoryListDTO(categoryService.getAll()), HttpStatus.OK);
	}

	@GetMapping("{name}")
	public ResponseEntity<CategoryDTO> getByName(@PathVariable String name) {
		return new ResponseEntity<CategoryDTO>(categoryService.getByName(name), HttpStatus.OK);
	}
}
