package com.oems.home.model;

public class QuestionSummery {

    private int examId;
	private String courseCode;
	private String teacherId;
	private Double percentageValue;
	private String startingDateTime;
	private String endingDateTime;
    private double total;
	private double obtainMark;

	public int getExamId() {
		return examId;
	}
	public void setExamId(int examId) {
		this.examId = examId;
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

	public String getEndingDateTime() {
		return endingDateTime;
	}

	public void setEndingDateTime(String endingDateTime) {
		this.endingDateTime = endingDateTime;
	}

	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}

	public double getObtainMark() {
		return obtainMark;
	}

	public void setObtainMark(double obtainMark) {
		this.obtainMark = obtainMark;
	}
}
