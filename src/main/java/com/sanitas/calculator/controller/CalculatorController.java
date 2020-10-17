package com.sanitas.calculator.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanitas.calculator.request.CalulatorRequest;
import com.sanitas.calculator.response.CalculatorResponse;


@RestController
@RequestMapping("/sanitas")
public class CalculatorController {

	
	@PostMapping(path="/calculate", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?>  calculator(@RequestBody CalulatorRequest request) {
		CalculatorResponse r = null;
		int i = 0;
		return null;
	}
	
}
