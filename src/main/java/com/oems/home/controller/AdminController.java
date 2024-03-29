package com.oems.home.controller;

import com.oems.home.dao.AdminJdbcDao;
import com.oems.home.dao.StudentJdbcDao;
import com.oems.home.dao.TeacherJdbcDao;
import com.oems.home.model.*;
import com.oems.home.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AdminController {

    @Autowired
    StudentJdbcDao studentDao;
    @Autowired
    TeacherJdbcDao teacherDao;
    @Autowired
    AdminJdbcDao adminDao;
    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("/admin/board")
    public HashMap<String, Integer> adminBoardManager(@RequestHeader(value = "Authorization") String token){
        String adminId = jwtUtil.extractUsername(token.substring(7));
        return adminDao.adminBoardManager(adminId);
    }

  //-----------For teacher approve-------------
    @PostMapping("/public/request-to-join-as-teacher")
    public Teacher addTeacher(Teacher teacher){
        System.out.println("the value is :"+teacher.getNid());
        teacherDao.create(teacher);
        return teacher;
    }

    @GetMapping("/admin/approve-teachers/list")
    public List<Teacher> approveTeacher(){
        return teacherDao.listOfNonApprovedTeacher();
    }

    @PostMapping("/admin/approve-teachers/approve/{teacher-id}")
    public void approveTeacher(@PathVariable("teacher-id") String tId){
//    	teacherDao.approveOrDeleteTeacher(tId, true);
    }

    @PostMapping("/admin/approve-teachers/delete/{teacher-id}")
    public void deleteTeacher(@PathVariable("teacher-id") String tId){
//    	teacherDao.approveOrDeleteTeacher(tId, false);
    }

    //-----------For student approve-------------
    @PostMapping("/public/request-to-join-as-student")
    public Student addStudent(Student student){
//        String encodedPassword = new BCryptPasswordEncoder().encode(student.getPassword());
//        student.setPassword(encodedPassword);
        studentDao.create(student);
        return student;
    }

    @GetMapping("/public/get-available-dept")
    public List<Department> generateAllDepartment(){
        return studentDao.getAllDept();
    }

    @GetMapping("/admin/approve-student/list")
    public List<Student> approveStudent(){
        return studentDao.listOfNonApprovedStudent();
    }

    @PostMapping("/admin/approve-student/approve/{student-id}")
    public void approveStudent(@PathVariable("student-id") String sId){
//        studentDao.approveOrDeleteStudent(sId,true);
    }

    @PostMapping("/admin/approve-student/delete/{student-id}")
    public void deleteStudent(@PathVariable("student-id") String sId){
//        studentDao.approveOrDeleteStudent(sId,false);
    }

    @PostMapping("/admin/add-department/")
    public String addDepartment(Department department){
//        adminDao.addADepartment(department);
        return "department Added Successful";
    }

    @PostMapping("/admin/add-courses/")
    public String addCourse(CourseDetails details){
        adminDao.addACourses(details);
        return "Course Added Successful";
    }

    @PostMapping("/admin/action/changeSemester")
    public ActionMsg changeAndGoNextSemester(String nid, String password){
/*    	UserVerificationModel model =  new UserVerificationModel();
    	model.setNid(nid);
    	model.setPassword(password);
        ActionMsg am = new ActionMsg();

    	if(adminDao.checkUserAndPassword(model)){
            adminDao.updateSemester();
            am.setStatus(true);
            return am;
        }*/
        return null;
    }
    @PostMapping("/admin/course/assign-teacher")
    public String assignTeacher(String courseCode, String deptId, String teacherId){
    	
//    	return adminDao.assignTeacherToCourse(courseCode, deptId, teacherId);
        return "";
    }

    @GetMapping("/admin/requested-courses")
    public List<RequestCourse> allRequestedCourses(){
        return adminDao.listOfRequestedCourses();
    }

    @PostMapping("/admin/requested-courses/approve")
    public void approveCrsRequest(@RequestBody RequestCourse reqCrs){
//        adminDao.approveCoursesForStudent(reqCrs,false);
    }
    @PostMapping("/admin/requested-courses/delete")
    public void DeleteCrsRequest(@RequestBody RequestCourse reqCrs){
//        adminDao.approveCoursesForStudent(reqCrs,true);
    }


}
