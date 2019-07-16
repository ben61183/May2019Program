package com.formulaone.formulaone.entities;

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
@Table(name="FON_TEAM")
@NamedQueries({@NamedQuery(name="Team.findByEngine",
		query="select d from Team d where d.engine = :engine")})
public class Team implements Serializable{
	@Value("0")
	private int teamID;
	@Value("0")
	private int numDrivers;
	@Value("default")
	private String teamName;
	@Value("0")
	private int numStaff;
	@Value("default")
	private String engine;
	
	// many drivers to one team
	// many teams to many races
	
	Team(){
		System.out.println("new team initialized");
	}
	
	@Id
	@Column(name="teamid")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getTeamID() {
		return teamID;
	}

	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}

	@Column(name="numdrivers",nullable=false)
	public int getNumDrivers() {
		return numDrivers;
	}

	public void setNumDrivers(int numDrivers) {
		this.numDrivers = numDrivers;
	}

	@Column(name="teamname",nullable=false)
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	@Column(name="numstaff",nullable=true)
	public int getNumStaff() {
		return numStaff;
	}

	public void setNumStaff(int numStaff) {
		this.numStaff = numStaff;
	}
	
	@Column(name="engine",nullable=false)
	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	@Override
	public String toString() {
		return "Team [teamID=" + teamID + ", numDrivers=" + numDrivers + ", teamName=" + teamName + ", numStaff="
				+ numStaff + ", engine=" + engine + "]";
	}
	
}
