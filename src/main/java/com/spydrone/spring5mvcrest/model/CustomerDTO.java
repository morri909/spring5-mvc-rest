package com.spydrone.spring5mvcrest.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CustomerDTO {
	private Long id;
	@ApiModelProperty(value = "This is the first name", required = true)
	private String firstName;
	private String lastName;
	private String customerUrl;
}
