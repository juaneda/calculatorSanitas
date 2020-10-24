package com.sanitas.calculator.service;

import com.sanitas.calculator.exception.OperationNotValid;
import com.sanitas.calculator.request.CalculatorListRequest;
import com.sanitas.calculator.request.CalculatorRequest;
import com.sanitas.calculator.response.CalculatorResponse;


public interface CalculatorService {
	
	/**
	 * Realiza la operacion indicada en la request para dos parametros
	 * @param request
	 * @return
	 * @throws OperationNotValid
	 */
	CalculatorResponse calculate (CalculatorRequest request) throws OperationNotValid ;
	
	/**
	 * Realiza la operacion indicada en la request para un listado de parametros
	 * @param request
	 * @return
	 * @throws OperationNotValid
	 */
	CalculatorResponse calculate (CalculatorListRequest request) throws OperationNotValid ;

}
