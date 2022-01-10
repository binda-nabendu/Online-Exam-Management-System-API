package com.oems.home.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.oems.home.model.CourseDetails;
import com.oems.home.model.Dashboard;
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

    private final RowMapper<Teacher> teacherRowMapper = (rs,rowNumber)->{
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
		return jdbcTemplate.query(joinQueryForAllPendingTeachers,teacherRowMapper);
	}
	
	public void approveOrDeleteTeacher(String tId, boolean approve) {
		if(approve){
            String q1= "update baseUser set adminApproval=1 where nid= ?";
            jdbcTemplate.update(q1,tId);
        }else{
            String q1="delete from teacher where teacherId=?";
            String q2 = "delete from baseUser where nid= ?";
            jdbcTemplate.update(q1,tId);
            jdbcTemplate.update(q2,tId);
        }
	}
	
	public List<Teacher> listOfAllTeacher() {
		String queryForListOfAllTeacher = "select * from baseUser b, teacher t where b.nid=t.teacherId";
		return jdbcTemplate.query(queryForListOfAllTeacher,teacherRowMapper);
	}
	
	private final RowMapper<CourseDetails> crsDetailsRowMapper = (rs, rowNumber)->{
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setCourseCode(rs.getString("courseCode"));
        courseDetails.setCourseName(rs.getString("courseName"));
        courseDetails.setCourseSessions(rs.getInt("courseCurrSession"));
        return courseDetails;
    };

	public List<CourseDetails> listOfAllCoursesOfAllDept() {
		String queryForListOfAllCoursesOfAllDept = "select * from courses";
		return jdbcTemplate.query(queryForListOfAllCoursesOfAllDept,crsDetailsRowMapper);
	}

	public List<CourseDetails> currCoursesOfTeacher(String tId) {
		String queryForCurrCoursesOfTeacher = "select * from courses where teacherId="+tId;
		return jdbcTemplate.query(queryForCurrCoursesOfTeacher,crsDetailsRowMapper);
	}
	
	private final RowMapper<Student> studentRowMapper = (rs, rowNumber)->{
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

	public List<Student> listOfAllStudentOfThatCourse(String courseCode) {
		String queryForAllStudentOfThatCourse ="select * from result where courseCode="+courseCode+" and cgpa=-1";
        return jdbcTemplate.query(queryForAllStudentOfThatCourse, studentRowMapper);
	}

	public Dashboard teacherBoardManager(String tId) {
		String q1= "select COUNT(*) from teacher";
        String q2= "select count(*) from result where courseCode in" +
        	    " (Select courseCode from courses where teacherId="+tId+" ) and cgpa=-1";
        String q3 = "select COUNT(*) from department";
        String q4 = "select COUNT(*) from examPaper where teacherId="+tId;

        Dashboard dashboard = new Dashboard();
        dashboard.setCard1(jdbcTemplate.queryForObject(q1,Integer.class));
        dashboard.setCard2(jdbcTemplate.queryForObject(q2,Integer.class));
        dashboard.setCard3(jdbcTemplate.queryForObject(q3,Integer.class));
        dashboard.setCard4(jdbcTemplate.queryForObject(q4,Integer.class));
        return dashboard;
	}

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
