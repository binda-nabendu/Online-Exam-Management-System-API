package com.oems.home.model;

import javax.persistence.Entity;
import javax.persistence.Id;


public class Teacher extends BaseUser{
	private String eduQualification;
	private String expertise;

	public Teacher(String nid, String name, String fatherName, String motherName, int gender, String contactNo, String email, String dob, String address, String password, String role) {
		super(nid, name, fatherName, motherName, gender, contactNo, email, dob, address, password, role);
		
	}


	public String getEduQualification() {
		return eduQualification;
	}

	public void setEduQualification(String eduQualification) {
		this.eduQualification = eduQualification;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
}
