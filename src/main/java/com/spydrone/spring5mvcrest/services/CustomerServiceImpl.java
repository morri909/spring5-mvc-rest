package com.spydrone.spring5mvcrest.services;

import com.spydrone.spring5mvcrest.domain.Customer;
import com.spydrone.spring5mvcrest.mapper.CustomerMapper;
import com.spydrone.spring5mvcrest.model.CustomerDTO;
import com.spydrone.spring5mvcrest.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerMapper customerMapper;
	private final CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
		this.customerMapper = customerMapper;
		this.customerRepository = customerRepository;
	}

	@Override
	public List<CustomerDTO> getAll() {
		return customerRepository.findAll().stream()
				.map(customerMapper::customerToCustomerDTO)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<CustomerDTO> getById(Long id) {
		return customerRepository.findById(id)
				.map(customerMapper::customerToCustomerDTO);
	}
}
