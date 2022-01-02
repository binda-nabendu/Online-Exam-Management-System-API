package com.oems.home.dao;

import com.oems.home.ExamManagementSystemApplication;
import com.oems.home.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Component
@Repository
public class StudentJdbcDao implements Dao<Student> {
    private static final Logger log = LoggerFactory.getLogger(StudentJdbcDao.class);
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
        int baseUserStatus = jdbcTemplate.update(sqlQueryForBaseUser, student.getNid(), student.getName(),student.getFatherName(),
                student.getMotherName(), student.getGender(), student.getContactNo(), student.getEmail(),student.getDob(),
                student.getAddress(),student.getRole(),student.getPassword());
        int studentStatus = jdbcTemplate.update(sqlQueryForStudent,student.getNid(),student.getDeptId(),student.getSemester());
        if (baseUserStatus==1 && studentStatus==1){
            //todo here
        }
    }

    @Override
    public Optional<Student> get(String target) {
        return Optional.empty();
    }

    @Override
    public void update(Student student, String target) {

    }

    @Override
    public void delete(String target) {

    }
}
