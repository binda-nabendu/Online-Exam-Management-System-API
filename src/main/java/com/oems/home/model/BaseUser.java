package com.oems.home.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "base_user")
public class BaseUser {
@Id
@OneToOne(mappedBy = "student")
private Student nid;
private String name;
private String fatherName;
private String motherName;
private int gender;//1 for male, 2 for female
private String contactNo;
private String email;
private Date dob;
private String address;
private Boolean adminApproval;
private int role;//1 for admin, 2 for teacher, 3 for student
private String password;

	public Student getNid() {
		return nid;
	}

	public void setNid(Student nid) {
		this.nid = nid;
	}

	public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getFatherName() {
	return fatherName;
}
public void setFatherName(String fatherName) {
	this.fatherName = fatherName;
}
public String getMotherName() {
	return motherName;
}
public void setMotherName(String motherName) {
	this.motherName = motherName;
}
public int getGender() {
	return gender;
}
public void setGender(int gender) {
	this.gender = gender;
}
public String getContactNo() {
	return contactNo;
}
public void setContactNo(String contactNo) {
	this.contactNo = contactNo;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public Date getDob() {
	return dob;
}
public void setDob(Date dob) {
	this.dob = dob;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public Boolean getAdminApproval() {
	return adminApproval;
}
public void setAdminApproval(Boolean adminApproval) {
	this.adminApproval = adminApproval;
}
public String getRole() {
	return Integer.toString(role);
}
public void setRole(int role) {

	this.role = role;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

}
