package com.sanitas.calculator.service;

import com.sanitas.calculator.exception.OperationNotValid;
import com.sanitas.calculator.request.CalulatorListRequest;
import com.sanitas.calculator.request.CalulatorRequest;
import com.sanitas.calculator.response.CalculatorResponse;


public interface CalculatorService {
	
	/**
	 * Realiza la operacion indicada en la request para dos parametros
	 * @param request
	 * @return
	 * @throws OperationNotValid
	 */
	CalculatorResponse calculate (CalulatorRequest request) throws OperationNotValid ;
	
	/**
	 * Realiza la operacion indicada en la request para un listado de parametros
	 * @param request
	 * @return
	 * @throws OperationNotValid
	 */
	CalculatorResponse calculate (CalulatorListRequest request) throws OperationNotValid ;

}
