package com.sanitas.calculator.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.Getter;
import lombok.Setter;

@EntityScan
public class CalulatorRequest {
	
	@Getter
	@Setter
	@NotNull
	private BigDecimal param1;
	
	@Getter
	@Setter
	@NotNull
	private BigDecimal param2;
	
	@Getter
	@Setter
	@NotNull
	private String  operation;

	@Override
	public String toString() {
		return  "CalulatorRequest:: param1 = "  +  param1 +  " param2 = " +  param2  +  " operation =" + operation;
	}
	
}
