package com.formulaone.formulaone.apis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.formulaone.formulaone.entities.Driver;
import com.formulaone.formulaone.entities.Race;
import com.formulaone.formulaone.entities.Team;
import com.formulaone.formulaone.repositories.TeamRepository;

@Component
@Scope("singleton")
public class TeamService {

	@Autowired
	TeamRepository teaRep;
	
	TeamService(){
		System.out.println("team service added");
	}
	
	public Team addOrUpdateTeam(Team d) {
		d = teaRep.save(d);
		System.out.println("team "+d.getTeamID()+" registered");
		return d;
	}
	
	public Team findTeam(int id) {
		try{
		return teaRep.findById(id).get();
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public void deleteTeam(int id) {
		teaRep.deleteById(id);
	}
	
	public List<Team> fetchByEngine(String engine){
		return teaRep.findByEngine(engine);
	}
}
