package com.sanitas.calculator.service;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sanitas.calculator.exception.OperationNotValid;
import com.sanitas.calculator.request.CalulatorRequest;
import com.sanitas.calculator.response.CalculatorResponse;
import com.sanitas.calculator.service.impl.CalculatorServiceImpl;
import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class CalculatorServiceTests {
	
	@Autowired
	private CalculatorServiceImpl calculatorService;
	

	@Test
	public void testCalculateServiceAdd() throws OperationNotValid  {

		CalulatorRequest request = new CalulatorRequest();
		request.setOperation("ADD");
		request.setParam1(new BigDecimal(11));
		request.setParam2(new BigDecimal(13));
		
		CalculatorResponse response  = calculatorService.calculate(request);
		
		assertEquals(24, response.getResult().intValue());

	}
	
	@Test
	public void testCalculateServiceSustract() throws OperationNotValid {

		CalulatorRequest request = new CalulatorRequest();
		request.setOperation("SUBSTRACT");
		request.setParam1(new BigDecimal(11));
		request.setParam2(new BigDecimal(12));
		
		CalculatorResponse response  = calculatorService.calculate(request);
		
		assertEquals(-1, response.getResult().intValue());

	}

	@Test
	public void testCalculateServiceOperaionNotValid()  {
		
		CalulatorRequest request = new CalulatorRequest();
		request.setOperation("SUBSTRACT2");
		request.setParam1(new BigDecimal(11));
		request.setParam2(new BigDecimal(12));
		
		Assertions.assertThrows(OperationNotValid.class, () -> calculatorService.calculate(request) );		
		
	}

}
