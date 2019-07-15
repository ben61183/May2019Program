package com.mastek.training.hrapp.entities;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class EmployeeLifecycleListener {
	// lifecycle methods:
	// @<Event>
	// public void <name>(Entity e)
	
	@PrePersist
	public void beforeInsert(Employee e) {
		System.out.println("before insert: "+e);
	}
	
	@PostPersist
	public void afterInsert(Employee e) {
		System.out.println("after insert: "+e);
	}
	

	@PreUpdate
	public void beforeUpdate(Employee e) {
		System.out.println("before update: "+e);
	}
	
	@PostUpdate
	public void afterUpdate(Employee e) {
		System.out.println("after update: "+e);
	}
	

	@PreRemove
	public void beforeDelete(Employee e) {
		System.out.println("before remove: "+e);
	}
	
	@PostLoad
	public void afterSelect(Employee e) {
		System.out.println("after select: "+e);
	}
}
