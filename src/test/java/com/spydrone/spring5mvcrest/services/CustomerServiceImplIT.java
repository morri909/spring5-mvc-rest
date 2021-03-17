package com.spydrone.spring5mvcrest.services;

import com.spydrone.spring5mvcrest.bootstrap.Bootstrap;
import com.spydrone.spring5mvcrest.domain.Customer;
import com.spydrone.spring5mvcrest.mapper.CustomerMapper;
import com.spydrone.spring5mvcrest.model.CustomerDTO;
import com.spydrone.spring5mvcrest.repositories.CategoryRepository;
import com.spydrone.spring5mvcrest.repositories.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CustomerServiceImplIT {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	CategoryRepository categoryRepository;

	CustomerService sut;

	@BeforeEach
	public void setUp() throws Exception {
		Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository);
		bootstrap.run();
		sut = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
	}

	@Test
	public void patchCustomerFirstName() {
		CustomerDTO customer = new CustomerDTO();
		customer.setFirstName("test");
		customer.setId(customerRepository.findAll().get(0).getId());

		sut.patch(customer);

		Optional<Customer> resultOptional = customerRepository.findById(customer.getId());

		Assertions.assertTrue(resultOptional.isPresent());
		Customer result = resultOptional.get();
		Assertions.assertEquals(customer.getFirstName(), result.getFirstName());
		Assertions.assertNotNull(result.getLastName());
	}

	@Test
	public void patchCustomerLastName() {
		CustomerDTO customer = new CustomerDTO();
		customer.setLastName("test");
		customer.setId(customerRepository.findAll().get(0).getId());

		sut.patch(customer);

		Optional<Customer> resultOptional = customerRepository.findById(customer.getId());

		Assertions.assertTrue(resultOptional.isPresent());
		Customer result = resultOptional.get();
		Assertions.assertEquals(customer.getLastName(), result.getLastName());
		Assertions.assertNotNull(result.getFirstName());
	}
}
