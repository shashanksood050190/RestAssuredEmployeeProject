package com.employee.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employee.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import junit.framework.Assert;

public class TC001_Get_All_Employees extends TestBase {

	@BeforeClass
	void getAllEmployees() throws InterruptedException {
		logger.info("*******started TC001_Get_All_Employees******");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees");
		Thread.sleep(3000);
	}

	@Test
	void checkResponseBody() {
		logger.info("*******checking response body******");
		String responseBody = response.getBody().toString();
		logger.info("response body ==>" + responseBody);

		Assert.assertTrue(responseBody != null);

	}

	@Test
	void checkStatusCode() {
		logger.info("*******checking status code******");
		int statusCode = response.getStatusCode();
		logger.info("status  code ==>" + statusCode);

		Assert.assertEquals(200, statusCode);

	}

	@Test
	void checkResponseTime() {
		logger.info("*******checking response time******");
		long responseTime = response.getTime();
		logger.info("response time  ==>" + responseTime);
		if (responseTime > 2000)
			logger.warn("Response time is greater than 2000");
		Assert.assertTrue(responseTime < 10000);

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

		Assert.assertEquals("gzip", contentEncoding);

	}

	@Test
	void checkContentLength() {
		logger.info("*******checking content length******");
		String contentLength = response.getHeader("Content-Length");
		logger.info("content Length  ==>" + contentLength);
		if (Integer.parseInt(contentLength) < 100)
			logger.warn("content length is less than 100");
		Assert.assertTrue(Integer.parseInt(contentLength) > 100);

	}

	@Test
	void checkCookies() {
		logger.info("*******checking cookies ******");
		String cookie = response.getCookie("PHPSESSID");

	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("****** finished TC001_Get_All_Employess*************");
	}

}
