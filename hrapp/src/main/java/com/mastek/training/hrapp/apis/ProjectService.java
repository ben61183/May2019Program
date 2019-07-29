package com.mastek.training.hrapp.apis;

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

import com.mastek.training.hrapp.entities.Department;
import com.mastek.training.hrapp.entities.Project;
import com.mastek.training.hrapp.repositories.ProjectRepository;

@Component
@Scope("singleton")
@Path("/projects/") // map the URL pattern with the class as service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projRep;
	
	public ProjectService() {
	System.out.println("proj created");	
	}
	
	@GET
	@Path("/list")
	@Produces({MediaType.APPLICATION_JSON})
	public Iterable<Project> listAllProjects(){
		return projRep.findAll();
	}
	
	@POST // http method to send the form data
	@Path("/register") // url pattern
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) // form daya
	@Produces(MediaType.APPLICATION_JSON) // json data
	public Project registerOrUpdateproj(
			@BeanParam Project proj) {
		proj = projRep.save(proj);
		System.out.println("proj registered"+proj);
		return proj;
	}
	
	@Path("/find/{projno}")
	@GET // http method used to call the api
	@Produces({ // declare all possible content types of return value
		MediaType.APPLICATION_JSON, // object to be given in JSON
		MediaType.APPLICATION_XML // or object to be given in XML
	})
	public Project findById(@PathParam("projno") int projno) {
		try {
			return projRep.findById(projno).get();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Path("/fetch_cust")
	@GET // http method used to call the api
	@Produces({ // declare all possible content types of return value
		MediaType.APPLICATION_JSON, // object to be given in JSON
		MediaType.APPLICATION_XML // or object to be given in XML
	})
	public List<Project> fetchProjsByCustomerName(@QueryParam("customerName") String customerName){
		return projRep.findBycustomerName(customerName);
	}

	@DELETE 
	@Path("/delete/{projno}") // url pattern
	public void deleteByprojno(@PathParam("projno") int projno) {
		projRep.deleteById(projno);
	}
}