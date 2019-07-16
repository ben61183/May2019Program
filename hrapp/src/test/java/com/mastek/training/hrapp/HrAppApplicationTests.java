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

import com.mastek.training.hrapp.apis.DepartmentService;
import com.mastek.training.hrapp.apis.EmployeeService;
import com.mastek.training.hrapp.apis.ProjectService;
import com.mastek.training.hrapp.entities.Department;
import com.mastek.training.hrapp.entities.Employee;
import com.mastek.training.hrapp.entities.Project;


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
	
	@Autowired
	DepartmentService depService;
	
	@Autowired
	ProjectService projService;
	
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
		int empno = 5;
		empService.deleteByEmpno(empno);
		assertNull(empService.findByEmpno(empno));
	}
	
	@Test
	public void checkFetchBySalary() {
		List<Employee> emps = empService.fetchEmployeesBySalaryRange(0, 150);
		for(Employee e:emps) {
			System.out.println(e);
		}
		assertTrue(emps.size()>=3);
	}
	
	@Test
	public void manageAssociations() {
		Department d1 = new Department();
		d1.setLocation("UK");
		d1.setName("admin");
		
		Employee emp1 = new Employee();
		emp1.setName("admin emp1");
		emp1.setSalary(244);
		
		Employee emp2 = new Employee();
		emp2.setName("admin emp2");
		emp2.setSalary(194.32);
		
		Project p1 = new Project();
		p1.setCustomerName("UK customer");
		p1.setName("UK project");
		
		Project p2 = new Project();
		p2.setCustomerName("US customer");
		p2.setName("US project");
		
		// one to many: one department has many employees
		d1.getMembers().add(emp1);
		d1.getMembers().add(emp2);
		
		// many to one: many employees have one department
		emp1.setCurrentDepartment(d1);
		emp2.setCurrentDepartment(d1);
		
		// many to many: one employee has many projects
		emp1.getAssignments().add(p1);
		emp1.getAssignments().add(p2);
		
		// many to many: one project has many employees
		emp2.getAssignments().add(p1);
		
		depService.registerOrUpdateDept(d1);
		empService.registerOrUpdateEmployee(emp2);
		empService.registerOrUpdateEmployee(emp1);
	}
	
	@Test
	public void contextLoads() {
	System.out.println("system test executed");
	}
}