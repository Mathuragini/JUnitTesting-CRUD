package com.sgic.internal.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.sgic.internal.employee.EmployeeTest;
import com.sgic.internal.employee.dto.EmployeeDTO;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

public class GetEmployeeTest extends EmployeeTest {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@SuppressWarnings("unused")
	private EmployeeDTO employeeDTO = new EmployeeDTO();
	private String BASE_URL = "http://localhost:8080/employeeservice";
	private String ADD_API_URL = "/createemployee";
	private String GET_API_URL = "/getallemployee";

	private static final String ADD_EMPLOYEE_RESPONSE =  "[{\"empId\":\"1\",\"name\":\"mathu\",\"email\":\"mathu@gmail.com\",\"designation\":\"desgni1\"}]";
	@SuppressWarnings("unused")
	private static final String GET_EMPLOYEE_RESPONSE =  "{\"empId\":\"1\",\"name\":\"mathu\",\"email\":\"mathu@gmail.com\",\"designation\":\"desgni1\"}";

	@Test
	public void addEmployee() throws IOException, RestClientException {
//	EmployeeDTO.setMainClassName("ABC");
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
		assertEquals(ADD_EMPLOYEE_RESPONSE, response.getBody());

	}

}