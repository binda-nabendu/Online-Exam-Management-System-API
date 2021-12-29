package com.oems.home.model;

import com.fasterxml.jackson.databind.ser.Serializers;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {
	@Id
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id", referencedColumnName = "nid")
	private BaseUser id;
	private String deptId;
	private int batch;
	private int semester;

	public BaseUser getId() {
		return id;
	}

	public void setId(BaseUser id) {
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
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	
	
}
