package com.formulaone.formulaone.apis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.formulaone.formulaone.entities.Race;
import com.formulaone.formulaone.repositories.RaceRepository;

@Component
@Scope("singleton")
public class RaceService {

	@Autowired
	RaceRepository racRep;
	
	RaceService(){
		System.out.println("driver service added");
	}
	
	public Race addOrUpdateDriver(Race d) {
		d = racRep.save(d);
		System.out.println("race "+d.getRaceID()+" registered");
		return d;
	}

	public Race findRace(int id) {
		try{
		return racRep.findById(id).get();
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	public void deleteRace(int id) {
		racRep.deleteById(id);
	}

	public List<Race> fetchByCountry(String country){
		return racRep.findByCountry(country);
	}
}
