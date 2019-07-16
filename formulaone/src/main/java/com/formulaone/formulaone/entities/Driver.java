package com.formulaone.formulaone.entities;

import java.io.Serializable;import javax.persistence.Column;
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
@Table(name="FON_DRIVER")
@NamedQueries({@NamedQuery(name="Driver.findByWins",
		query="select d from Driver d where d.seasonwins between :min and :max")})
public class Driver implements Serializable{
	
	@Value("0")
	private int driverID;
	@Value("default")
	private String driverName;
	@Value("0")
	private int points;
	@Value("0")
	private int seasonWins;
	
	// many drivers to one team
	
	Driver(){
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
