package com.mastek.training.hrapp.apis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mastek.training.hrapp.entities.Department;
import com.mastek.training.hrapp.repositories.DepartmentRepository;

@Component
@Scope("singleton")
public class DepartmentService {
	@Autowired
	private DepartmentRepository depRep;
	
	public DepartmentService() {
	System.out.println("dept created");	
	}
	
	public Department registerOrUpdateDept(Department dep) {
		dep = depRep.save(dep);
		System.out.println("dep registered"+dep);
		return dep;
	}
	
	public Department findById(int deptno) {
		try {
			return depRep.findById(deptno).get();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Department> fetchDeptsByLocation(String location){
		return depRep.findByLocation(location);
	}

	public void deleteByDeptno(int deptno) {
		depRep.deleteById(deptno);
	}
}