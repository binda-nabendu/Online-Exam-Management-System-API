package com.oems.home.dao;

import com.oems.home.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public class AdminJdbcDao{
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public AdminJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public boolean checkUserAndPassword(UserVerificationModel userDetails){
        String verifier = "select nid, email, password,role from " +
                          "baseUser where nid ="+userDetails.getNid();

        UserVerificationModel modelUser;
        modelUser = jdbcTemplate.queryForObject(verifier,(rs, rowNumber)->{
            UserVerificationModel model=new UserVerificationModel();
            model.setNid(rs.getString("nid"));
            model.setEmail(rs.getString("email"));
            model.setPassword(rs.getString("password"));
            model.setRole(rs.getString("role"));

            return model;
        });
        if(modelUser==null)
            return false;
        else{
            return userDetails.getNid().equals(modelUser.getNid()) &&
                    userDetails.getPassword().equals(modelUser.getPassword()) &&
                    userDetails.getEmail().equals(modelUser.getEmail()) &&
                    modelUser.getRole().equals("ADMIN");
        }
    }

    public void updateSemester() {

    }

    public List<RequestCourse> listOfRequestedCourses() {
        String q1="select * from student s,requestcourse r,courses c" +
                " where s.stdId=r.stdId and r.courseCode=c.courseCode";
        return jdbcTemplate.query(q1,(rs, rowNumber)->{
            RequestCourse requestedCourse = new RequestCourse();
            requestedCourse.setCourseCode(rs.getString("courseCode"));
            return requestedCourse;
        });
    }

    public void approveCoursesForStudent(List<RequestCourse> list) {
        for(RequestCourse requestedCourse : list){
            String queryForRemoveFormRequestedCourseTable = " delete from requestcourse where stdId="
                                                            +requestedCourse.getStdId()+" and courseCode="
                                                            +requestedCourse.getCourseCode();
            String queryForAddSub = " insert into result " +
                                    "(stdId, courseCode,semester) " +
                                    "values (?,?,?)";
            jdbcTemplate.update(queryForRemoveFormRequestedCourseTable);
            jdbcTemplate.update(queryForAddSub, requestedCourse.getStdId(),requestedCourse.getCourseCode(),requestedCourse.getSemester());
        }
    }

    public void addACourses(CourseDetails details) {
        if(details.getTeacherId().length()<2){
            details.setTeacherId("Not assigned");
        }
        if(details.getCourseSessions()<0){
            details.setCourseSessions(-9999);
        }
        String queryForAddCourse = "Insert into courses " +
                " (courseCode, deptId, courseName,teacherId, courseCurrSession)  " +
                "values(?,?,?,?,?)";
        jdbcTemplate.update(queryForAddCourse,details.getCourseCode(),details.getDeptId(),details.getCourseName(),details.getTeacherId(),details.getCourseSessions());

    }

    public void addADepartment(Department department) {
        String queryForAddDept = "insert into department " +
                "(deptId, deptName)" +
                "values(?,?)";
        jdbcTemplate.update(queryForAddDept, department.getDeptId(), department.getDeptName());
    }
}
