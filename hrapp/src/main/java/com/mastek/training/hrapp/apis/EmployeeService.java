package com.mastek.training.hrapp.apis;

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

import com.mastek.training.hrapp.entities.Department;
import com.mastek.training.hrapp.entities.Employee;
import com.mastek.training.hrapp.entities.Project;
import com.mastek.training.hrapp.repositories.EmployeeRepository;

// @Component: indicate to spring to create an object of this class as component
// @Scope: singleton [default] - one object for app, prototype - one object for each use

@Component
@Scope("singleton")
@Path("/employees/") // map the URL pattern with the class as service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepository;
	
	@Autowired
	private DepartmentService depServ;

	@Autowired
	private ProjectService proServ;
	
	public EmployeeService() {
		System.out.println("employee service created");
	}
	
	@POST // http method to ssend the form data
	@Path("/register") // url pattern
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) // form data
	@Produces(MediaType.APPLICATION_JSON) // json data
	@Transactional
	public Employee registerOrUpdateEmployee(
			@BeanParam Employee emp) { // input bean using form
		Employee currentEmp = findByEmpno(emp.getEmpno());
		if(currentEmp!=null) { // update existing details with form values
			currentEmp.setName(emp.getName());
			currentEmp.setSalary(emp.getSalary());
			emp = empRepository.save(currentEmp);
		}else { // register new Employee
			emp = empRepository.save(emp); // save will save or update (if existing key)
		}	
		System.out.println("employee registered"+emp);
		return emp;
	}

	// use find method using PathPara 
	// eg /find/1122: 1122->empno to pass as param to method
	@Path("/find/{empno}")
	@GET // http method used to call the api
	@Produces({ // declare all possible content types of return value
		MediaType.APPLICATION_JSON, // object to be given in JSON
		MediaType.APPLICATION_XML // or object to be given in XML
	})
	@Transactional // to help fetch dependent data
	public Employee findByEmpno(@PathParam("empno") int empno) { // use the PathParam as the argument for the method
		// fetches the employee details from the db by empno
		try {
			Employee emp = empRepository.findById(empno).get();
			System.out.println(emp.getAssignments().size()+" ass's passed");
			return emp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	// delete http method
	@DELETE 
	@Path("/delete/{empno}") // url pattern
	public void deleteByEmpno(@PathParam("empno") int empno) {
		empRepository.deleteById(empno);
	}
	

	@Path("/fetch_salary")
	@GET // http method used to call the api
	@Produces({ // declare all possible content types of return value
		MediaType.APPLICATION_JSON, // object to be given in JSON
	})
	public List<Employee> fetchEmployeesBySalaryRange(@QueryParam("min") double min, @QueryParam("max") double max){
		return empRepository.findBySalary(min, max);
	}

	// spring ensures that database session is open until all operations in method across repositories are completed
	// it is used to fetch all collections which are initialized using lazy initialization
	@Transactional
	@POST
	@Path("/assign/department")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Employee assignDepartment(@FormParam("empno") int empno, @FormParam("deptno") int deptno) {
		try {
			// fetch entities to be associated
			Employee emp = findByEmpno(empno);
			Department dep = depServ.findById(deptno);
			// manage the association
			dep.getMembers().add(emp); // one assigned with many
			emp.setCurrentDepartment(dep); // many assigned with one
			emp = registerOrUpdateEmployee(emp);
			return emp;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	@POST
	@Path("/assign/project")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
		public Set<Project> assignProject(@FormParam("empno") int empno, @FormParam("projno") int projno) {
		try {
			// fetch entities to be associated
			Employee emp = findByEmpno(empno);
			Project pro = proServ.findById(projno);
			// associate employee with project
			emp.getAssignments().add(pro); 
			emp = registerOrUpdateEmployee(emp);
			return emp.getAssignments();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	@POST
	@Path("/delete/project")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
		public Set<Project> deleteProject(@FormParam("empno") int empno, @FormParam("projno") int projno) {
		try {
			// fetch entities to be associated
			Employee emp = findByEmpno(empno);
			Project pro = proServ.findById(projno);
			// associate employee with project
			emp.getAssignments().remove(pro); 
			emp = registerOrUpdateEmployee(emp);
			return emp.getAssignments();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
