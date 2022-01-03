package com.oems.home.model;

public class CourseDetails {
    private String courseCode;
    private String courseName;
    private String courseSessions;

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

    public String getCourseSessions() {
        return courseSessions;
    }

    public void setCourseSessions(String courseCurrSessions) {
        this.courseSessions = courseCurrSessions;
    }
}
