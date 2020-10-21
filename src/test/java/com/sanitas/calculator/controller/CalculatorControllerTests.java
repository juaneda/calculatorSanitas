package com.sanitas.calculator.controller;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanitas.calculator.CalculatorApplication;
import com.sanitas.calculator.request.CalulatorListRequest;
import com.sanitas.calculator.request.CalulatorRequest;
import com.sanitas.calculator.response.CalculatorResponse;

/**
 * @author juane
 *
 */
/**
 * @author juane
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CalculatorApplication.class)
@WebAppConfiguration
class CalculatorControllerTests {

	protected MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationContext;


	/**
	 * Verifica  la llamada al endpoint encargado de realizar la suma de dos parametros
	 * @throws Exception
	 */
	@Test
	public void testCalculate() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		String uri = "/sanitas/calculate";
		CalulatorRequest request = new CalulatorRequest();
		request.setParam1(new BigDecimal(7));
		request.setParam2(new BigDecimal(9));
		request.setOperation("+");

		String inputJson = mapToJson(request);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String json = mvcResult.getResponse().getContentAsString();
		CalculatorResponse response = mapToObject(json);
		
		assertEquals(16, response.getResult().intValue());

	}
	
	/**
	 * Verifica  que el servicio devuelve un BadRequest cuando el primer parametro es nulo
	 * @throws Exception
	 */
	@Test
	public void testCalculateWhenParam1IsNullTest() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		String uri = "/sanitas/calculate";
		CalulatorRequest request = new CalulatorRequest();
		request.setParam2(new BigDecimal(7));
		request.setOperation("+");

		String inputJson = mapToJson(request);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);

	}
	
	/**
	 * Verifica  que el servicio devuelve un BadRequest cuando el segundo parametro es nulo
	 * @throws Exception
	 */
	@Test
	public void testCalculateWhenParam2IsNullTest() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		String uri = "/sanitas/calculate";
		CalulatorRequest request = new CalulatorRequest();
		request.setParam1(new BigDecimal(7));
		request.setOperation("+");

		String inputJson = mapToJson(request);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);

	}
	
	/**
	 * Verifica  que el servicio devuelve un BadRequest cuando el operador es nulo
	 * @throws Exception
	 */
	@Test
	public void testCalculateWhenOperationIsNullTest() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		String uri = "/sanitas/calculate";
		CalulatorRequest request = new CalulatorRequest();
		request.setParam1(new BigDecimal(7));
		request.setParam2(new BigDecimal(7));

		String inputJson = mapToJson(request);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);

	}
	
	/**
	 * Verifica  que el servicio devuelve un BadRequest cuando el primer parametro no es un numero
	 * @throws Exception
	 */
	@Test
	public void testCalculateWhenParam1IsNotNumber() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		String uri = "/sanitas/calculate";

		String inputJson ="{\"param1\":\"valor\",\"param2\":8,\"operation\":\"+\"}";
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);

	}
	
	/**
	 * Verifica  que el servicio devuelve un BadRequest cuando el segundo parametro no es un numero
	 * @throws Exception
	 */
	@Test
	public void testCalculateWhenParam2IsNotNumber() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		String uri = "/sanitas/calculate";

		String inputJson ="{\"param1\":4 ,\"param2\":\"valor\",\"operation\":\"+\"}";
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);

	}
	
	/**
	 * Verifica  que el servicio devuelve un BadRequest cuando el operador no es  valido.
	 * @throws Exception
	 */
	@Test
	public void testCalculateNotValidOperation() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		String uri = "/sanitas/calculate";
		CalulatorRequest request = new CalulatorRequest();
		request.setParam1(new BigDecimal(7));
		request.setParam2(new BigDecimal(9));
		request.setOperation("++");

		String inputJson = mapToJson(request);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals("Operaion not valid: ++", response);

	}

	
	/**
	 * Verifica  la llamada al endpoint encargado de realizar la suma un listados de numeros
	 * @throws Exception
	 */
	@Test
	public void testCalculateList() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		String uri = "/sanitas/calculate/list";
		CalulatorListRequest request = new CalulatorListRequest();
		List<BigDecimal> params = new ArrayList<BigDecimal> ();
		params.add(new BigDecimal(7));
		params.add(new BigDecimal(8));
		params.add(new BigDecimal(9));
		request.setParams(params);
		request.setOperation("+");

		String inputJson = mapToJson(request);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String json = mvcResult.getResponse().getContentAsString();
		CalculatorResponse response = mapToObject(json);
		
		assertEquals(24, response.getResult().intValue());

	}
	
	/**
	 * Verifica  que el servicio devuelve un BadRequest cuando existe un valor no numerico en la lista.
	 * @throws Exception
	 */
	@Test
	public void testCalculateListWhereExistNotNumber() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		String uri = "/sanitas/calculate/list";
		
		String inputJson ="{\"params\": \"[\"1\", \"a\"  ] \",\"operation\":\"+\"}";
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);

	}
	
	/**
	 * Verifica  que el servicio devuelve un BadRequest cuando el operador no es  valido.
	 * @throws Exception
	 */
	@Test
	public void testCalculateListWhereOperatorIsNotValid() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		String uri = "/sanitas/calculate/list";
		
		String inputJson ="{\"params\": [\"1\", \"1\" ] ,\"operation\":\"++\"}";
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);
		
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals("Operaion not valid: ++", response);

	}
	
	
	
	private String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}
	
	private CalculatorResponse mapToObject(String json) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, CalculatorResponse.class);
	}


}
