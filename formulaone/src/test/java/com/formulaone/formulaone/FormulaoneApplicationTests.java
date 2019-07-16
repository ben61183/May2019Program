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
	@Autowired
	Driver d;
	@Autowired
	Team t;
	@Autowired
	Race r;
	
	@Ignore
	@Test
	public void addDriver() {
		d.setDriverName("Vettel");
		d.setPoints(43);
		d.setSeasonWins(1);
		driSer.addOrUpdateDriver(d);
		assertNotNull(driSer);
	}
	
	@Ignore
	@Test
	public void addTeam() {
		t.setEngine("Ferrari");
		t.setNumDrivers(2);
		t.setNumStaff(29);
		t.setTeamName("Ferrari");
		teaSer.addOrUpdateTeam(t);
		assertNotNull(teaSer);
	}
	
	@Ignore
	@Test
	public void addCircuit() {
		r.setCircuit("Nurburgring");
		r.setCountry("Germany");
		r.setLength(19);
		racSer.addOrUpdateDriver(r);
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
		int id = 86;
		driSer.deleteDriver(id);
	}
	
	@Ignore
	@Test
	public void deleteTeamService() {
		int id = 85;
		teaSer.deleteTeam(id);
	}
	
	@Ignore
	@Test
	public void deleteRaceService() {
		int id = 84;
		racSer.deleteRace(id);
	}
	
	@Test
	public void fetchDriverByWins() {
		List<Driver> drivs = driSer.fetchByWins(2, 4);
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
}
