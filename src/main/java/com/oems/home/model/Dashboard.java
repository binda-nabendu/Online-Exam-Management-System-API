package com.oems.home.model;

public class Dashboard {
    private int totalTeachers;
    private int totalStudents;
    private int totalDepartments;
    private int totalExamTakenByThatTeacher;
    private int totalUpComingExam;

    public int getTotalTeachers() {
        return totalTeachers;
    }

    public void setTotalTeachers(int totalTeachers) {
        this.totalTeachers = totalTeachers;
    }

    public int getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }

    public int getTotalDepartments() {
        return totalDepartments;
    }

    public void setTotalDepartments(int totalDepartments) {
        this.totalDepartments = totalDepartments;
    }

    public int getTotalExamTakenByThatTeacher() {
        return totalExamTakenByThatTeacher;
    }

    public void setTotalExamTakenByThatTeacher(int totalExamTakenByThatTeacher) {
        this.totalExamTakenByThatTeacher = totalExamTakenByThatTeacher;
    }

    public int getTotalUpComingExam() {
        return totalUpComingExam;
    }

    public void setTotalUpComingExam(int totalUpComingExam) {
        this.totalUpComingExam = totalUpComingExam;
    }
}
