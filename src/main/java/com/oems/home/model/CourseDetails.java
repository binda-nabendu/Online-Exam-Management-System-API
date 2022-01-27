package com.oems.home.model;

import org.springframework.stereotype.Component;

@Component
public class CourseDetails {
    private String courseCode;
    private String deptId;
    private String courseName;
    private String teacherId="Not assigned";
    private int courseSessions=-33;

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

    public int getCourseSessions() {
        return courseSessions;
    }

    public void setCourseSessions(int courseCurrSessions) {
        this.courseSessions = courseCurrSessions;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
}
