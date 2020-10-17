package com.sanitas.calculator.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.sanitas.calculator.exception.OperationNotValid;
import com.sanitas.calculator.request.CalulatorRequest;
import com.sanitas.calculator.response.CalculatorResponse;
import com.sanitas.calculator.service.CalculatorService;

@Service
public class CalculatorServiceImpl implements CalculatorService {
	
	private static final String OPERATION_ADD = "ADD";
	private static final String OPERATION_SUBSTRACT = "SUBSTRACT";

	@Override
	public CalculatorResponse calculate(final CalulatorRequest request) throws OperationNotValid {
		BigDecimal result = null;
		if (request.getOperation().equals(OPERATION_ADD)) {
			
			result = request.getParam1().add(request.getParam2());
			
		} else if (request.getOperation().equals(OPERATION_SUBSTRACT)) {
			
			result = request.getParam1().subtract(request.getParam2());
			
		} else {
			throw new OperationNotValid(request.getOperation()) ;
		}
		CalculatorResponse response = new CalculatorResponse();
		response.setResult(result);
		return response;
	}
	

}
