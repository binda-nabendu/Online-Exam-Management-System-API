package com.oems.home.model;

import org.springframework.stereotype.Component;

@Component
public class BaseUser {
private String nid;
private String userName;
private String fatherName;
private String motherName;
private int gender;//1 for male, 2 for female, 3 for others, 4 for not disclose
private String contactNo;
private String email;
private String dob;
private String address;
private String password;
//private Boolean adminApproval;
private String role;

	public BaseUser(){
	}

	public BaseUser(String nid, String userName, String fatherName, String motherName, int gender, String contactNo, String email, String dob, String address, String password, String role) {
		this.nid = nid;
		this.userName = userName;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.gender = gender;
		this.contactNo = contactNo;
		this.email = email;
		this.dob = dob;
		this.address = address;
		this.password = password;
		this.role = role;
	}

	public String getNid() {
	return nid;
}
public void setNid(String nid) {
	this.nid = nid;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
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
//public Boolean getAdminApproval() {
//	return adminApproval;
//}
//public void setAdminApproval(Boolean adminApproval) {
//	this.adminApproval = adminApproval;
//}

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
