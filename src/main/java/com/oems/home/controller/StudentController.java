package com.oems.home.controller;

import com.oems.home.dao.AdminJdbcDao;
import com.oems.home.dao.StudentJdbcDao;
import com.oems.home.model.CourseDetails;
import com.oems.home.model.Dashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentJdbcDao studentDao;
    @Autowired
    AdminJdbcDao adminJdbcDao;

    @GetMapping("student-board/{id}")
    public Dashboard studentDashBoard(@PathVariable("id") String id){

        return studentDao.studentBoardManager(id);
    }
    @PostMapping("/request-for-courses")
    public void reqCourseList(List<CourseDetails> courses, String stdId){
        adminJdbcDao.requestedCourseAdd(courses, stdId);
    }
    @GetMapping("/my-courses/{studentId}")
    public List<CourseDetails> studentCurrentSubject(@PathVariable("studentId") String stdId){
        return studentDao.allRunningCourseDetails(stdId);
    }
    @GetMapping("/departmental-course/{deptId}")
    public List<CourseDetails> allCourseOfThatDepartment(@PathVariable("deptId") String deptId){
        return studentDao.departmentalCourseSet(deptId);
    }
    @GetMapping("/departmental-course/completed/{studentId}")
    public List<CourseDetails> allCompletedCourseOfThatStudent(@PathVariable("studentId") String stdId){
        return studentDao.completedCoursesByStudent(stdId);
    }
    @GetMapping("/exams/upcoming/{studentId}")
    public String upComingExamStudent(@PathVariable("studentId") String id){
        return "List of upcoming exam";
    }
    @GetMapping("/exams/previous/{studentId}")
    public String previusExamStudent(@PathVariable("studentId") String id){
        return "List of previous exam up to 2 semester";
    }
    @PostMapping("/exams/send-review/{studentId}/{examId}")
    public String RequestReview(@PathVariable("studentId")String id ,@PathVariable("examId") int examId){
        return "Review Request Sand..";
    }
}
