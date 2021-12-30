package com.oems.home.controller;

import com.oems.home.model.CourseDetails;
import com.oems.home.model.Student;
import com.oems.home.model.Teacher;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

@RestController
public class AdminController {

    @GetMapping
    @RequestMapping("/admin-board/{user-id}")
    public String adminBoardManager(@PathVariable("user-id") String user){
        return "This return admin Dashboard";
    }

    @PostMapping
    @RequestMapping("/teachers/add-teacher/{teacher-details}")
    public String addTeacher(@PathVariable("teacher-details") Teacher teacher){
        return "This return admin Dashboard";
    }

    //-----------For teacher approve-------------
    @GetMapping
    @RequestMapping("/teachers/approve-teachers/list")
    public String approveTeacher(){
        return "All teacher list who are request for approve";
    }

    @PostMapping
    @RequestMapping("/teachers/approve-teachers/approve/{teacher-id}")
    public String approveTeacher(@PathVariable("teacher-id") String tId){
        return "Teacher who will be approved by admin now";
    }

    @PostMapping
    @RequestMapping("/teachers/approve-teachers/delete/{teacher-id}")
    public String deleteTeacher(@PathVariable("teacher-id") String tId){
        return "Teacher who will be deleted by admin now";
    }

    //-----------For student approve-------------
    @PostMapping
    @RequestMapping("/student/add-student/{student-details}")
    public String addTeacher(@PathVariable("student-details") Student student){
        return "Student added successful";
    }


    @GetMapping
    @RequestMapping("/student/approve-student/list")
    public String approveStudent(){
        return "All student list who are request for approve";
    }

    @PostMapping
    @RequestMapping("/student/approve-student/approve/{student-id}")
    public String approveStudent(@PathVariable("student-id") String sId){
        return "Student who will be approved by admin now";
    }

    @PostMapping
    @RequestMapping("/student/approve-student/delete/{student-id}")
    public String deleteStudent(@PathVariable("student-id") String sId){
        return "Student who will not be approve instead of delete them";
    }

    @PostMapping
    @RequestMapping("/courses/add-courses/{course-details}")
    public String addCourse(@PathVariable("course-details") CourseDetails details){
        return "Course Added Successful";
    }


}
