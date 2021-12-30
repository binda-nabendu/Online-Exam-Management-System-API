package com.oems.home.controller;

import com.oems.home.model.CourseDetails;
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
    @RequestMapping("/teachers/add-teachers/{teacher-details}")
    public String addTeacher(@PathVariable("teacher-details") Teacher teacher){
        return "This return admin Dashboard";
    }

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
    @RequestMapping("/teachers/approve-teachers/delate/{teacher-id}")
    public String deleteTeacher(@PathVariable("teacher-id") String tId){
        return "Teacher who will be deleted by admin now";
    }

    @PostMapping
    @RequestMapping("/teachers/add-courses/{course-details}")
    public String deleteTeacher(@PathVariable("course-details") CourseDetails details){
        return "Course Added Successful";
    }

    @PostMapping
    @RequestMapping("/teachers/add-courses/{course-details}")
    public String addCourse(@PathVariable("course-details") CourseDetails details){
        return "Course Added Successful";
    }


}
