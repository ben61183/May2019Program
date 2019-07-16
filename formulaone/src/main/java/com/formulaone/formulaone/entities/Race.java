package com.formulaone.formulaone.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Table(name="F1_RACE")
//@NamedQueries({@NamedQuery(name="Department.findByLocation",
//		query="select d from Department d where d.location = :loc")})
public class Race implements Serializable{
	
	private String circuit;
	private String country;
	private int turns;
}
