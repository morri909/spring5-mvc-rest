package com.spydrone.spring5mvcrest.bootstrap;

import com.spydrone.spring5mvcrest.domain.Category;
import com.spydrone.spring5mvcrest.domain.Customer;
import com.spydrone.spring5mvcrest.repositories.CategoryRepository;
import com.spydrone.spring5mvcrest.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

	private final CategoryRepository categoryRepository;
	private final CustomerRepository customerRepository;

	public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
		this.categoryRepository = categoryRepository;
		this.customerRepository = customerRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		loadCategories();
		loadCustomers();
	}

	private void loadCustomers() {
		Customer customer1 = new Customer();
		customer1.setFirstName("Jordan");
		customer1.setLastName("Spieth");
		customerRepository.save(customer1);
		Customer customer2 = new Customer();
		customer2.setFirstName("Rory");
		customer2.setLastName("McIlroy");
		customerRepository.save(customer2);
		Customer customer3 = new Customer();
		customer3.setFirstName("Justin");
		customer3.setLastName("Thomas");
		customerRepository.save(customer3);

		System.out.println("Number of customers: " + customerRepository.findAll().size());
	}

	private void loadCategories() {
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
