package com.formulaone.formulaone.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;


//@Component

@Scope("prototype")
@Entity
@Table(name="FON_TEAM")
@NamedQueries({@NamedQuery(name="Team.findByEngine",
		query="select d from Team d where d.engine = :engine")})
public class Team implements Serializable{
	@Value("0")
	private int teamID;
	@Value("0")
	@FormParam("numDrivers")
	private int numDrivers;
	@Value("default")
	@FormParam("teamName")
	private String teamName;
	@Value("0")
	@FormParam("numStaff")
	private int numStaff;
	@Value("default")
	@FormParam("engine")
	private String engine;
	
	private Set<Driver> teamMembers = new HashSet<>();
	
	// many drivers to one team
	// many teams to many races
	
	public Team(){
		System.out.println("new team initialized");
	}
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="currentTeam")
	@XmlTransient
	public Set<Driver> getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(Set<Driver> teamMembers) {
		this.teamMembers = teamMembers;
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
