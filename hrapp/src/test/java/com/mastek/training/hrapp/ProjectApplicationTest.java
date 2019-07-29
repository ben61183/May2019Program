package com.mastek.training.hrapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mastek.training.hrapp.apis.EmployeeService;
import com.mastek.training.hrapp.entities.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectApplicationTest {
	
	@Autowired
	EmployeeService empService;

	
	@Test
	public void exampleProjectTest() {
//		empService.registerOrUpdateEmployee(emp);
//		emp.setSalary(212.20);
		System.out.println("project test case scenario");
	}
}