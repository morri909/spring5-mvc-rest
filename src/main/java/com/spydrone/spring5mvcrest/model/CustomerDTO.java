package com.spydrone.spring5mvcrest.model;

import lombok.Data;

@Data
public class CustomerDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private String customerUrl;
}
