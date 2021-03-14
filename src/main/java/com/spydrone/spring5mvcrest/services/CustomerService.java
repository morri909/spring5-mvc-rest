package com.spydrone.spring5mvcrest.services;

import com.spydrone.spring5mvcrest.model.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
	List<CustomerDTO> getAll();
	Optional<CustomerDTO> getById(Long id);
}
