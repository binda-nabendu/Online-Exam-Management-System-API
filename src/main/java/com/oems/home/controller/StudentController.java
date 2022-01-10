package com.oems.home.controller;

import com.oems.home.dao.AdminJdbcDao;
import com.oems.home.dao.StudentJdbcDao;
import com.oems.home.model.CourseDetails;
import com.oems.home.model.Dashboard;
import com.oems.home.model.QuestionPaper;

import com.oems.home.model.QuestionSummery;
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
    public List<QuestionSummery> upComingExamStudent(@PathVariable("studentId") String stdId){
    	return studentDao.upcomingExamForStudent(stdId);
    }
    @GetMapping("/exams/previous/{studentId}")
    public List<QuestionSummery> previousExamStudent(@PathVariable("studentId") String stdId){
    	return studentDao.prevExamForStudent(stdId);
    }
    @PostMapping("/exams/send-review/{studentId}/{examId}")
    public String RequestReview(@PathVariable("studentId")String id ,@PathVariable("examId") int examId){
        return "Review Request Sand..";
    }
    
    //@GetMapping("give-post-exam/{studentId}")
   // public String giveOrPostExam(QuestionPaper qPaper) {
    //	return "";
   // }
   // @PostMapping()
   // public String giveOrPostExam(QuestionPaper qPaper) {
    //	return "";
    //}
}
