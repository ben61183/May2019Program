package com.formulaone.formulaone.entities;

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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.ws.rs.FormParam;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component

@Scope("prototype")
@Entity
@Table(name="FON_DRIVER")
@NamedQueries({@NamedQuery(name="Driver.findByWins",
		query="select d from Driver d where d.seasonWins between :min and :max")})
public class Driver implements Serializable{
	
	@Value("0")
	private int driverID;
	@Value("default")
	@FormParam("driverName")
	private String driverName;
	@Value("0")
	@FormParam("points")
	private int points;
	@Value("0")
	@FormParam("seasonWins")
	private int seasonWins;
	
	// many drivers to many races
	private Set<Race> attendedRaces = new HashSet<>();
	
	@ManyToMany(cascade= CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name = "FON_RACEDRIVERS", 
		joinColumns = @JoinColumn(name = "FK_DRIVERID"),
		inverseJoinColumns = @JoinColumn(name = "FK_RACEID"))
//	@XmlTransient
	public Set<Race> getAttendedRaces() {
		return attendedRaces;
	}

	public void setAttendedRaces(Set<Race> raceDrivers) {
		this.attendedRaces = raceDrivers;
	}

	// many drivers to one team
	private Team currentTeam;
	
	@ManyToOne
	@JoinColumn(name="FK_TEAMID")
	public Team getCurrentTeam() {
		return currentTeam;
	}

	public void setCurrentTeam(Team currentTeam) {
		this.currentTeam = currentTeam;
	}

	public Driver(){
		System.out.println("new driver initialized");
	}

	@Id
	@Column(name="driverid")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getDriverID() {
		return driverID;
	}

	public void setDriverID(int driverID) {
		this.driverID = driverID;
	}
	
	@Column(name="drivername",nullable=false)
	public String getDriverName() {
		return driverName;
	}
	
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	@Column(name="points",nullable=true)
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Column(name="seasonwins",nullable=true)
	public int getSeasonWins() {
		return seasonWins;
	}

	public void setSeasonWins(int seasonWins) {
		this.seasonWins = seasonWins;
	}

	@Override
	public String toString() {
		return "Driver [driverID=" + driverID + ", driverName=" + driverName + ", points=" + points + ", seasonWins="
				+ seasonWins + "]";
	}

	
}
