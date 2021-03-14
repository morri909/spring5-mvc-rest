package com.spydrone.spring5mvcrest.controllers;

import com.spydrone.spring5mvcrest.model.CustomerDTO;
import com.spydrone.spring5mvcrest.model.CustomerListDTO;
import com.spydrone.spring5mvcrest.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityNotFoundException;

@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping
	public ResponseEntity<CustomerListDTO> getAll() {
		return new ResponseEntity<CustomerListDTO>(new CustomerListDTO(customerService.getAll()), HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<CustomerDTO> getById(@PathVariable  Long id) {
		return new ResponseEntity<CustomerDTO>(customerService.getById(id).orElseThrow(EntityNotFoundException::new), HttpStatus.OK);
	}
}
