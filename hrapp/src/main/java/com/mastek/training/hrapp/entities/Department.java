package com.mastek.training.hrapp.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Entity
@Table(name="JPA_DEPARTMENT")
@NamedQueries({@NamedQuery(name="Department.findByLocation",
		query="select d from Department d where d.location = :loc")})
public class Department implements Serializable{
	@Value("-1")
	private int deptno;
	@Value("default")
	@FormParam("name")
	private String name;
	@Value("default")
	@FormParam("location")
	private String location;
	
	// OneToMany: one dept has many employees
	private Set<Employee> members = new HashSet<>();
	
	public Department() {
		System.out.println("department created");
	}
	
	//@OneToMany: used ont eh get method of set to configure associations
	// fetch=	LAZY: delay initialization until method getMetmbers() is called using additional select query [default]
	//			EAGER: initialize the collection on entity find post load event
	// cascade=	ALL: all entity operations done on department would be performed on each associated employee object
	//			PERSIST/MERGE/DELETE/REFRESH
	// mappedBy= identifies property names in child class where the JoinColumn [Foreign Key] configuration is present
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL, mappedBy="currentDepartment")
	@XmlTransient // ignore the collections while using API
	public Set<Employee> getMembers() {
		return members;
	}

	public void setMembers(Set<Employee> members) {
		this.members = members;
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
