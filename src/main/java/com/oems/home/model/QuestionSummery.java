package com.oems.home.model;

public class QuestionSummery {

	private String courseCode;
	private String teacherId;
	private Double percentageValue;
	private String startingDateTime;
    private double total;
    
	public QuestionSummery() {
		super();
	}
	public QuestionSummery(String courseCode, String teacherId, Double percentageValue, String startingDateTime,
			double total) {
		super();
		this.courseCode = courseCode;
		this.teacherId = teacherId;
		this.percentageValue = percentageValue;
		this.startingDateTime = startingDateTime;
		this.total = total;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public Double getPercentageValue() {
		return percentageValue;
	}
	public void setPercentageValue(Double percentageValue) {
		this.percentageValue = percentageValue;
	}
	public String getStartingDateTime() {
		return startingDateTime;
	}
	public void setStartingDateTime(String startingDateTime) {
		this.startingDateTime = startingDateTime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
    
    
}
