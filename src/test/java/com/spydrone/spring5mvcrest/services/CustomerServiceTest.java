package com.spydrone.spring5mvcrest.services;

import com.spydrone.spring5mvcrest.domain.Customer;
import com.spydrone.spring5mvcrest.mapper.CustomerMapper;
import com.spydrone.spring5mvcrest.model.CustomerDTO;
import com.spydrone.spring5mvcrest.repositories.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

	@Mock
	CustomerRepository customerRepository;

	CustomerService sut;

	@BeforeEach
	void setUp() {
		sut = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
	}

	@Test
	public void getAll() {
		Mockito.when(customerRepository.findAll()).thenReturn(Arrays.asList(new Customer(), new Customer()));

		List<CustomerDTO> result = sut.getAll();

		Assertions.assertNotNull(result);
		Assertions.assertEquals(2, result.size());
	}

	@Test
	public void getById() {
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setFirstName("test");
		customer.setLastName("this is another test");
		Mockito.when(customerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(customer));

		Optional<CustomerDTO> resultOptional = sut.getById(customer.getId());

		Assertions.assertTrue(resultOptional.isPresent());
		CustomerDTO result = resultOptional.get();
		Assertions.assertEquals(customer.getFirstName(), result.getFirstName());
		Assertions.assertEquals(customer.getLastName(), result.getLastName());
		Assertions.assertEquals("/api/v1/customers/" + customer.getId().toString(), result.getCustomerUrl());
	}

	@Test
	public void save() {
		Customer customer = new Customer();
		customer.setFirstName("test");
		customer.setLastName("this is a test");

		Mockito.when(customerRepository.save(Mockito.any())).thenReturn(customer);

		CustomerDTO result = sut.save(new CustomerDTO());

		Assertions.assertNotNull(result);
		Assertions.assertEquals(customer.getFirstName(), result.getFirstName());
		Assertions.assertEquals(customer.getLastName(), result.getLastName());
		Mockito.verify(customerRepository).save(Mockito.any());
	}
}