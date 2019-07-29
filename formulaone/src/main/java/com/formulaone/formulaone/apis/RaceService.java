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

import com.formulaone.formulaone.entities.Race;
import com.formulaone.formulaone.repositories.RaceRepository;

@Component
@Scope("singleton")
@Path("/race/")
public class RaceService {

	@Autowired
	RaceRepository racRep;
	
	RaceService(){
		System.out.println("driver service added");
	}
		
	@POST // http method to ssend the form data
	@Path("/add") // url pattern
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) // form data
	@Produces(MediaType.APPLICATION_JSON) // json data
	public Race addOrUpdateRace(@BeanParam Race d) {
		d = racRep.save(d);
		System.out.println("race "+d.getRaceID()+" registered");
		return d;
	}

	@GET
	@Path("/find/{raceID}")
	@Produces({ // declare all possible content types of return value
		MediaType.APPLICATION_JSON, // object to be given in JSON
		MediaType.APPLICATION_XML // or object to be given in XML
	})
	public Race findRace(@PathParam("raceID") int id) {
		try{
		return racRep.findById(id).get();
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	@DELETE
	@Path("/delete/{raceID}")
	public void deleteRace(@PathParam("raceID") int id) {
		racRep.deleteById(id);
	}

	@Path("/fetch_country")
	@GET // http method used to call the api
	@Produces({ // declare all possible content types of return value
		MediaType.APPLICATION_JSON, // object to be given in JSON
	})
	public List<Race> fetchByCountry(@QueryParam("country") String country){
		return racRep.findByCountry(country);
	}
}
