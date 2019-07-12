package com.mastek.training.hrapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mastek.training.hrapp.apis.EmployeeService;
import com.mastek.training.hrapp.entities.Employee;


// initialize the JUnit test with spring boot application environment
@RunWith(SpringRunner.class)

//spring boot test: used to test spring application context with all test cases identified
@SpringBootTest
public class HrAppApplicationTests {
	
	// scan in memory all components and provide the best match object in the component
	@Autowired
	EmployeeService empService;
	@Autowired
	Employee emp;
	
	@Test
	public void addEmployeeUsingService() {
		empService.registerEmployee(emp);
	}
	
	@Test
	public void contextLoads() {
	System.out.println("system test executed");
	}
}