package com.sanitas.calculator.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.sanitas.calculator.exception.OperationNotValid;
import com.sanitas.calculator.request.CalculatorListRequest;
import com.sanitas.calculator.request.CalculatorRequest;
import com.sanitas.calculator.response.CalculatorResponse;
import com.sanitas.calculator.service.CalculatorService;

@Service
public class CalculatorServiceImpl implements CalculatorService {
	
	private final static String OPERATOR_PLUS = "+";
	private final static String OPERATOR_SUBTRACT = "-";

	@Override
	public CalculatorResponse calculate(final CalculatorRequest request) throws OperationNotValid {
		BigDecimal result = null;
		//Comprueba  la operacion a realizar
		if ( OPERATOR_PLUS.equals(request.getOperation())) {
			
			result = request.getParam1().add(request.getParam2());
			
		} else if ( OPERATOR_SUBTRACT.equals(request.getOperation())) {
			
			result = request.getParam1().subtract(request.getParam2());
			
		} else {
			
			throw new OperationNotValid(request.getOperation()) ;
		
		}
		
		CalculatorResponse response = new CalculatorResponse();
		response.setResult(result);
		return response;
	}

	@Override
	public CalculatorResponse calculate(final CalculatorListRequest request) throws OperationNotValid {
		BigDecimal result = null;
		//Comprueba  la operacion a realizar
		if ( OPERATOR_PLUS.equals(request.getOperation())) {
			
			//Recorre el listado sumando cada uno de sus elementos
			result = request.getParams().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
			
		} else if ( OPERATOR_SUBTRACT.equals(request.getOperation())) {
			
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
