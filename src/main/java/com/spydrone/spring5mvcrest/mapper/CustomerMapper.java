package com.spydrone.spring5mvcrest.mapper;

import com.spydrone.spring5mvcrest.domain.Customer;
import com.spydrone.spring5mvcrest.model.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
	CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

	CustomerDTO customerToCustomerDTO(Customer customer);
	Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
