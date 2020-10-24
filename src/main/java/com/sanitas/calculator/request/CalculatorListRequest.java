package com.sanitas.calculator.request;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.Getter;
import lombok.Setter;

@EntityScan
public class CalculatorListRequest {
	
	@Getter
	@Setter
	@NotNull
	private List<BigDecimal> params;
	
	
	@Getter
	@Setter
	@NotNull
	private String  operation;

	@Override
	public String toString() {
		return  "CalulatorRequest:: params = "  +  params.toString() +  " operation =" + operation;
	}
	
}
