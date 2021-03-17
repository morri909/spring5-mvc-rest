package com.spydrone.spring5mvcrest.controllers;

import com.spydrone.spring5mvcrest.model.CustomerDTO;
import com.spydrone.spring5mvcrest.model.CustomerListDTO;
import com.spydrone.spring5mvcrest.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
		return new ResponseEntity<>(new CustomerListDTO(customerService.getAll()), HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<CustomerDTO> getById(@PathVariable Long id) {
		return new ResponseEntity<>(customerService.getById(id).orElseThrow(EntityNotFoundException::new), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDTO) {
		return new ResponseEntity<>(customerService.save(customerDTO), HttpStatus.CREATED);
	}

	@PutMapping("{id}")
	public ResponseEntity<CustomerDTO> update(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
		customerDTO.setId(id);
		return new ResponseEntity<>(customerService.save(customerDTO), HttpStatus.OK);
	}

	@PatchMapping("{id}")
	public ResponseEntity<CustomerDTO> patch(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
		customerDTO.setId(id);
		return new ResponseEntity<>(customerService.patch(customerDTO), HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> patch(@PathVariable Long id) {
		customerService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
