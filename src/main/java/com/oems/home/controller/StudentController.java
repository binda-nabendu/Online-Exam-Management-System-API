package com.oems.home.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    @GetMapping
    @RequestMapping("student-board/{id}")
    public String studentBoardManager(@PathVariable("id") String id){

        return "Student board manager";
    }

    @GetMapping
    @RequestMapping("/departmental-course/my-courses/{studentId}")
    public String studentCurrentSubject(@PathVariable("studentId") String id){
        return "All Courses";
    }
    @GetMapping
    @RequestMapping("/departmental-course/{deptId}")
    public String allCourseOfThatDepartment(@PathVariable("deptId") String deptId){
        return "All course of that department";
    }
    @GetMapping
    @RequestMapping("/departmental-course/completed/{studentId}")
    public String allCompletedCourseOfThatStudent(@PathVariable("studentId") String id){
        return "All completed course of that Student";
    }
    @GetMapping
    @RequestMapping("/exams/upcoming/{studentId}")
    public String upComingExamStudent(@PathVariable("studentId") String id){
        return "List of upcoming exam";
    }
    @GetMapping
    @RequestMapping("/exams/previous/{studentId}")
    public String previusExamStudent(@PathVariable("studentId") String id){
        return "List of previous exam up to 2 semester";
    }
    @PostMapping
    @RequestMapping("/exams/review/{studentId}/{examId}")
    public String RequestReview(@PathVariable("studentId")String id ,@PathVariable("examId") int examId){
        return "Review Request Sand..";
    }
}
