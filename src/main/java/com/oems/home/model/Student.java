package com.oems.home.model;

import com.fasterxml.jackson.databind.ser.Serializers;

import javax.persistence.*;

public class Student extends BaseUser{
	private String deptId;
	private int batch;
	private int semester;

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
