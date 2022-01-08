package com.oems.home.controller;

import com.oems.home.dao.StudentJdbcDao;
import com.oems.home.dao.TeacherJdbcDao;
import com.oems.home.model.CourseDetails;
import com.oems.home.model.Dashboard;
import com.oems.home.model.Student;
import com.oems.home.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

@RestController
public class AdminController {

    @Autowired
    StudentJdbcDao studentDao;
    @Autowired
    TeacherJdbcDao teacherDao; 

    @GetMapping("/admin-board/{user-id}")
    public Dashboard adminBoardManager(@PathVariable("user-id") String user){
        return studentDao.adminBoardManager(user);
    }

  //-----------For teacher approve-------------
    @PostMapping("/add-teacher")
    public Teacher addTeacher(Teacher teacher){
        teacherDao.create(teacher);
        return teacher;
    }

    @GetMapping("/teachers/approve-teachers/list")
    public List<Teacher> approveTeacher(){
        return teacherDao.listOfNonApprovedTeacher();
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
    public List<Student> approveStudent(){
        return studentDao.listOfNonApprovedStudent();
    }

    @PostMapping("/student/approve-student/approve/{student-id}")
    public void approveStudent(@PathVariable("student-id") String sId){
        studentDao.approveOrDeleteStudent(sId,true);
    }

    @PostMapping("/student/approve-student/delete/{student-id}")
    public void deleteStudent(@PathVariable("student-id") String sId){
        studentDao.approveOrDeleteStudent(sId,false);
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
