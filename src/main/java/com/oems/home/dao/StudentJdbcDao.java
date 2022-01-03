package com.oems.home.dao;

import com.oems.home.model.Dashboard;
import com.oems.home.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Component
@Repository
public class StudentJdbcDao implements Dao<Student> {
    //private static final Logger log = LoggerFactory.getLogger(StudentJdbcDao.class);
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public StudentJdbcDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Student> listOfAll() {
        return null;
    }

    @Override
    public void create(Student student) {
        student.setRole("STUDENT");
        String sqlQueryForBaseUser = "insert into baseUser(nid ,userName ,fatherName ,motherName ,gender ,contactNo ,email ,dob ,address ,role ,password) values(?,?,?,?,?,?,?,?,?,?,?)";
        String sqlQueryForStudent = "insert into student(stdId ,deptId ,semester) values(?,?,?)";
        int baseUserStatus = jdbcTemplate.update(sqlQueryForBaseUser, student.getNid(), student.getUserName(),student.getFatherName(),
                student.getMotherName(), student.getGender(), student.getContactNo(), student.getEmail(),student.getDob(),
                student.getAddress(),student.getRole(),student.getPassword());
        int studentStatus = jdbcTemplate.update(sqlQueryForStudent,student.getNid(),student.getDeptId(),student.getSemester());
        if (baseUserStatus==1 && studentStatus==1){
            //todo here
        }
    }

    RowMapper<Student> rowMapper = (rs,rowNumber)->{
        Student student = new Student();
        student.setNid(rs.getString("nid"));
        student.setUserName(rs.getString("userName"));
        student.setFatherName(rs.getString("fatherName"));
        student.setMotherName(rs.getString("motherName"));
        student.setGender(rs.getInt("gender"));
        student.setContactNo(rs.getString("contactNo"));
        student.setEmail(rs.getString("email"));
        student.setDob(rs.getString("dob"));
        student.setAddress(rs.getString("address"));
        student.setDeptId(rs.getString("deptId"));
        student.setSemester(rs.getInt("semester"));
        //here we have to add batch later
        student.setRole(rs.getString("role"));
        student.setPassword(rs.getString("password"));
        return student;
    };

    public List<Student> listOfNonApprovedStudent(){
        String joinQueryForAllStudentInfo ="select * from baseUser b, student s where b.nid=s.stdId and adminApproval=0";
        return jdbcTemplate.query(joinQueryForAllStudentInfo,rowMapper);
    }

    @Override
    public Optional<Student> get(String target) {
        return Optional.empty();
    }

    @Override
    public void update(Student student, String target) {

    }
    public void approveOrDeleteStudent(String id,Boolean approve){
        if(approve){
            String q1= "update baseUser set adminApproval=1 where nid= ?";
            jdbcTemplate.update(q1,id);
        }else{
            String q1="delete from student where stdId=?";
            String q2 = "delete from baseUser where nid= ?";
            jdbcTemplate.update(q1,id);
            jdbcTemplate.update(q2,id);
        }
    }
    public Dashboard adminBoardManager(String id){
        String q1= "select COUNT(*) from student";
        String q2= "select COUNT(*) from teacher";
        String q3 = "select COUNT(*) from department";
        String q4 = "select COUNT(*) from exam where teacherId=?";
        Dashboard dashboard = new Dashboard();
        dashboard.setTotalStudents(jdbcTemplate.queryForObject(q1,Integer.class));
        dashboard.setTotalTeachers(jdbcTemplate.queryForObject(q2,Integer.class));
        dashboard.setTotalDepartments(jdbcTemplate.queryForObject(q3,Integer.class));
        //todo...
        //dashboard.setTotalUpComingExam(jdbcTemplate.queryForObject(q4,Integer.class));
        return dashboard;

    }


    @Override
    public void delete(String target) {

    }
}
