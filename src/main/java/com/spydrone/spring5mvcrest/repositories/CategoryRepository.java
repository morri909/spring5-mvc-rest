package com.spydrone.spring5mvcrest.repositories;

import com.spydrone.spring5mvcrest.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	Category findByName(String name);
}
