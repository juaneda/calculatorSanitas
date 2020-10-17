package com.sanitas.calculator.service.impl;

import org.springframework.stereotype.Service;

import com.sanitas.calculator.request.CalulatorRequest;
import com.sanitas.calculator.response.CalculatorResponse;
import com.sanitas.calculator.service.CalculatorService;

@Service
public class CalculatorServiceImpl implements CalculatorService {

	@Override
	public CalculatorResponse calculate(CalulatorRequest request) {
		// TODO Auto-generated method stub
		CalculatorResponse response = new CalculatorResponse();
		response.setResult(15);
		return response;
	}
	

}
