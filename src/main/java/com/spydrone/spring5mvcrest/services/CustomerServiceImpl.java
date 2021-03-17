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
	public static final String API_V_1_CUSTOMERS = "/api/v1/customers/";

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
				.map(customerDTO -> {
					customerDTO.setCustomerUrl(API_V_1_CUSTOMERS + customerDTO.getId());
					return customerDTO;
				})
				.collect(Collectors.toList());
	}

	@Override
	public Optional<CustomerDTO> getById(Long id) {
		return customerRepository.findById(id)
				.map(customerMapper::customerToCustomerDTO)
				.map(customerDTO -> {
					customerDTO.setCustomerUrl(API_V_1_CUSTOMERS + customerDTO.getId());
					return customerDTO;
				});
	}

	@Override
	public CustomerDTO save(CustomerDTO customerDTO) {
		Customer savedCustomer = customerRepository.save(customerMapper.customerDTOToCustomer(customerDTO));
		CustomerDTO savedCustomerDTO = customerMapper.customerToCustomerDTO(savedCustomer);
		savedCustomerDTO.setCustomerUrl(API_V_1_CUSTOMERS + savedCustomerDTO.getId());
		return savedCustomerDTO;
	}

	@Override
	public CustomerDTO patch(CustomerDTO customerDTO) {
		return customerRepository.findById(customerDTO.getId())
				.map(customer -> {
					if (customerDTO.getFirstName() != null) {
						customer.setFirstName(customerDTO.getFirstName());
					}
					if (customerDTO.getLastName() != null) {
						customer.setLastName(customerDTO.getLastName());
					}
					return customerMapper.customerToCustomerDTO(customerRepository.save(customer));
				})
				.orElse(new CustomerDTO());
	}


}
