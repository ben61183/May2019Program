package com.mastek.training.hrapp.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mastek.training.hrapp.entities.Project;

public interface ProjectRepository extends CrudRepository<Project,Integer>{
	public List<Project> findBycustomerName(
		@Param("cust") String cust);

}
