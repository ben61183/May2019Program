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
import com.mastek.training.hrapp.repositories.DepartmentRepository;

@Component
@Scope("singleton")
@Path("/departments/") // map the URL pattern with the class as service
public class DepartmentService {
	@Autowired
	private DepartmentRepository depRep;
	
	public DepartmentService() {
	System.out.println("dept created");	
	}
	
	@GET
	@Path("/list")
	@Produces({MediaType.APPLICATION_JSON})
	public Iterable<Department> listAllDepartments(){
		// fetch all departments from the table
		return depRep.findAll();
	}
	
	@POST // http method to ssend the form data
	@Path("/register") // url pattern
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) // form daya
	@Produces(MediaType.APPLICATION_JSON) // json data
	public Department registerOrUpdateDept(@BeanParam Department dep) {
		dep = depRep.save(dep);
		System.out.println("department registered"+dep);
		return dep;
	}
	
	@Path("/find/{deptno}")
	@GET // http method used to call the api
	@Produces({ // declare all possible content types of return value
		MediaType.APPLICATION_JSON, // object to be given in JSON
		MediaType.APPLICATION_XML // or object to be given in XML
	})
	public Department findById(@PathParam("deptno") int deptno) {
		try {
			return depRep.findById(deptno).get();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Path("/fetch_loc")
	@GET // http method used to call the api
	@Produces({ // declare all possible content types of return value
		MediaType.APPLICATION_JSON, // object to be given in JSON
		MediaType.APPLICATION_XML // or object to be given in XML
	})
	public List<Department> fetchDeptsByLocation(@QueryParam("loc") String location){
		return depRep.findByLocation(location);
	}

	@DELETE 
	@Path("/delete/{deptno}") // url pattern
	public void deleteByDeptno(@PathParam("deptno") int deptno) {
		depRep.deleteById(deptno);
	}
}