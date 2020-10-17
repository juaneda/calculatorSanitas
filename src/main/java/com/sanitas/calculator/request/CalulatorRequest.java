package com.sanitas.calculator.request;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.Getter;
import lombok.Setter;

@EntityScan
public class CalulatorRequest {
	
	@Getter
	@Setter
	private Integer param1;
	
	@Getter
	@Setter
	private Integer param2;
	
	@Getter
	@Setter
	private String  operation;

}
