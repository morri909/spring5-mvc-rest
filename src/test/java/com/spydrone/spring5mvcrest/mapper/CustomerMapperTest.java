package com.spydrone.spring5mvcrest.mapper;

import com.spydrone.spring5mvcrest.domain.Customer;
import com.spydrone.spring5mvcrest.model.CustomerDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerMapperTest {

	CustomerMapper sut = CustomerMapper.INSTANCE;

	@Test
	public void customerToCustomerDTO() {
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setFirstName("test");
		customer.setLastName("this is a test");

		CustomerDTO result = sut.customerToCustomerDTO(customer);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(customer.getId(), result.getId());
		Assertions.assertEquals(customer.getFirstName(), result.getFirstName());
		Assertions.assertEquals(customer.getLastName(), result.getLastName());
	}

	@Test
	public void customerDTOToCustomer() {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(1L);
		customerDTO.setFirstName("test");
		customerDTO.setLastName("another test");

		Customer result = sut.customerDTOToCustomer(customerDTO);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(customerDTO.getId(), result.getId());
		Assertions.assertEquals(customerDTO.getFirstName(), result.getFirstName());
		Assertions.assertEquals(customerDTO.getLastName(), result.getLastName());
	}
}
