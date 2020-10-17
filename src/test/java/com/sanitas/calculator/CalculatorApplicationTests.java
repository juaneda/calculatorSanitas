package com.sanitas.calculator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
import com.sanitas.calculator.request.CalulatorRequest;
import com.sanitas.calculator.response.CalculatorResponse;
import com.sanitas.calculator.service.impl.CalculatorServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CalculatorApplication.class)
@WebAppConfiguration
class CalculatorApplicationTests {

	protected MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Mock
	CalculatorServiceImpl calculator;

	@Test
	void contextLoads() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testCalculate() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		String uri = "/sanitas/calculate";
		CalulatorRequest request = new CalulatorRequest();
		request.setParam1(7);
		request.setParam2(8);
		request.setOperation("ADD");

		String inputJson = mapToJson(request);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String json = mvcResult.getResponse().getContentAsString();
		CalculatorResponse response = mapToObject(json);
		assertEquals(15, response.getResult().intValue());

	}
	
	@Test
	public void testCalculateWhenParam1IsNullTest() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		String uri = "/sanitas/calculate";
		CalulatorRequest request = new CalulatorRequest();
		request.setParam2(8);
		request.setOperation("ADD");

		String inputJson = mapToJson(request);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);

	}
	
	@Test
	public void testCalculateWhenParam2IsNullTest() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		String uri = "/sanitas/calculate";
		CalulatorRequest request = new CalulatorRequest();
		request.setParam1(8);
		request.setOperation("ADD");

		String inputJson = mapToJson(request);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);

	}
	
	@Test
	public void testCalculateWhenOperationIsNullTest() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		String uri = "/sanitas/calculate";
		CalulatorRequest request = new CalulatorRequest();
		request.setParam1(8);
		request.setParam2(8);

		String inputJson = mapToJson(request);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);

	}
	
	@Test
	public void testCalculateWhenParam1IsNotNumber() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		String uri = "/sanitas/calculate";
		CalulatorRequest request = new CalulatorRequest();
		request.setParam1(7);
		request.setParam2(8);
		request.setOperation("ADD");

		String inputJson ="\"{\"param1\":\"valor\",\"param2\":8,\"operation\":\"ADD\"}\"";
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);

	}
	
	@Test
	public void testCalculateWhenParam2IsNotNumber() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		String uri = "/sanitas/calculate";
		CalulatorRequest request = new CalulatorRequest();
		request.setParam1(7);
		request.setParam2(8);
		request.setOperation("ADD");

		String inputJson ="\"{\"param1\":4 ,\"param2\":\"valor\",\"operation\":\"ADD\"}\"";
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);

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
