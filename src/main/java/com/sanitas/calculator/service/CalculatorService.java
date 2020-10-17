package com.sanitas.calculator.service;

import org.springframework.stereotype.Service;

import com.sanitas.calculator.request.CalulatorRequest;
import com.sanitas.calculator.response.CalculatorResponse;

@Service
public interface CalculatorService {
	
	CalculatorResponse calculate (CalulatorRequest request);

}
