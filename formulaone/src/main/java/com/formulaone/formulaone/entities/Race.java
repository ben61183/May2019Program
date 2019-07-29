package com.formulaone.formulaone.entities;

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
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component

@Scope("prototype")
@Entity
@Table(name="FON_RACE")
@NamedQueries({@NamedQuery(name="Race.findByCountry",
		query="select d from Race d where d.country = :country")})
public class Race implements Serializable{
	@Value("0")
	private int raceID;
	@Value("default")
	@FormParam("circuit")
	private String circuit;
	@Value("default")
	@FormParam("country")
	private String country;
	@Value("0")
	@FormParam("length")
	private int length;
	
	// many races to many teams
	private Set<Driver> circuitRacers = new HashSet<>();
	
	@ManyToMany(mappedBy = "attendedRaces")
	@XmlTransient
	public Set<Driver> getCircuitRacers() {
		return circuitRacers;
	}

	public void setCircuitRacers(Set<Driver> circuitRacers) {
		this.circuitRacers = circuitRacers;
	}

	public Race(){
		System.out.println("new race initialized");
	}

	@Id
	@Column(name="raceid")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getRaceID() {
		return raceID;
	}

	public void setRaceID(int raceID) {
		this.raceID = raceID;
	}
	
	@Column(name="circuit",nullable=false)
	public String getCircuit() {
		return circuit;
	}

	public void setCircuit(String circuit) {
		this.circuit = circuit;
	}

	@Column(name="country",nullable=false)
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	@Column(name="length",nullable=false)
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public String toString() {
		return "Race [raceID=" + raceID + ", circuit=" + circuit + ", country=" + country + ", length=" + length + "]";
	}
	
}
