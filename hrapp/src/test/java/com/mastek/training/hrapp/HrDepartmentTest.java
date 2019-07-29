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

import com.mastek.training.hrapp.apis.DepartmentService;
import com.mastek.training.hrapp.entities.Department;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HrDepartmentTest {

	@Autowired
	DepartmentService depServ;
	
	
	
	@Test
	public void addNewDeptUsingServ() {
		Department dep = new Department();
		dep.setDeptno(0);
		dep.setLocation("china");
		dep.setName("china hr");
		depServ.registerOrUpdateDept(dep);
		assertNotNull(dep);
	}
	
	@Test
	public void findDeptByNoUsingServ() {
		int deptno = 15;
		System.out.println("found: "+depServ.findById(deptno));
		assertNotNull(depServ.findById(deptno));
	}
	
	@Ignore
	@Test
	public void deleteUsingServ() {
		int deptno = 24;
		depServ.deleteByDeptno(deptno);
		assertNull(depServ.findById(deptno));
	}
	
	@Test
	public void fetchByLocation() {
		List<Department> depts = depServ.fetchDeptsByLocation("UK");
		for(Department d:depts) {
			System.out.println(d);
		}
		assertTrue(depts.size()>0);
	}
}
