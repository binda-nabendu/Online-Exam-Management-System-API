package com.oems.home.dao;

import com.oems.home.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Component
@Repository
public class AdminJdbcDao{
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public AdminJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public Dashboard adminBoardManager(String id){
        String q1= "select COUNT(*) from teacher";
        String q2= "select COUNT(*) from student";
        String q3= "select COUNT(*) from department";
        String q4= "select COUNT(*) from examPaper where teacherId="+id;

        Dashboard dashboard = new Dashboard();
        dashboard.setCard1(Optional.ofNullable(jdbcTemplate.queryForObject(q1, Integer.class)).orElse(-1));
        dashboard.setCard2(Optional.ofNullable(jdbcTemplate.queryForObject(q2, Integer.class)).orElse(-1));
        dashboard.setCard3(Optional.ofNullable(jdbcTemplate.queryForObject(q3, Integer.class)).orElse(-1));
        dashboard.setCard4(Optional.ofNullable(jdbcTemplate.queryForObject(q4, Integer.class)).orElse(-1));
        return dashboard;

    }

    public boolean checkUserAndPassword(UserVerificationModel userDetails){
        String verifier = "select nid, email, password,role from " +
                          "baseUser where nid ="+userDetails.getNid();

        UserVerificationModel modelUser;
        modelUser = Optional.ofNullable (jdbcTemplate.queryForObject(verifier,(rs, rowNumber)->{
            UserVerificationModel model=new UserVerificationModel();
            model.setNid(rs.getString("nid"));
            model.setEmail(rs.getString("email"));
            model.setPassword(rs.getString("password"));
            model.setRole(rs.getString("role"));

            return model;
        })).orElse(new UserVerificationModel());
        System.out.println(modelUser.toString());
        return userDetails.getNid().equals(modelUser.getNid()) &&
                    userDetails.getPassword().equals(modelUser.getPassword()) &&
                    modelUser.getRole().equals("ADMIN");
       
    }

    public void updateSemester() {
    	String q1 = "update student set semester=semester+1";
    	String q2 = "update result set previousSemCrs=false";
    	String q3 = "update result set previousSemCrs=true where cgpa=-1";
    	String q4 = "update result set cgpa=-2 where cgpa=-1";
    	String q5 = "update courses set courseCurrSession=courseCurrSession+1 where teacherId !='Not assigned'";

    	jdbcTemplate.update(q1);
    	jdbcTemplate.update(q2);
    	jdbcTemplate.update(q3);
    	jdbcTemplate.update(q4);
    	jdbcTemplate.update(q5);
    }

    public List<RequestCourse> listOfRequestedCourses() {
        String q1="select * from student s,requestcourse r,courses c" +
                " where s.stdId=r.stdId and r.courseCode=c.courseCode and r.deptId=c.deptId";
        return jdbcTemplate.query(q1,(rs, rowNumber)->{
            RequestCourse requestedCourse = new RequestCourse();
            requestedCourse.setStdId(rs.getString("stdId"));
            requestedCourse.setDeptId(rs.getString("deptId"));
            requestedCourse.setBatch(rs.getInt("batch"));
            requestedCourse.setSemester(rs.getInt("semester"));
            requestedCourse.setCourseCode(rs.getString("courseCode"));
            requestedCourse.setCourseName(rs.getString("courseName"));
            requestedCourse.setTeacherId(rs.getString("teacherId"));
            requestedCourse.setCourseCurrSession(rs.getString("courseCurrSession"));
            return requestedCourse;
        });
    }

    public void approveCoursesForStudent(RequestCourse requestedCourse, boolean isDelete) {

        String queryForDelFrmRequestCourse = "delete from requestCourse where stdId=? " +
                "and courseCode=? and deptId=?";

        String queryForAddSub = " insert into result " +
                "(stdId, courseCode,deptId,courseSession,semester) " +
                "values (?,?,?,?,?)";

        jdbcTemplate.update(queryForDelFrmRequestCourse, requestedCourse.getStdId(),
                requestedCourse.getCourseCode(), requestedCourse.getDeptId());

        if(isDelete) return;

        jdbcTemplate.update(queryForAddSub, requestedCourse.getStdId(),
                requestedCourse.getCourseCode(), requestedCourse.getDeptId(), requestedCourse.getCourseCurrSession(),
                requestedCourse.getSemester());


    }

    public void addACourses(CourseDetails details) {
        if (details.getCourseSessions() < 0) {
            details.setCourseSessions(-9999);
        }
        if(details.getTeacherId().length()<2) {


            String queryForAddCourse = "Insert into courses " +
                    " (courseCode, deptId, courseName, courseCurrSession)  " +
                    "values(?,?,?,?)";
            jdbcTemplate.update(queryForAddCourse, details.getCourseCode(), details.getDeptId(),
                    details.getCourseName(), details.getCourseSessions());
        }else{
            String queryForAddCourse = "Insert into courses " +
                    " (courseCode, deptId, courseName,teacherId, courseCurrSession)  " +
                    "values(?,?,?,?,?)";
            jdbcTemplate.update(queryForAddCourse, details.getCourseCode(), details.getDeptId(),
                    details.getCourseName(), details.getTeacherId(), details.getCourseSessions());
        }
    }

    public void addADepartment(Department department) {
        String queryForAddDept = "insert into department " +
                "(deptId, deptName)" +
                "values(?,?)";
        jdbcTemplate.update(queryForAddDept, department.getDeptId(), department.getDeptName());
    }
	public void assignTeacherToCourse(String courseCode, String deptId, String teacherId) {
		String q1 = "update courses set teacherId =? where courseCode=? and deptId=?";
		jdbcTemplate.update(q1,teacherId,courseCode,deptId);
	}
}

















