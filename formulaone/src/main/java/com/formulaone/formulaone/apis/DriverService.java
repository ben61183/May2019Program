package com.formulaone.formulaone.apis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.formulaone.formulaone.entities.Driver;
import com.formulaone.formulaone.repositories.DriverRepository;

@Component
@Scope("singleton")
public class DriverService {

	@Autowired
	DriverRepository driRep;
	
	DriverService(){
		System.out.println("driver service added");
	}
	
	public Driver addOrUpdateDriver(Driver d) {
		d = driRep.save(d);
		System.out.println("driver "+d.getDriverID()+" registered");
		return d;
	}

	public Driver findDriver(int id) {
		try{
		return driRep.findById(id).get();
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}

	public void deleteDriver(int id) {
		driRep.deleteById(id);
	}
	
	public List<Driver> fetchByWins(double min, double max){
		return driRep.findBySalary(min, max);
	}

}
