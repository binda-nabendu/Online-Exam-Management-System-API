package com.oems.home.controller;

import com.oems.home.dao.AdminJdbcDao;
import com.oems.home.dao.StudentJdbcDao;
import com.oems.home.dao.TeacherJdbcDao;
import com.oems.home.model.*;
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
    @Autowired
    AdminJdbcDao adminJdbcDao;

    @GetMapping("/admin/board/{user-id}")
    public Dashboard adminBoardManager(@PathVariable("user-id") String user){
        return studentDao.adminBoardManager(user);
    }

  //-----------For teacher approve-------------
    @PostMapping("/admin/add-teacher")
    public Teacher addTeacher(Teacher teacher){
        teacherDao.create(teacher);
        return teacher;
    }

    @GetMapping("/admin/approve-teachers/list")
    public List<Teacher> approveTeacher(){
        return teacherDao.listOfNonApprovedTeacher();
    }

    @PostMapping("/admin/approve-teachers/approve/{teacher-id}")
    public void approveTeacher(@PathVariable("teacher-id") String tId){
    	teacherDao.approveOrDeleteTeacher(tId, true);
    }

    @PostMapping("/admin/approve-teachers/delete/{teacher-id}")
    public void deleteTeacher(@PathVariable("teacher-id") String tId){
    	teacherDao.approveOrDeleteTeacher(tId, false);
    }

    //-----------For student approve-------------
    @PostMapping("/admin/add-student")
    public Student addStudent(Student student){
//        String encodedPassword = new BCryptPasswordEncoder().encode(student.getPassword());
//        student.setPassword(encodedPassword);
        studentDao.create(student);
        return student;
    }

    @GetMapping("/admin/approve-student/list")
    public List<Student> approveStudent(){
        return studentDao.listOfNonApprovedStudent();
    }

    @PostMapping("/admin/approve-student/approve/{student-id}")
    public void approveStudent(@PathVariable("student-id") String sId){
        studentDao.approveOrDeleteStudent(sId,true);
    }

    @PostMapping("/admin/approve-student/delete/{student-id}")
    public void deleteStudent(@PathVariable("student-id") String sId){
        studentDao.approveOrDeleteStudent(sId,false);
    }

    @PostMapping("/admin/courses/add-department/")
    public String addDepartment(Department department){
        adminJdbcDao.addADepartment(department);
        return "department Added Successful";
    }

    @PostMapping("/admin/add-courses/{course-details}")
    public String addCourse(@PathVariable("course-details") CourseDetails details){
        adminJdbcDao.addACourses(details);
        return "Course Added Successful";
    }

    @PostMapping("/admin/action/changeSemester")
    public String changeAndGoNextSemester(UserVerificationModel model){
        if(adminJdbcDao.checkUserAndPassword(model)){
            adminJdbcDao.updateSemester();
            return "Successful...Enjoy new semester";
        }
        return "Failed... You are not authorized";
    }

    @GetMapping("/admin/requested-courses")
    public List<RequestCourse> allRequested(){
        return adminJdbcDao.listOfRequestedCourses();
    }

    @PostMapping("/admin/requested-courses")
    public void approveCrsRequest(List<RequestCourse> list){
        adminJdbcDao.approveCoursesForStudent(list);
    }


}
