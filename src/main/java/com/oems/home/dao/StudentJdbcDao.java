package com.oems.home.dao;

import com.oems.home.model.*;
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

    private int currentBatch(String deptId){
        String q1="select currentBatch from department where deptId="+deptId;

        return Optional.ofNullable(jdbcTemplate.queryForObject(q1, Integer.class)).orElse(0);
    }

    @Override
    public void create(Student student) {
        student.setRole("STUDENT");
        int setBatch = currentBatch(student.getDeptId());

        String sqlQueryForBaseUser = "insert into baseuser(nid ,userName ,fatherName ,motherName ,gender ,contactNo ,email ,dob ,address ,role ,password) values(?,?,?,?,?,?,?,?,?,?,?)";
        String sqlQueryForStudent = "insert into student(stdId ,deptId ,semester, batch) values(?,?,?,?)";


        int baseUserStatus = jdbcTemplate.update(sqlQueryForBaseUser, student.getNid(), student.getUserName(),student.getFatherName(),
                student.getMotherName(), student.getGender(), student.getContactNo(), student.getEmail(),student.getDob(),
                student.getAddress(),student.getRole(),student.getPassword());
        int studentStatus = jdbcTemplate.update(sqlQueryForStudent,student.getNid(),student.getDeptId(),student.getSemester(),setBatch);
        if (baseUserStatus==1 && studentStatus==1){
            System.out.println("Student inserted");
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
        String joinQueryForAllStudentInfo ="select * from baseuser b, student s where b.nid=s.stdId and adminApproval=0";
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
            String q1= "update baseuser set adminApproval=1 where nid= ?";
            jdbcTemplate.update(q1,id);
        }else{
            String q1="delete from student where stdId=?";
            String q2 = "delete from baseuser where nid= ?";
            jdbcTemplate.update(q1,id);
            jdbcTemplate.update(q2,id);
        }
    }

    public Dashboard studentBoardManager(String id){
        String q1= "select COUNT(*) from result where stdId="+id+" and cgpa=-1";
        String q2= "select COUNT(*) from result where stdId="+id+" and cgpa>0";

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String now = df.format(today);

        String q3 = "select COUNT(*) from exampaper where courseCode = any( " +
                "select courseCode from result where stdId= "+id+" and cgpa=-1) and startingDateTime > '"+now+"'";

        String q4 = "select COUNT(*) from studentmark where review=true";

        Dashboard dashboard = new Dashboard();
        dashboard.setCard1(Optional.ofNullable(jdbcTemplate.queryForObject(q1, Integer.class)).orElse(0));
        dashboard.setCard2(Optional.ofNullable(jdbcTemplate.queryForObject(q2, Integer.class)).orElse(0));
        dashboard.setCard3(Optional.ofNullable(jdbcTemplate.queryForObject(q3, Integer.class)).orElse(0));
        dashboard.setCard4(Optional.ofNullable(jdbcTemplate.queryForObject(q4, Integer.class)).orElse(0));
        return dashboard;
    }

    private final RowMapper<CourseDetails> crsDetailsRowMapper = (rs, rowNumber)->{
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setCourseCode(rs.getString("courseCode"));
        courseDetails.setDeptId(rs.getString("deptId"));
        courseDetails.setCourseName(rs.getString("courseName"));
        courseDetails.setTeacherId(rs.getString("teacherId"));
        courseDetails.setCourseSessions(rs.getInt("courseCurrSession"));
        return courseDetails;
    };

    public List<CourseDetails> completedCoursesByStudent(String stdId){
        String q1 = "select * from courses where courseCode=any(" +
                "select courseCode from result where stdId="+stdId+" and cgpa>0)";
        return jdbcTemplate.query(q1,crsDetailsRowMapper);
    }

    public void requestedCourseAdd(List<CourseDetails> all, String stdId) {
        for (CourseDetails course:all) {

            String queryForInsIntoReq="Insert into requestcourse" +
                    "(stdId, courseCode, deptId)" +
                    "values(?,?,?)";
            jdbcTemplate.update(queryForInsIntoReq,stdId,course.getCourseCode(),course.getDeptId());
        }
    }

    @Override
    public void delete(String target) {

    }

    public String getDeptId(String stdId){
        String q1= "select deptId from student where stdId='"+stdId+"'";
        return Optional.ofNullable(jdbcTemplate.queryForObject(q1, String.class)).orElse("0000");
    }

    public List<CourseDetails> departmentalCourseSet(String dept){
        String q1 ="select * from courses where deptId="+dept+" and teacherId != 'Not assigned' ";
        return jdbcTemplate.query(q1,crsDetailsRowMapper);

    }
    public List<CourseDetails> allRunningCourseDetails(String stdId){
        String q1 ="select * from result r, courses c where r.courseCode=c.courseCode and r.stdId="+stdId+" and r.cgpa=-1";
        return jdbcTemplate.query(q1,crsDetailsRowMapper);
    }
    protected final RowMapper<QuestionSummery> questionSummaryMapper = (rs, rn)->{
        QuestionSummery question = new QuestionSummery();
        question.setExamId(rs.getInt("examId"));
        question.setCourseCode(rs.getString("courseCode"));
        question.setTeacherId(rs.getString("teacherId"));
        question.setPercentageValue(rs.getDouble("percentageValue"));
        question.setStartingDateTime(rs.getString("startingDateTime"));
        question.setEndingDateTime(rs.getString("endingDateTime"));
        question.setTotal(rs.getDouble("total"));
        return question;
    };

    public List<QuestionSummery> upcomingExamForStudent(String stdId) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String presentDateTime = df.format(today);
        String q1 =" select * from exampaper where (courseCode, courseSession) in(" +
                "select courseCode, courseSession from result" +
                " where cgpa=-1 and stdId="+stdId+
                ")  and exampaper.startingDateTime> '"+presentDateTime+"'";

        return jdbcTemplate.query(q1,questionSummaryMapper);
    }

    public List<QuestionSummery> prevExamForStudent(String stdId) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String presentDateTime = df.format(today);
        String q1 =" select * from exampaper where (courseCode, courseSession) in(" +
                "select courseCode, courseSession from result" +
                " where (cgpa=-1 or previousSemCrs = true) and stdId= "+stdId +
                ") and endingDateTime< '"+presentDateTime+"'";

        List<QuestionSummery> l1= jdbcTemplate.query(q1,questionSummaryMapper);

        for(QuestionSummery qs:l1){
            String pubQuery = "select published from exampaper where examId="+qs.getExamId();
            Boolean published=jdbcTemplate.queryForObject(pubQuery,Boolean.class);
            if(Boolean.TRUE.equals(published)){
                String queryForGettingMark = " select gotTotalMarks from studentmark where examId= "+qs.getExamId();
                double mark = Optional.ofNullable(jdbcTemplate.queryForObject(queryForGettingMark, Double.class)).orElse(0.00);
                qs.setObtainMark(mark);
            }
        }
        return l1;
    }

    public void requestForReview(String stdId, int examId) {
        String queryForActiveReviewFlag = "update studentmark set review=true" +
                " where stdId=? and examId=?";
        jdbcTemplate.update(queryForActiveReviewFlag,stdId,examId);
    }

    public void ReceiveAnswer(AnswerScript ansScript) {
        for(QuestionOptionPair qp : ansScript.getAllQuestionAnswer()) {
            String s = "insert into stdansscript " +
                    "(stdId, examId, questionNo, optionNo) " +
                    "values(?,?,?,?)";
            jdbcTemplate.update(s, ansScript.getStdId(), ansScript.getExamId(), qp.getQuestionNo(), qp.getOptionNo());
        }
    }

    public List<Department> getAllDept() {
        String queryForGettingAllDept =" select  * from department";
        return jdbcTemplate.query(queryForGettingAllDept,(rs,rt)->{
            Department dept = new Department();
            dept.setDeptId(rs.getString("deptId"));
            dept.setDeptName(rs.getString("deptName"));
            return dept;
        });
    }
}
