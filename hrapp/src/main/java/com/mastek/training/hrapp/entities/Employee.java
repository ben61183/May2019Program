package com.mastek.training.hrapp.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component // disable for form parameter processing

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
	@FormParam("empno")
	private int empno;
	@Value("default")
	@FormParam("name") // name of parameters passed via html form
	private String name;
	@Value("1.0")
	@FormParam("salary")
	private double salary;
	
	// ManyToMany: mapping many projects to many employees
	private Set<Project> assignments = new HashSet<>();
	
	// @ManyToMany: configuring association for both the entities
	// @JoinTable: provides all the configuration fo the third table
	// name: name of the join table
	// joinColumns: foreign key column name fir current class
	// inverseJoinColumns: foreign key column for other class
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY) // has cascade -> primary class
	@JoinTable(name = "JPA_ASSIGNMENTS", 
		joinColumns = @JoinColumn(name = "FK_EMPNO"),
		inverseJoinColumns = @JoinColumn(name = "FK_PROJECTID"))
//	@XmlTransient // ignore the collections while using API
	public Set<Project> getAssignments() {
		return assignments;
	}
	
	public void setAssignments(Set<Project> assignments) {
		this.assignments = assignments;
	}
	
	// ManyToOne: mapping each employee to one department
	private Department currentDepartment;
	
	// @ManyToOne: associating the many class to one object
	// @JoinColumn: configure the foreign key column for the association between two entites
	@ManyToOne
	@JoinColumn(name="FK_DepartmentId")
	public Department getCurrentDepartment() {
		return currentDepartment;
	}

	public void setCurrentDepartment(Department currentDepartment) {
		this.currentDepartment = currentDepartment;
	}

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
