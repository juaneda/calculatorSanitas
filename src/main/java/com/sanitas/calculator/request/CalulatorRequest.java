package com.sanitas.calculator.request;

import javax.validation.constraints.NotNull;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.Getter;
import lombok.Setter;

@EntityScan
public class CalulatorRequest {
	
	@Getter
	@Setter
	@NotNull
	private Integer param1;
	
	@Getter
	@Setter
	@NotNull
	private Integer param2;
	
	@Getter
	@Setter
	@NotNull
	private String  operation;

}
