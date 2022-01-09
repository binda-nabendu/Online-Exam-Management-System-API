package com.oems.home.model;

public class RequestCourse {
    private String stdId;
    private String deptId;
    private int batch;
    private int semester;
    private String courseCode;
    private String courseName;
    private String teacherId;
    private String courseCurrSession;

    public String getStdId() {
        return stdId;
    }

    public void setStdId(String stdId) {
        this.stdId = stdId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getCourseCurrSession() {
        return courseCurrSession;
    }

    public void setCourseCurrSession(String courseCurrSession) {
        this.courseCurrSession = courseCurrSession;
    }
}
