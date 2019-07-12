package com.mastek.training.hrapp.apis;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mastek.training.hrapp.entities.Employee;

// @Component: indicate to spring to create an object of this class as component
// @Scope: singleton [default] - one object for app, prototype - one object for each use

@Component
@Scope("singleton")
public class EmployeeService {
	
	public EmployeeService() {
		System.out.println("employee service created");
	}
	
	public Employee registerEmployee(Employee emp) {
		System.out.println("employee registered"+emp);
		return emp;
	}
	
}
