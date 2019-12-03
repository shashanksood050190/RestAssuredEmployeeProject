package com.employee.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employee.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class TC005_Delete_Employee_Record extends TestBase{
	
	@BeforeClass
	void deleteEmployee() throws InterruptedException
	{
		logger.info("*******started TC005_Delete_Employee_Record******");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		
		httpRequest.header("Content-type","application/json");
	
		response = httpRequest.request(Method.GET, "/employees");
		JsonPath jsonPathEvaluator=response.jsonPath();
		String empID= jsonPathEvaluator.get("[0].id");
		logger.info("empID ==>"+empID);
		response=httpRequest.request(Method.DELETE, "/delete/"+empID);
		Thread.sleep(5000);
	}

	

	@Test
	void checkResponseBody() {
		logger.info("*******checking response body******");
		String responseBody = response.getBody().asString();
		logger.info("response body ==>" + responseBody);

		Assert.assertTrue(responseBody.contains("successfully! deleted records"));
		

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

		Assert.assertEquals("Apache", serverType);

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
		logger.info("*******started TC005_Delete_Employee_Record******");
	}
	
}
