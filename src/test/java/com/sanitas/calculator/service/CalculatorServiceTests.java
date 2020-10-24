package com.sanitas.calculator.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sanitas.calculator.exception.OperationNotValid;
import com.sanitas.calculator.request.CalculatorListRequest;
import com.sanitas.calculator.request.CalculatorRequest;
import com.sanitas.calculator.response.CalculatorResponse;
import com.sanitas.calculator.service.impl.CalculatorServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class CalculatorServiceTests {
	
	@Autowired
	private CalculatorServiceImpl calculatorService;
	
	/**
	 * Verifica la suma entre dos parametros
	 * @throws OperationNotValid
	 */

	@Test
	public void testCalculateServiceAdd() throws OperationNotValid  {

		CalculatorRequest request = new CalculatorRequest();
		request.setOperation("+");
		request.setParam1(new BigDecimal(11));
		request.setParam2(new BigDecimal(13));
		
		CalculatorResponse response  = calculatorService.calculate(request);
		
		assertEquals(24, response.getResult().intValue());

	}
	
	/**
	 * Verifica la resta entre dos parametros
	 * @throws OperationNotValid
	 */
	@Test
	public void testCalculateServiceSubtract() throws OperationNotValid {

		CalculatorRequest request = new CalculatorRequest();
		request.setOperation("-");
		request.setParam1(new BigDecimal(11));
		request.setParam2(new BigDecimal(12));
		
		CalculatorResponse response  = calculatorService.calculate(request);
		
		assertEquals(-1, response.getResult().intValue());

	}

	/**
	 * Verifica que se lanza una excepcion cuando recibe un operador que no es valido
	 */
	@Test
	public void testCalculateServiceOperaionNotValid()  {
		
		CalculatorRequest request = new CalculatorRequest();
		request.setOperation("++");
		request.setParam1(new BigDecimal(11));
		request.setParam2(new BigDecimal(12));
		
		Assertions.assertThrows(OperationNotValid.class, () -> calculatorService.calculate(request) );		
		
	}
	
	/**
	 * Verifica que realiza la suma de los parametros pasados en un listado
	 * @throws OperationNotValid
	 */
	@Test
	public void testCalculateServiceAddList() throws OperationNotValid  {

		CalculatorListRequest request = new CalculatorListRequest();
		List<BigDecimal> params = new ArrayList<BigDecimal> ();
		params.add(new BigDecimal(7));
		params.add(new BigDecimal(8));
		params.add(new BigDecimal(9));
		request.setParams(params);
		request.setOperation("+");
		
		CalculatorResponse response  = calculatorService.calculate(request);
		
		assertEquals(24, response.getResult().intValue());

	}
	
	/**
	 * Verifica que realiza la resta de los parametros pasados en un listado
	 * @throws OperationNotValid
	 */
	@Test
	public void testCalculateServiceSubtractList() throws OperationNotValid  {

		CalculatorListRequest request = new CalculatorListRequest();
		List<BigDecimal> params = new ArrayList<BigDecimal> ();
		params.add(new BigDecimal(17));
		params.add(new BigDecimal(8));
		params.add(new BigDecimal(1));
		request.setParams(params);
		request.setOperation("-");
		
		CalculatorResponse response  = calculatorService.calculate(request);
		
		assertEquals(8, response.getResult().intValue());

	}
	
	/**
	 *  Verifica que se lanza una excepcion cuando recibe un operador que no es valido
	 */
	@Test
	public void testCalculateServiceListOperaionNotValid()  {
		
		CalculatorListRequest request = new CalculatorListRequest();
		List<BigDecimal> params = new ArrayList<BigDecimal> ();
		params.add(new BigDecimal(17));
		params.add(new BigDecimal(8));
		params.add(new BigDecimal(1));
		request.setParams(params);
		request.setOperation("--");
		
		Assertions.assertThrows(OperationNotValid.class, () -> calculatorService.calculate(request) );		
		
	}
	
	
	

}
