package com.mastek.training.hrapp.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mastek.training.hrapp.entities.Department;

public interface DepartmentRepository extends CrudRepository<Department,Integer>{
	public List<Department> findByLocation(
		@Param("loc") String loc);
}
