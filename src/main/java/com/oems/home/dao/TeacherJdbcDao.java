package com.oems.home.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.oems.home.model.Teacher;

@Component
@Repository
public class TeacherJdbcDao implements Dao<Teacher> {
	@Autowired
	private final JdbcTemplate jdbcTemplate;
	
	public TeacherJdbcDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<Teacher> listOfAll() {
		return null;
	}
	
	@Override
	public void create(Teacher teacher) {
		teacher.setRole("TEACHER");
		String sqlQueryForBaseUser = "insert into baseUser(nid ,userName ,fatherName ,motherName ,gender ,contactNo ,email ,dob ,address ,role ,password) values(?,?,?,?,?,?,?,?,?,?,?)";
		String sqlQueryForTeacher = "insert into teacher(teacherId ,eduQualification ,expertise) values(?,?,?)";
		
		int baseUserStatus = jdbcTemplate.update(sqlQueryForBaseUser, teacher.getNid(), teacher.getUserName(),teacher.getFatherName(),
				teacher.getMotherName(), teacher.getGender(), teacher.getContactNo(), teacher.getEmail(),teacher.getDob(),
				teacher.getAddress(),teacher.getRole(),teacher.getPassword());
		int teacherStatus = jdbcTemplate.update(sqlQueryForTeacher,teacher.getNid(),teacher.getEduQualification() ,teacher.getExpertise());
		if (baseUserStatus==1 && teacherStatus==1){
            //todo here
        }
	}
	
	@Override
    public Optional<Teacher> get(String target) {
        return Optional.empty();
    }

    @Override
    public void update(Teacher teacher, String target) {

    }

    @Override
    public void delete(String target) {

    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
