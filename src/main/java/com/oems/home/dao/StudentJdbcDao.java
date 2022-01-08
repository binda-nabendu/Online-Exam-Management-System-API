package com.oems.home.dao;

import com.oems.home.model.CourseDetails;
import com.oems.home.model.Dashboard;
import com.oems.home.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

    public List<Student> listOfNonApprovedStudent(){
        String joinQueryForAllStudentInfo ="select * from baseUser b, student s where b.nid=s.stdId and adminApproval=0";
        return jdbcTemplate.query(joinQueryForAllStudentInfo, studentRowMapper);
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
        String q1= "select COUNT(*) from teacher";
        String q2= "select COUNT(*) from student";
        String q3 = "select COUNT(*) from department";

        //todo... this query will have to write for upcoming not all
        String q4 = "select COUNT(*) from examPaper where teacherId="+id;

        Dashboard dashboard = new Dashboard();
        dashboard.setCard1(jdbcTemplate.queryForObject(q1,Integer.class));
        dashboard.setCard2(jdbcTemplate.queryForObject(q2,Integer.class));
        dashboard.setCard3(jdbcTemplate.queryForObject(q3,Integer.class));
        dashboard.setCard4(jdbcTemplate.queryForObject(q4,Integer.class));
        return dashboard;

    }
    public Dashboard studentBoardManager(String id){
        String q1= "select COUNT(*) from result where stdId="+id+" and cgpa=-1";
        String q2= "select COUNT(*) from result where stdId="+id+" and cgpa>0";

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String now = df.format(today);

        String q3 = "select COUNT(*) " +
                "from examPaper where courseCode=any(" +
                "select courseCode from result where stdId="+id+" and cgpa=-1) and startingDateTime > "+now;

        //todo
        // this query will have to write for total not reviewed by teacher but he send
        // String q4 = "select COUNT(*) from examPaper where teacherId="+id;

        Dashboard dashboard = new Dashboard();
        dashboard.setCard1(jdbcTemplate.queryForObject(q1,Integer.class));
        dashboard.setCard2(jdbcTemplate.queryForObject(q2,Integer.class));
        dashboard.setCard3(jdbcTemplate.queryForObject(q3,Integer.class));
        //dashboard.setCard4(jdbcTemplate.queryForObject(q4,Integer.class));
        return dashboard;
    }

    private final RowMapper<CourseDetails> crsDetailsRowMapper = (rs, rowNumber)->{
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setCourseCode(rs.getString("courseCode"));
        courseDetails.setCourseName(rs.getString("courseName"));
        courseDetails.setCourseSessions(rs.getString("courseCurrSession"));
        return courseDetails;
    };

    public List<CourseDetails> completedCoursesByStudent(String stdId){
        String q1 = "select * from courses where courseCode=any(" +
                "select courseCode from result where stdId="+stdId+" and cgpa>0)";
        return jdbcTemplate.query(q1,crsDetailsRowMapper);
    }

    @Override
    public void delete(String target) {

    }

    public List<CourseDetails> departmentalCourseSet(String dept){
        String q1 ="select * from courses where deptId="+dept;
        return jdbcTemplate.query(q1,crsDetailsRowMapper);

    }
    public List<CourseDetails> allRunningCourseDetails(String stdId){
        String q1 ="select courseCode from result where stdId="+stdId+" and cgpa="+-1;
        return jdbcTemplate.query(q1,(rs, rowNumber)->{
            CourseDetails courseDetails = new CourseDetails();
            courseDetails.setCourseCode(rs.getString("courseCode"));
            return courseDetails;
        });
    }
}
