package com.oems.home.controller;

import com.oems.home.dao.StudentJdbcDao;
import com.oems.home.model.CourseDetails;
import com.oems.home.model.Student;
import com.oems.home.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

@RestController
public class AdminController {

    @Autowired
    StudentJdbcDao studentDao;

    @GetMapping("/admin-board/{user-id}")
    public String adminBoardManager(@PathVariable("user-id") String user){
        return "This return admin Dashboard";
    }

    @PostMapping("/teachers/add-teacher/{teacher-details}")
    public String addTeacher(@PathVariable("teacher-details") Teacher teacher){
        return "This return admin Dashboard";
    }

    //-----------For teacher approve-------------
    @GetMapping("/teachers/approve-teachers/list")
    public String approveTeacher(){
        return "All teacher list who are request for approve";
    }

    @PostMapping("/teachers/approve-teachers/approve/{teacher-id}")
    public String approveTeacher(@PathVariable("teacher-id") String tId){
        return "Teacher who will be approved by admin now";
    }

    @PostMapping("/teachers/approve-teachers/delete/{teacher-id}")
    public String deleteTeacher(@PathVariable("teacher-id") String tId){
        return "Teacher who will be deleted by admin now";
    }

    //-----------For student approve-------------
    @PostMapping("/add-student")
    public Student addStudent(Student student){
//        String encodedPassword = new BCryptPasswordEncoder().encode(student.getPassword());
//        student.setPassword(encodedPassword);
        studentDao.create(student);


        return student;
    }


    @GetMapping("/student/approve-student/list")
    public String approveStudent(){
        return "All student list who are request for approve";
    }

    @PostMapping("/student/approve-student/approve/{student-id}")
    public String approveStudent(@PathVariable("student-id") String sId){
        return "Student who will be approved by admin now";
    }

    @PostMapping("/student/approve-student/delete/{student-id}")
    public String deleteStudent(@PathVariable("student-id") String sId){
        return "Student who will not be approve instead of delete them";
    }

    @PostMapping("/courses/add-department/{department-details}")
    public String addDepartment(@PathVariable("department-details") CourseDetails department){
        return "department Added Successful";
    }

    @PostMapping("/courses/add-courses/{course-details}")
    public String addCourse(@PathVariable("course-details") CourseDetails details){
        return "Course Added Successful";
    }


}
