package com.mastek.training.hrapp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Scope("prototype")
@Entity
@Table(name="JPA_DEPARTMENT")
@NamedQueries({@NamedQuery(name="Department.findByLocation",
		query="select d from Department d where d.location = :loc")})
public class Department implements Serializable{
	@Value("-1")
	private int deptno;
	@Value("default")
	private String name;
	@Value("default")
	private String location;
	
	public Department() {
		System.out.println("department created");
	}

	@Override
	public String toString() {
		return "Department [deptno=" + deptno + ", name=" + name + ", location=" + location + "]";
	}

	@Id
	@Column(name="deptno")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	
	@Column(name="name",nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="location",nullable=false)
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
		
}
