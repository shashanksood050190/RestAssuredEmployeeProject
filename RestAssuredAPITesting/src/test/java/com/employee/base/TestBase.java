package com.employee.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	
	public static RequestSpecification httpRequest;
	public static Response response;
	public String empID="1"; //HardCoded - Input for Get Details of single employee and update employee
	
	public static Logger logger;

	@BeforeClass
	public void setUp() {
		
		 logger = Logger.getLogger("EmployeesRstAPI"); //added logger
		 PropertyConfigurator.configure("log4j.properties"); //added logger
		 logger.setLevel(Level.DEBUG);
	}
	
	
}
