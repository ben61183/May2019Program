package com.formulaone.formulaone.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.formulaone.formulaone.entities.Driver;

public interface DriverRepository extends CrudRepository<Driver, Integer>{
	public List<Driver> findBySalary(
			@Param("min") Double min
			,@Param("max") Double max);
}
