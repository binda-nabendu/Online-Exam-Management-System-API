package com.oems.home.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.oems.home.model.Student;
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

    RowMapper<Teacher> rowMapper = (rs,rowNumber)->{
        Teacher teacher = new Teacher();
        teacher.setNid(rs.getString("nid"));
        teacher.setUserName(rs.getString("userName"));
        teacher.setFatherName(rs.getString("fatherName"));
        teacher.setMotherName(rs.getString("motherName"));
        teacher.setGender(rs.getInt("gender"));
        teacher.setContactNo(rs.getString("contactNo"));
        teacher.setEmail(rs.getString("email"));
        teacher.setDob(rs.getString("dob"));
        teacher.setAddress(rs.getString("address"));
        teacher.setEduQualification(rs.getString("eduQualification"));
        teacher.setExpertise(rs.getString("expertise"));
        teacher.setRole(rs.getString("role"));
        teacher.setPassword(rs.getString("password"));
        return teacher;
    };
	public List<Teacher> listOfNonApprovedTeacher() {
		String joinQueryForAllPendingTeachers = "select * from baseUser b, teacher t where b.nid=t.teacherId and adminApproval=0";
		return jdbcTemplate.query(joinQueryForAllPendingTeachers,rowMapper);
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
