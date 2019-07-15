package com.mastek.training.hrapp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype") // prototype - one copy for each test case
@Entity // declares the class as an entity
@Table(name="JPA_EMPLOYEE") // declare table name for the class
@EntityListeners({EmployeeLifecycleListener.class})
@NamedQueries({
	@NamedQuery(name="Employee.findBySalary", // name = "<classname>.<queryname>"
			query="select e from Employee e where e.salary between :min and :max") // query = "object_query"
})
public class Employee implements Serializable{ // manage serialization of objects
	@Value("-1")
	private int empno;
	@Value("default")
	private String name;
	@Value("1.0")
	private double salary;
	// default constructor
	public Employee() {
		System.out.println("employee created");
	}
	
	@Override
	public String toString() {
		return "Employee [empno=" + empno + ", name=" + name + ", salary=" + salary + "]";
	}
	// the gets and sets
	@Id //  declare the property as primary key
	@Column(name="employee_number") // declare the name of the column
	@GeneratedValue(strategy=GenerationType.AUTO) // auto numbering 
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	@Column(name="employee_name",nullable=false,length=45)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
}
