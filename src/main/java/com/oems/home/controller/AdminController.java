package com.oems.home.controller;

import com.oems.home.dao.AdminJdbcDao;
import com.oems.home.dao.StudentJdbcDao;
import com.oems.home.dao.TeacherJdbcDao;
import com.oems.home.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class AdminController {

    @Autowired
    StudentJdbcDao studentDao;
    @Autowired
    TeacherJdbcDao teacherDao;
    @Autowired
    AdminJdbcDao adminDao;

    @GetMapping("/admin/board/{user-id}")
    public Dashboard adminBoardManager(@PathVariable("user-id") String user){
        return adminDao.adminBoardManager(user);
    }

  //-----------For teacher approve-------------
    @PostMapping("/request-to-add-teacher")
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
    @PostMapping("/request-to-join-as-student")
    public Student addStudent(Student student){
//        String encodedPassword = new BCryptPasswordEncoder().encode(student.getPassword());
//        student.setPassword(encodedPassword);
        studentDao.create(student);
        return student;
    }

    @GetMapping("/get-available-dept")
    public List<Department> generateAllDepartment(){
        return studentDao.getAllDept();
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
        adminDao.addADepartment(department);
        return "department Added Successful";
    }

    @PostMapping("/admin/add-courses/")
    public String addCourse(CourseDetails details){
        adminDao.addACourses(details);
        return "Course Added Successful";
    }

    @PostMapping("/admin/action/changeSemester")
    public String changeAndGoNextSemester(UserVerificationModel model){ // ************
        if(adminDao.checkUserAndPassword(model)){
            adminDao.updateSemester();
            return "Successful...Enjoy new semester";
        }
        return "Failed... You are not authorized";
    }
    @PostMapping("/admin/course/assign-teacher")
    public void assignTeacher(String courseCode, String teacherId){ //************
        //return courseCode+" Get Its teacher whose teacher id is:"+teacherId;
    	adminDao.assignTeacherToCourse(courseCode, teacherId);
    }

    @GetMapping("/admin/requested-courses")
    public List<RequestCourse> allRequested(){
        return adminDao.listOfRequestedCourses();
    }

    @PostMapping("/admin/requested-courses/approve")
    public void approveCrsRequest(@RequestBody RequestCourse list){
        adminDao.approveCoursesForStudent(list,false);
    }
    @PostMapping("/admin/requested-courses/delete")
    public void DeleteCrsRequest(@RequestBody RequestCourse list){
        adminDao.approveCoursesForStudent(list,true);
    }


}
