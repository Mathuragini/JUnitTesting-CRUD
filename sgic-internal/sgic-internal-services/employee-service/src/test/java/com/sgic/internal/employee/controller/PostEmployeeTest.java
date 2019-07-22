package com.sgic.internal.employee.controller;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestClientException;
import com.sgic.internal.employee.EmployeeTest;
import com.sgic.internal.employee.dto.EmployeeDTO;

public class PostEmployeeTest extends EmployeeTest {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@SuppressWarnings("unused")
	private EmployeeDTO employeeDTO = new EmployeeDTO();
	private String BASE_URL = "http://localhost:8080/employeeservice";
	private String ADD_API_URL = "/createemployee";

	@SuppressWarnings("unused")
	private static final String ADD_EMPLOYEE_RESPONSE = "[{\"empId\":\"1\",\"name\":\"mathu\",\"email\":\"mathu@gmail.com\",\"designation\":\"desgni1\"}]";

	@Test
	public void addEmployee() throws IOException, RestClientException {
		EmployeeDTO employeeDTO = new EmployeeDTO("1", "mathu", "mathu@gmail.com", "desgni1");
		HttpEntity<EmployeeDTO> request = new HttpEntity<EmployeeDTO>(employeeDTO, httpHeaders);
		ResponseEntity<String> postResponse = testRestTemplate.postForEntity(BASE_URL + ADD_API_URL, request,
				String.class);
		assertEquals(200, postResponse.getStatusCodeValue());
	}

}
