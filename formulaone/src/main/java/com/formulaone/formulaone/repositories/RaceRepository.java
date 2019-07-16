package com.formulaone.formulaone.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.formulaone.formulaone.entities.Race;

public interface RaceRepository  extends CrudRepository<Race, Integer>{
	public List<Race> findByCountry(
			@Param("country") String country);
}
