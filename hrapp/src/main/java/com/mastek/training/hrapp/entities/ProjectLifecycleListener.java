package com.mastek.training.hrapp.entities;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class ProjectLifecycleListener {
	// lifecycle methods:
	// @<Event>
	// public void <name>(Entity e)
	
	@PrePersist
	public void beforeInsert(Project e) {
		System.out.println("before insert: "+e);
	}
	
	@PostPersist
	public void afterInsert(Project e) {
		System.out.println("after insert: "+e);
	}
	

	@PreUpdate
	public void beforeUpdate(Project e) {
		System.out.println("before update: "+e);
	}
	
	@PostUpdate
	public void afterUpdate(Project e) {
		System.out.println("after update: "+e);
	}
	

	@PreRemove
	public void beforeDelete(Project e) {
		System.out.println("before remove: "+e);
	}
	
	@PostLoad
	public void afterSelect(Project e) {
		System.out.println("after select: "+e);
	}
}
