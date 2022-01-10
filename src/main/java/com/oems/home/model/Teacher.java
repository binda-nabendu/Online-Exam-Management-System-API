package com.oems.home.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;

@Component
public class Teacher extends BaseUser{
	private String eduQualification;
	private String expertise;

	public Teacher() {
		super();
	}

	public Teacher(String nid, String userName, String fatherName, String motherName, int gender, String contactNo, 
			String email, String dob, String address, String password, String role , String eduQualification, String expertise) {
		super(nid, userName, fatherName, motherName, gender, contactNo, email, dob, address, password, role);
		this.eduQualification = eduQualification;
		this.expertise = expertise;
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
