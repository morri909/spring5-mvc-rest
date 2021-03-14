package com.spydrone.spring5mvcrest.bootstrap;

import com.spydrone.spring5mvcrest.domain.Category;
import com.spydrone.spring5mvcrest.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {
	private CategoryRepository categoryRepository;

	public Bootstrap(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Category grain = new Category();
		grain.setName("grain");
		categoryRepository.save(grain);
		Category diary = new Category();
		diary.setName("diary");
		categoryRepository.save(diary);
		Category meat = new Category();
		meat.setName("meat");
		categoryRepository.save(meat);
		Category vegetable = new Category();
		vegetable.setName("vegetable");
		categoryRepository.save(vegetable);
		Category fruit = new Category();
		fruit.setName("fruit");
		categoryRepository.save(fruit);
		System.out.println("Number of categories: " + categoryRepository.findAll().size());
	}
}
