package com.oems.home.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Student {
	@Id
	private String id;
	private String deptId;
	private int batch;
	private int semister;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public int getBatch() {
		return batch;
	}
	public void setBatch(int batch) {
		this.batch = batch;
	}
	public int getSemister() {
		return semister;
	}
	public void setSemister(int semister) {
		this.semister = semister;
	}
	
	
}
