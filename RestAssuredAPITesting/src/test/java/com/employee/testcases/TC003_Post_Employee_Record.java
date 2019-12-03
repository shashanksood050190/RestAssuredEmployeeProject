package com.employee.testcases;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employee.base.TestBase;
import com.employee.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import junit.framework.Assert;

public class TC003_Post_Employee_Record extends TestBase{
	
	String empName=RestUtils.empName();
	String empSal=RestUtils.empSal();
	String empAge=RestUtils.empAge();
	
	@BeforeClass
	void createEmployee() throws InterruptedException {
		logger.info("*******started TC003_Post_Employee_Record******");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		JSONObject requestParams=new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("salary", empSal);
		requestParams.put("age", empAge);
		
		httpRequest.header("Content-type","application/json");
	
		httpRequest.body(requestParams.toJSONString());
		response = httpRequest.request(Method.POST, "/create");
		Thread.sleep(5000);
	}

	@Test
	void checkResponseBody() {
		logger.info("*******checking response body******");
		String responseBody = response.getBody().asString();
		logger.info("response body ==>" + responseBody);

		Assert.assertTrue(responseBody.contains(empName));
		Assert.assertTrue(responseBody.contains(empAge));
		Assert.assertTrue(responseBody.contains(empSal));

	}
	
	@Test
	void checkStatusCode() {
		logger.info("*******checking status code******");
		int statusCode = response.getStatusCode();
		logger.info("status  code ==>" + statusCode);

		Assert.assertEquals(200, statusCode);

	}
	
	@Test
	void checkStatusLine() {
		logger.info("*******checking status line******");
		String statusLine = response.getStatusLine();
		logger.info("status  code ==>" + statusLine);

		Assert.assertEquals("HTTP/1.1 200 OK", statusLine);

	}

	@Test
	void checkContentType() {
		logger.info("*******checking content type******");
		String contentType = response.getHeader("Content-Type");
		logger.info("content type   ==>" + contentType);

		Assert.assertEquals("text/html; charset=UTF-8", contentType);

	}
	

	@Test
	void checkServerType() {
		logger.info("*******checking server type******");
		String serverType = response.getHeader("Server");
		logger.info("content type   ==>" + serverType);

		Assert.assertEquals("nginx/1.16.0", serverType);

	}

	

	@Test
	void checkContentEncoding() {
		logger.info("*******checking content encoding******");
		String contentEncoding = response.getHeader("Content-Encoding");
		logger.info("content encoding   ==>" + contentEncoding);

		Assert.assertEquals(null, contentEncoding);

	}

	
	@AfterClass
	void tearDown()
	{
		logger.info("*******started TC003_Post_Employee_Record******");
	}
	
}
