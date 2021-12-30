package com.oems.home.model;

import com.fasterxml.jackson.databind.ser.Serializers;

import javax.persistence.*;

public class Student extends BaseUser{
	private String deptId;
	private int batch;
	private int semester;

}
