package com.sanitas.calculator.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanitas.calculator.request.CalulatorRequest;
import com.sanitas.calculator.response.CalculatorResponse;
import com.sanitas.calculator.service.impl.CalculatorServiceImpl;


@RestController
@RequestMapping("/sanitas")
public class CalculatorController {
	
	@Autowired
	private CalculatorServiceImpl calculator;
	
	@PostMapping(path="/calculate", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CalculatorResponse>  calculate(@Valid @RequestBody CalulatorRequest request) {
		CalculatorResponse response = calculator.calculate(request);
		return ResponseEntity.ok(response);
	}
	
}
