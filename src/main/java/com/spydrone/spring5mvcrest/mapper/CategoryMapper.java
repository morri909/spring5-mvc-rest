package com.spydrone.spring5mvcrest.mapper;

import com.spydrone.spring5mvcrest.domain.Category;
import com.spydrone.spring5mvcrest.model.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

	CategoryDTO categoryToCategoryDTO(Category category);
}
