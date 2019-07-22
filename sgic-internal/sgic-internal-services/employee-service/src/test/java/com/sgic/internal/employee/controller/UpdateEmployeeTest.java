package com.sgic.internal.employee.controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestClientException;
import com.sgic.internal.employee.EmployeeTest;
import com.sgic.internal.employee.dto.EmployeeDTO;

public class UpdateEmployeeTest extends EmployeeTest {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@SuppressWarnings("unused")
	private EmployeeDTO employeeDTO = new EmployeeDTO();
	private String BASE_URL = "http://localhost:8080/employeeservice";
	private String ADD_API_URL = "/createemployee";
	private String GET_API_URL = "/getallemployee";
	private String PUT_API_URL = "/update/";
	private String empId = "1";

	private static final String ADD_EMPLOYEE_RESPONSE =  "[{\"empId\":\"1\",\"name\":\"mathu\",\"email\":\"mathu@gmail.com\",\"designation\":\"desgni1\"}]";
	private static final String GET_EMPLOYEE_RESPONSE =  "{\"empId\":\"1\",\"name\":\"mathu\",\"email\":\"mathu@gmail.com\",\"designation\":\"desgni1\"}";
	private static final String PUT_EMPLOYEE_RESPONSE =  "[{\"empId\":\"1\",\"name\":\"mathu\",\"email\":\"mathu@gmail.com\",\"designation\":\"desgni1\"}]";

	@Test
	public void addEmployee() throws IOException, RestClientException {
		EmployeeDTO employeeDTO = new EmployeeDTO("1", "mathu", "mathu@gmail.com", "desgni1");
		HttpEntity<EmployeeDTO> request = new HttpEntity<EmployeeDTO>(employeeDTO, httpHeaders);
		ResponseEntity<String> postResponse = testRestTemplate.postForEntity(BASE_URL + ADD_API_URL, request,
				String.class);
		assertEquals(200, postResponse.getStatusCodeValue());
	}

	@Test
	public void geteemployee() throws IOException, RestClientException {
		ResponseEntity<String> response = testRestTemplate.exchange(BASE_URL + GET_API_URL, HttpMethod.GET,
				new HttpEntity<>(httpHeaders), String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
//		assertEquals( GET_EMPLOYEE_RESPONSE, response.getBody());

	}

	@Test
	public void updateemployee() throws IOException, RestClientException {
		EmployeeDTO employeeDTO = new EmployeeDTO("emp002", "ABC", "saidputhi@gmail.com", "QA");
		HttpEntity<EmployeeDTO> updateRequest = new HttpEntity<EmployeeDTO>(employeeDTO,
				httpHeaders);
		ResponseEntity<String> putResponse = testRestTemplate.exchange(BASE_URL + PUT_API_URL+ empId, HttpMethod.PUT,
				updateRequest, String.class);
		assertEquals(200, putResponse.getStatusCodeValue());

		ResponseEntity<String> getResponse = testRestTemplate.exchange(BASE_URL + GET_API_URL, HttpMethod.GET,
				new HttpEntity<>(httpHeaders), String.class);
		assertEquals(HttpStatus.OK, getResponse.getStatusCode());
		assertEquals(PUT_EMPLOYEE_RESPONSE, getResponse.getBody());
	}
}