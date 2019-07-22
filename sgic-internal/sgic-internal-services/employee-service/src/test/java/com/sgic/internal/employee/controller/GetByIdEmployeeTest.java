package com.sgic.internal.employee.controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import com.sgic.internal.employee.EmployeeTest;
import com.sgic.internal.employee.dto.EmployeeDTO;

public class GetByIdEmployeeTest extends EmployeeTest {

	@SuppressWarnings("unused")
	private EmployeeDTO employeeDTO = new EmployeeDTO();
	private String BASE_URL = "http://localhost:8080/employeeservice";
	private String ADD_API_URL = "/createemployee";
	@SuppressWarnings("unused")
	private String GET_API_URL = "/getallemployee";
	private String GETBYID_API_URL = "/getempolyeeById/";
	private String empId = "1";

	@SuppressWarnings("unused")
	private static final String ADD_EMPLOYEE_RESPONSE = "[{\"empId\":\"1\",\"name\":\"mathu\",\"email\":\"mathu@gmail.com\",\"designation\":\"desgni1\"}]";
	@SuppressWarnings("unused")
	private static final String GETBYID_EMPLOYEE_RESPONSE = "[{\"empId\":\"1\",\"name\":\"mathu\",\"email\":\"mathu@gmail.com\",\"designation\":\"desgni1\"}]";
	private static final String GETBYID_EMPLOYEE_RESPONSE1 = "{\"empId\":\"1\",\"name\":\"mathu\",\"email\":\"mathu@gmail.com\",\"designation\":\"desgni1\"}";

	@Test
	public void addEmployee() throws IOException, RestClientException {

		EmployeeDTO employeeDTO = new EmployeeDTO("1", "mathu", "mathu@gmail.com", "desgni1");
		HttpEntity<EmployeeDTO> request = new HttpEntity<EmployeeDTO>(employeeDTO, httpHeaders);
		ResponseEntity<String> postResponse = testRestTemplate.postForEntity(BASE_URL + ADD_API_URL, request,
				String.class);
		assertEquals(200, postResponse.getStatusCodeValue());

	}

	@Test
	public void geteemployeebyid() throws IOException, RestClientException {
		ResponseEntity<String> getResponse = testRestTemplate.exchange(BASE_URL + GETBYID_API_URL + empId,
				HttpMethod.GET, new HttpEntity<>(httpHeaders), String.class);
		assertEquals(HttpStatus.OK, getResponse.getStatusCode());
		assertEquals(GETBYID_EMPLOYEE_RESPONSE1, getResponse.getBody());

	}
}
