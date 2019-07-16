package com.formulaone.formulaone.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.formulaone.formulaone.entities.Team;

public interface TeamRepository  extends CrudRepository<Team, Integer>{
	public List<Team> findByEngine(
			@Param("engine") String engine);
}
