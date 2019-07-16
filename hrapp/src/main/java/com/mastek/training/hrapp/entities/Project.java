package com.mastek.training.hrapp.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Table(name="JPA_PROJECT")
@NamedQueries({@NamedQuery(name="Project.findByLocation",
		query="select d from Project d where d.customerName = :cust")})
public class Project implements Serializable{
	@Value("-1")
	private int projectID;
	@Value("default")
	private String name;
	@Value("default")
	private String customerName;
	
	// ManyToMany: mapping many projects to many employees
	private Set<Employee> team = new HashSet<>();
	
	// mappedBy: check the configuration for many to many association in employee class getAssignments() method
	@ManyToMany(mappedBy = "assignments")
	public Set<Employee> getTeam() {
		return team;
	}

	public void setTeam(Set<Employee> team) {
		this.team = team;
	}

	public Project() {
		System.out.println("project created");
	}

	@Override
	public String toString() {
		return "Project [projectID=" + projectID + ", name=" + name + ", customerName=" + customerName + "]";
	}
	
	@Id
	@Column(name="projectID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	@Column(name="name",nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="customer",nullable=false)
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}		
}
