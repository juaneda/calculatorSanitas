package com.sanitas.calculator.service;

import com.sanitas.calculator.request.CalulatorRequest;
import com.sanitas.calculator.response.CalculatorResponse;


public interface CalculatorService {
	
	CalculatorResponse calculate (CalulatorRequest request);

}
