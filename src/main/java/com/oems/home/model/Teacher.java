package com.oems.home.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Teacher {
	@Id
	private String id;
	private String educationalQualification;
	private String expert;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEducationalQualification() {
		return educationalQualification;
	}
	public void setEducationalQualification(String educationalQualification) {
		this.educationalQualification = educationalQualification;
	}
	public String getExpert() {
		return expert;
	}
	public void setExpert(String expert) {
		this.expert = expert;
	}
}
