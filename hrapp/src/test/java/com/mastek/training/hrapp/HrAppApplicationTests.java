package com.mastek.training.hrapp;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
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
		emp.setEmpno(5);
		emp.setName("Roger");
		emp.setSalary(112.1);
		empService.registerOrUpdateEmployee(emp);
		assertNotNull(emp);
	}
	
	@Test
	public void findByEmpnoUsingService() {
		int empno = 1;
		System.out.println("found: "+empService.findByEmpno(empno));
		assertNotNull(empService.findByEmpno(empno));
	}
	
	@Ignore
	@Test
	public void deleteByEmpnoUsingService() {
		int empno = 3;
		empService.deleteByEmpno(empno);
		assertNull(empService.findByEmpno(empno));
	}
	
	@Test
	public void checkFetchBySalary() {
		List<Employee> emps = empService.fetchEmployeesBySalaryRange(0, 150);
		for(Employee e:emps) {
			System.out.println(e);
		}
		assertTrue(emps.size()==3);
	}
	
	@Test
	public void contextLoads() {
	System.out.println("system test executed");
	}
}