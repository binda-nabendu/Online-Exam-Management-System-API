package com.oems.home.model;

import com.fasterxml.jackson.databind.ser.Serializers;

import javax.persistence.*;

public class Student extends BaseUser{
	private String deptId;
	private int semester;

	public Student(String nid, String userName, String fatherName, String motherName, int gender, String contactNo,
				   String email, String dob, String address, String password, String role, String deptId, int semester) {
		super(nid, userName, fatherName, motherName, gender, contactNo, email, dob, address, password, role);
		this.deptId = deptId;
		this.semester = semester;
	}


	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}
}
