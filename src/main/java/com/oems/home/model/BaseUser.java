package com.oems.home.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BaseUser {
@Id
private String NID;
private String name;
private String fatherName;
private String motherName;
private String gender;
private String contactNo;
private String email;
private String dob;
private String address;
private Boolean adminApproval;
private String role;
private String password;

public String getNID() {
	return NID;
}
public void setNID(String nID) {
	NID = nID;
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
public String getGender() {
	return gender;
}
public void setGender(String gender) {
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
public String getDob() {
	return dob;
}
public void setDob(String dob) {
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
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

}
