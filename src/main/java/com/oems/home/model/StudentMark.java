package com.oems.home.model;

public class StudentMark {
    private String stdID;
    private String deptId;
    private String courseCode;
    private int examId;
    private int gotTotalMark;
    private boolean review;

    public String getStdID() {
        return stdID;
    }

    public void setStdID(String stdID) {
        this.stdID = stdID;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

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

    public int getGotTotalMark() {
        return gotTotalMark;
    }

    public void setGotTotalMark(int gotTotalMark) {
        this.gotTotalMark = gotTotalMark;
    }

    public boolean isReview() {
        return review;
    }

    public void setReview(boolean review) {
        this.review = review;
    }
}
