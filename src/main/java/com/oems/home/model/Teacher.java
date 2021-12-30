package com.oems.home.model;

import javax.persistence.Entity;
import javax.persistence.Id;


public class Teacher extends BaseUser{
	private String educationalQualification;

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	private String expertise;

	public String getEducationalQualification() {
		return educationalQualification;
	}

	public void setEducationalQualification(String educationalQualification) {
		this.educationalQualification = educationalQualification;
	}
}
