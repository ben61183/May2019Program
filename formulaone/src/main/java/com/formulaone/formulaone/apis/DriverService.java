package com.formulaone.formulaone.apis;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.formulaone.formulaone.entities.Driver;
import com.formulaone.formulaone.entities.Race;
import com.formulaone.formulaone.entities.Team;
import com.formulaone.formulaone.repositories.DriverRepository;
import com.formulaone.formulaone.repositories.RaceRepository;
import com.formulaone.formulaone.repositories.TeamRepository;


@Component
@Scope("singleton")
@Path("/driver/")
public class DriverService {

	@Autowired
	private DriverRepository driRep;
	
	@Autowired
	private TeamService teaSer;
	
	@Autowired
	private RaceService racSer;
	
	DriverService(){
		System.out.println("driver service added");
	}
	
	@POST // http method to ssend the form data
	@Path("/add") // url pattern
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) // form data
	@Produces(MediaType.APPLICATION_JSON) // json data
	public Driver addOrUpdateDriver(@BeanParam Driver d) {
		d = driRep.save(d);
		System.out.println("driver "+d.getDriverID()+" registered");
		return d;
	}

	@GET
	@Path("/find/{driverID}")
	@Produces({ // declare all possible content types of return value
		MediaType.APPLICATION_JSON, // object to be given in JSON
		MediaType.APPLICATION_XML // or object to be given in XML
	})
	@Transactional
	public Driver findDriver(@PathParam("driverID") int id) {
		try{
			Driver dri = driRep.findById(id).get();
			System.out.println(dri.getAttendedRaces().size()+" ass's passed");
			return dri;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@DELETE
	@Path("/delete/{driverID}")
	public void deleteDriver(@PathParam("driverID") int id) {
		driRep.deleteById(id);
	}
	
	@Path("/fetch_wins")
	@GET // http method used to call the api
	@Produces({ // declare all possible content types of return value
		MediaType.APPLICATION_JSON, // object to be given in JSON
	})
	public List<Driver> fetchByWins(@QueryParam("min") int min, @QueryParam("max") int max){
		return driRep.findByWins(min, max);
	}

	@Transactional
	@POST
	@Path("/assign/team")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Driver assignTeam(@FormParam("driverID") int driverID, @FormParam("teamID") int teamID) {
		try {
			// fetch entities to be associated
			Driver dri = findDriver(driverID);
			Team tea = teaSer.findTeam(teamID);
			// manage the association
			dri.setCurrentTeam(tea); // one assigned with many
			tea.getTeamMembers().add(dri);
			dri = addOrUpdateDriver(dri);
			return dri;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	@POST
	@Path("/assign/race")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
		public Set<Race> assignRace(@FormParam("driverID") int driverID, @FormParam("raceID") int raceID) {
		try {
			// fetch entities to be associated
			Driver dri = findDriver(driverID);
			Race rac = racSer.findRace(raceID);
			// associate employee with project
			dri.getAttendedRaces().add(rac); 
			dri = addOrUpdateDriver(dri);
			return dri.getAttendedRaces();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
