package com.oems.home.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    @GetMapping("student-board/{id}")
    public String studentBoardManager(@PathVariable("id") String id){

        return "Student board manager";
    }

    @GetMapping("/departmental-course/my-courses/{studentId}")
    public String studentCurrentSubject(@PathVariable("studentId") String id){
        return "All Courses";
    }
    @GetMapping("/departmental-course/{deptId}")
    public String allCourseOfThatDepartment(@PathVariable("deptId") String deptId){
        return "All course of that department";
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
