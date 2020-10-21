package com.sanitas.calculator.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.sanitas.calculator.exception.OperationNotValid;
import com.sanitas.calculator.request.CalulatorListRequest;
import com.sanitas.calculator.request.CalulatorRequest;
import com.sanitas.calculator.response.CalculatorResponse;
import com.sanitas.calculator.service.CalculatorService;

@Service
public class CalculatorServiceImpl implements CalculatorService {
	

	@Override
	public CalculatorResponse calculate(final CalulatorRequest request) throws OperationNotValid {
		BigDecimal result = null;
		//Comprueba  la operacion a realizar
		if (request.getOperation().equals("+")) {
			
			result = request.getParam1().add(request.getParam2());
			
		} else if (request.getOperation().equals("-")) {
			
			result = request.getParam1().subtract(request.getParam2());
			
		} else {
			throw new OperationNotValid(request.getOperation()) ;
		}
		CalculatorResponse response = new CalculatorResponse();
		response.setResult(result);
		return response;
	}

	@Override
	public CalculatorResponse calculate(CalulatorListRequest request) throws OperationNotValid {
		BigDecimal result = null;
		//Comprueba  la operacion a realizar
		if (request.getOperation().equals("+")) {
			
			//Recorre el listado sumando cada uno de sus elementos
			result = request.getParams().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
			
		} else if (request.getOperation().equals("-")) {
			
			//Recorre el listado relizando la resta de primer elemento con los sucesivos
			result = request.getParams().stream().reduce( (x,y) -> x.subtract(y)).get();
			
		} else {
			throw new OperationNotValid(request.getOperation()) ;
		}
		CalculatorResponse response = new CalculatorResponse();
		response.setResult(result);
		return response;
	}
	

}
