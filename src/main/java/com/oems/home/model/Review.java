package com.oems.home.model;

import org.springframework.stereotype.Component;

@Component
public class Review {
    private String courseCode;
    private int examId;
    private String stdId;
    private String stdName;
    private double gotTotalMarks;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getStdId() {
        return stdId;
    }

    public void setStdId(String stdId) {
        this.stdId = stdId;
    }

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    public double getGotTotalMarks() {
        return gotTotalMarks;
    }

    public void setGotTotalMarks(double gotTotalMarks) {
        this.gotTotalMarks = gotTotalMarks;
    }
}
