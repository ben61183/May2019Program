package com.mastek.training.hrapp.apis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mastek.training.hrapp.entities.Employee;
import com.mastek.training.hrapp.repositories.EmployeeRepository;

// @Component: indicate to spring to create an object of this class as component
// @Scope: singleton [default] - one object for app, prototype - one object for each use

@Component
@Scope("singleton")
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepository;
	
	public EmployeeService() {
		System.out.println("employee service created");
	}
	
	public Employee registerOrUpdateEmployee(Employee emp) {
		emp = empRepository.save(emp); // save will save or update (if existing key)
		System.out.println("employee registered"+emp);
		return emp;
	}

	public Employee findByEmpno(int empno) {
		// fetches the employee details from the db by empno
		try {
			return empRepository.findById(empno).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void deleteByEmpno(int empno) {
		empRepository.deleteById(empno);
	}
	
	public List<Employee> fetchEmployeesBySalaryRange(double min, double max){
		return empRepository.findBySalary(min, max);
	}
	
}
