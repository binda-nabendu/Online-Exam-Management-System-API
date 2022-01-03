package com.oems.home.controller;

import com.oems.home.dao.StudentJdbcDao;
import com.oems.home.model.CourseDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentJdbcDao studentDao;

    @GetMapping("student-board/{id}")
    public String studentBoardManager(@PathVariable("id") String id){

        return "Student board manager";
    }

    @GetMapping("/my-courses/{studentId}")
    public List<CourseDetails> studentCurrentSubject(@PathVariable("studentId") String stdId){
        return studentDao.allRunningCourseDetails(stdId);
    }
    @GetMapping("/departmental-course/{deptId}")
    public List<CourseDetails> allCourseOfThatDepartment(@PathVariable("deptId") String deptId){
        return studentDao.departmentalCourse(deptId);
    }
    @GetMapping("/departmental-course/completed/{studentId}")
    public String allCompletedCourseOfThatStudent(@PathVariable("studentId") String id){
        return "All completed course of that Student";
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
