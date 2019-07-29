package com.formulaone.formulaone;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.formulaone.formulaone.apis.DriverService;
import com.formulaone.formulaone.apis.RaceService;
import com.formulaone.formulaone.apis.TeamService;
import com.formulaone.formulaone.entities.Driver;
import com.formulaone.formulaone.entities.Race;
import com.formulaone.formulaone.entities.Team;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FormulaoneApplicationTests {
	
	@Autowired
	DriverService driSer;
	@Autowired
	TeamService teaSer;
	@Autowired
	RaceService racSer;
	
	@Ignore
	@Test
	public void addDriver() {
		Driver d = new Driver();
		d.setDriverName("Grojan");
		d.setPoints(27);
		d.setSeasonWins(0);
		driSer.addOrUpdateDriver(d);
		assertNotNull(driSer);
	}
	
	@Ignore
	@Test
	public void addTeam() {
		Team t = new Team();
		t.setEngine("Honda");
		t.setNumDrivers(2);
		t.setNumStaff(21);
		t.setTeamName("Renault");
		teaSer.addOrUpdateTeam(t);
		assertNotNull(teaSer);
	}
	
	@Ignore
	@Test
	public void addCircuit() {
		Race r = new Race();
		r.setCircuit("Abu Dhabi");
		r.setCountry("UAE");
		r.setLength(15);
		racSer.addOrUpdateRace(r);
		assertNotNull(racSer);
	}
	
	@Test
	public void findDriverService() {
		int id = 80;
		driSer.findDriver(id);
	}
	
	@Test
	public void findRaceService() {
		int id = 78;
		racSer.findRace(id);
	}
	
	@Test
	public void findTeamService() {
		int id = 79;
		teaSer.findTeam(id);
	}
	
	@Ignore
	@Test
	public void deleteDriverService() {
		int id = 98;
		driSer.deleteDriver(id);
	}
	
	@Ignore
	@Test
	public void deleteTeamService() {
		int id = 97;
		teaSer.deleteTeam(id);
	}
	
	@Ignore
	@Test
	public void deleteRaceService() {
		int id = 96;
		racSer.deleteRace(id);
	}
	
	@Test
	public void fetchDriverByWins() {
		int min = 2;
		int max = 4;
		List<Driver> drivs = driSer.fetchByWins(min, max);
		for(Driver driv:drivs) {
			System.out.println(driv);
		}
	}
	
	@Test
	public void fetchTeamByEngine() {
		List<Team> teas = teaSer.fetchByEngine("Honda");
		for(Team tea:teas) {
			System.out.println(tea);
		}
	}
	
	@Test
	public void fetchRaceByCountry() {
		List<Race> racs = racSer.fetchByCountry("Monacco");
		for(Race rac:racs) {
			System.out.println(rac);
		}
	}
	
	@Test
	public void attemptTwo() {
		Driver d1 = new Driver();
		d1.setDriverName("ATEST D1");
		d1.setSeasonWins(1);
		d1.setPoints(44);
		
		Driver d2 = new Driver();
		d2.setDriverName("ATEST D2");
		d2.setSeasonWins(0);
		d2.setPoints(27);
		
		Driver d3 = new Driver();
		d3.setDriverName("ATEST D3");
		d3.setPoints(41);
		d3.setSeasonWins(1);
		
		Team t1 = new Team();
		t1.setEngine("ATEST T1");
		t1.setNumDrivers(2);
		t1.setNumStaff(19);
		t1.setTeamName("ATEST T1");
		
		Team t2 = new Team();
		t2.setEngine("ATEST T2");
		t2.setNumDrivers(2);
		t2.setNumStaff(19);
		t2.setTeamName("ATEST T2");
		
		Race r1 = new Race();
		r1.setCircuit("ATEST R1");
		r1.setCountry("China");
		r1.setLength(9);
		
		Race r2 = new Race();
		r2.setCircuit("ATEST R2");
		r2.setCountry("Bahrain");
		r2.setLength(6);
		
		// members to a team
		t1.getTeamMembers().add(d1); 
		t1.getTeamMembers().add(d2); 
		t2.getTeamMembers().add(d3); 
		d1.setCurrentTeam(t1);		
		d2.setCurrentTeam(t1);		
		d3.setCurrentTeam(t2);
		
		// drivers to races and races to drivers
		d1.getAttendedRaces().add(r1);
		d1.getAttendedRaces().add(r2);
		
//		d2.getAttendedRaces().add(r2);
		
		try {
		teaSer.addOrUpdateTeam(t1);
		teaSer.addOrUpdateTeam(t2);
		
		driSer.addOrUpdateDriver(d1);
		driSer.addOrUpdateDriver(d2);
		driSer.addOrUpdateDriver(d3);
		
		racSer.addOrUpdateRace(r1);
		racSer.addOrUpdateRace(r2);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}