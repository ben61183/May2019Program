package com.formulaone.formulaone.apis;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import com.formulaone.formulaone.repositories.TeamRepository;

@Component
@Scope("singleton")
@Path("/team/")
public class TeamService {

	@Autowired
	TeamRepository teaRep;
	
	TeamService(){
		System.out.println("team service added");
	}
	
	@POST // http method to ssend the form data
	@Path("/add") // url pattern
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) // form data
	@Produces(MediaType.APPLICATION_JSON) // json data
	public Team addOrUpdateTeam(@BeanParam Team d) {
		d = teaRep.save(d);
		System.out.println("team "+d.getTeamID()+" registered");
		return d;
	}
	
	@GET
	@Path("/find/{teamID}")
	@Produces({ // declare all possible content types of return value
		MediaType.APPLICATION_JSON, // object to be given in JSON
		MediaType.APPLICATION_XML // or object to be given in XML
	})
	public Team findTeam(@PathParam("teamID") int id) {
		try{
		return teaRep.findById(id).get();
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@DELETE
	@Path("/delete/{teamID}")
	public void deleteTeam(@PathParam("teamID") int id) {
		teaRep.deleteById(id);
	}
	
	@Path("/fetch_engine")
	@GET // http method used to call the api
	@Produces({ // declare all possible content types of return value
		MediaType.APPLICATION_JSON, // object to be given in JSON
	})
	public List<Team> fetchByEngine(@QueryParam("engine") String engine){
		return teaRep.findByEngine(engine);
	}
}
