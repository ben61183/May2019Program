package com.mastek.training.hrapp.apis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mastek.training.hrapp.entities.Project;
import com.mastek.training.hrapp.repositories.ProjectRepository;

@Component
@Scope("singleton")
public class ProjectService {
	@Autowired
	private ProjectRepository projRep;
	
	public ProjectService() {
	System.out.println("proj created");	
	}
	
	public Project registerOrUpdateproj(Project proj) {
		proj = projRep.save(proj);
		System.out.println("proj registered"+proj);
		return proj;
	}
	
	public Project findById(int projno) {
		try {
			return projRep.findById(projno).get();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Project> fetchProjsByCustomerName(String customerName){
		return projRep.findBycustomerName(customerName);
	}

	public void deleteByprojno(int projno) {
		projRep.deleteById(projno);
	}
}