package com.mastek.training.hrapp;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mastek.training.hrapp.apis.ProjectService;
import com.mastek.training.hrapp.entities.Project;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HrProjectTest {

	@Autowired
	ProjectService projServ;
	
	@Autowired
	Project proj;
	
	@Test
	public void addNewprojUsingServ() {
		proj.setProjectID(0);
		proj.setName("graduate project");
		proj.setCustomerName("morrisons");
		projServ.registerOrUpdateproj(proj);
		assertNotNull(proj);
	}
	
	@Test
	public void findprojByNoUsingServ() {
		int projno = 12;
		System.out.println("found: "+projServ.findById(projno));
		assertNotNull(projServ.findById(projno));
	}
	
	@Ignore
	@Test
	public void deleteUsingServ() {
		int projno = 23;
		projServ.deleteByprojno(projno);
		assertNull(projServ.findById(projno));
	}
	
	@Test
	public void fetchByLocation() {
		List<Project> projs = projServ.fetchProjsByCustomerName("morrisons");
		for(Project d:projs) {
			System.out.println(d);
		}
		assertTrue(projs.size()>0);
	}
}
