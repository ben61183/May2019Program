package com.formulaone.formulaone.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.formulaone.formulaone.entities.Driver;

public interface DriverRepository extends CrudRepository<Driver, Integer>{
	public List<Driver> findByWins(
			@Param("min") int min
			,@Param("max") int max);
}
