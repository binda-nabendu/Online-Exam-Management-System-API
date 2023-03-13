package com.oems.home.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.oems.home.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

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
		String sqlQueryForBaseUser = "insert into baseuser(nid ,userName ,fatherName ,motherName ,gender ,contactNo ,email ,dob ,address ,role ,password) values(?,?,?,?,?,?,?,?,?,?,?)";
		String sqlQueryForTeacher = "insert into teacher(teacherId ,eduQualification ,expertise) values(?,?,?)";
		
		int baseUserStatus = jdbcTemplate.update(sqlQueryForBaseUser, teacher.getNid(), teacher.getUserName(),teacher.getFatherName(),
				teacher.getMotherName(), teacher.getGender(), teacher.getContactNo(), teacher.getEmail(),teacher.getDob(),
				teacher.getAddress(),teacher.getRole(),teacher.getPassword());
		int teacherStatus = jdbcTemplate.update(sqlQueryForTeacher,teacher.getNid(),teacher.getEduQualification() ,teacher.getExpertise());
//		if (baseUserStatus==1 && teacherStatus==1){
//            System.out.println("Get success");
//      }
	}
    public int assignMark(StudentMark questionPaper) {
        String query = "insert into studentmark(stdId, courseCode, deptId, examId, gotTotalMarks, review) values(?,?,?,?,?,?)";
        int teacherStatus = jdbcTemplate.update(query,questionPaper.getStdID(), questionPaper.getCourseCode(), questionPaper.getDeptId(), questionPaper.getExamId(), questionPaper.getGotTotalMark(), questionPaper.isReview());
        return teacherStatus;
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
    private final RowMapper<Teacher> displayteacherRowMapper = (rs,rowNumber)->{
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
        teacher.setPassword("No one authorized to see this");
        return teacher;
    };
    
	public List<Teacher> listOfNonApprovedTeacher() {
		String joinQueryForAllPendingTeachers = "select * from baseuser b, teacher t where b.nid=t.teacherId and adminApproval=0";
		return jdbcTemplate.query(joinQueryForAllPendingTeachers,teacherRowMapper);
	}
	
	public void approveOrDeleteTeacher(String tId, boolean approve) {
		if(approve){
            String q1= "update baseuser set adminApproval=1 where nid= ?";
            jdbcTemplate.update(q1,tId);
        }else{
            String q1="delete from teacher where teacherId=?";
            String q2 = "delete from baseuser where nid= ?";
            jdbcTemplate.update(q1,tId);
            jdbcTemplate.update(q2,tId);
        }
	}
	
	public List<Teacher> listOfAllTeacher() {
		String queryForListOfAllTeacher = "select * from baseuser b, teacher t where b.nid=t.teacherId";
		return jdbcTemplate.query(queryForListOfAllTeacher,displayteacherRowMapper);
	}
	
	private final RowMapper<CourseDetails> crsDetailsRowMapper = (rs, rowNumber)->{
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setCourseCode(rs.getString("courseCode"));
        courseDetails.setCourseName(rs.getString("courseName"));
        courseDetails.setCourseSessions(rs.getInt("courseCurrSession"));
        courseDetails.setDeptId(rs.getString("deptId"));
        courseDetails.setTeacherId(rs.getString("teacherId"));
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

	public List<Student> listOfAllStudentOfThatCourse(String courseCode, String deptId) {
		String queryForAllStudentOfThatCourse ="select * from baseuser b, student s where b.nid=s.stdId and s.stdId = any " +
                "(select stdId from result where courseCode='"+courseCode+"' and deptId='"+deptId+"' and cgpa=-1)";
        return jdbcTemplate.query(queryForAllStudentOfThatCourse, studentRowMapper);
	}

	public  HashMap<String, Integer> teacherBoardManager(String tId) {
		String q1="select COUNT(*) from studentmark s, courses c where s.courseCode = c.courseCode " +
                  "and review=true and s.courseCode = any(" +
                  "select c.courseCode from courses where c.teacherId="+tId+")";
        String q2= "select count(*) from result where courseCode in " +
                   "(select courseCode from courses where teacherId="+tId+" ) " +
                   "and cgpa=-1";

        String q3 = "select COUNT(*) from department";
        String q4 = "select COUNT(*) from exampaper where teacherId="+tId;

        Dashboard dashboard = new Dashboard();
        dashboard.setCard1(Optional.ofNullable(jdbcTemplate.queryForObject(q1, Integer.class)).orElse(0));
        dashboard.setCard2(Optional.ofNullable(jdbcTemplate.queryForObject(q2, Integer.class)).orElse(0));
        dashboard.setCard3(Optional.ofNullable(jdbcTemplate.queryForObject(q3, Integer.class)).orElse(0));
        dashboard.setCard4(Optional.ofNullable(jdbcTemplate.queryForObject(q4, Integer.class)).orElse(0));
        HashMap<String, Integer> all = new HashMap<String, Integer>();
        all.put("Coming For Review", dashboard.getCard1());
        all.put("Unrated Subject", dashboard.getCard2());
        all.put("Total Department", dashboard.getCard3());
        all.put("Total Exam Owner", dashboard.getCard4());
        return all;
	}


    private final RowMapper<Review> reviewMapper = (rs, rowNumber)->{
        Review review = new Review();
        review.setCourseCode(rs.getString("courseCode"));
        review.setExamId(rs.getInt("examId"));
        review.setStdId(rs.getString("stdId"));
        review.setGotTotalMarks(rs.getDouble("gotTotalMarks"));
        return review;
    };
    public List<Review> studentExamPaperReviewList(String tid) {
        String queryForReviewedStudentList = "select * from studentmark" +
                " where courseCode in (" +
                "select courseCode from courses " +
                "where teacherId ="+tid+") and review = true ";

        return jdbcTemplate.query(queryForReviewedStudentList,reviewMapper);
    }

	public List<QuestionSummery> listOfAllPendingResult(String tId) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String presentDateTime = df.format(today);
        
		String pendingList = "select * from exampaper where teacherId="+tId+" and published=false and "+
				"endingDateTime < '"+ presentDateTime+"'";

		return jdbcTemplate.query(pendingList,(rs, rn)->{
	        QuestionSummery question = new QuestionSummery();
	        question.setExamId(rs.getInt("examId"));
	        question.setCourseCode(rs.getString("courseCode"));
            question.setDeptId(rs.getString("deptId"));
	        question.setTeacherId(rs.getString("teacherId"));
	        question.setPercentageValue(rs.getDouble("percentageValue"));
	        question.setStartingDateTime(rs.getString("startingDateTime"));
	        question.setEndingDateTime(rs.getString("endingDateTime"));
	        question.setTotal(rs.getDouble("total"));
	        return question;
	    });
	}
	
	public List<Student> listOfAllPendingResultStdList(int examId) {
		String query ="select * from baseuser b, student s where b.nid=s.stdId and s.stdId = "+
				"any(select DISTINCT(stdId) from stdansscript where examId = " + examId +")";

        return jdbcTemplate.query(query, studentRowMapper);
	}

	public List<Student> listOfReadyStudentForCgpaOfThatCourse(String courseCode) {
		String query = "select * from baseuser b, student s where b.nid=s.stdId and s.stdId = "+
				"any (select stdId from result where courseCode= '"+courseCode+"' and cgpa=-2)";
		return jdbcTemplate.query(query, studentRowMapper);
	}

	public void assignStdCgpa(String stdId, String courseCode, String deptId, String cgpa, String grade) {
		
		String query = "update result set cgpa = ?, grade = ? where stdId = ? and courseCode = ? and deptId = ?";
		jdbcTemplate.update(query, cgpa, grade, stdId, courseCode, deptId);
	}

    public List<Student> listOfAllStudent() {
        String queryForAllStudentOfThatCourse ="select * from baseuser b, student s where b.nid=s.stdId and b.adminApproval=1";
        return jdbcTemplate.query(queryForAllStudentOfThatCourse, studentRowMapper);
    }
    public List<String> pendingNonPublishedExam(String tId){
        String q1 = "select examId from exampaper where teacherId= '"+tId+"' and published=false";
        return jdbcTemplate.query(q1, (rs, rn)->{
            String ei = rs.getString("examId");
            return ei;
        });
    }
//    public List<AnswerScript> allStudentPendingScript(String tId, int examId) {
//        String q1 = "select DISTINCT(stdId) from stdansscript where examId = " + examId;
//        List<String> allStdId = jdbcTemplate.query(q1, (rs, rn)->{
//            String ei = rs.getString("stdId");
//            return ei;
//        });
//      List<AnswerScript> allScript = new ArrayList<>();
//        for(String ai : allStdId){
//            AnswerScript as = new AnswerScript();
//
//            q1 = "select questionNo,optionNo,optionValue from stdansscript where examId="+examId+" and stdId='"+ai+"'";
//            as.setExamId(examId); as.setStdId(ai);
//
//            List<QuestionOptionPair> all = jdbcTemplate.query(q1, (rs, rn)->{
//                QuestionOptionPair ei = new QuestionOptionPair();
//                ei.setQuestionNo(rs.getInt("questionNo"));
//                ei.setOptionNo(rs.getInt("optionNo"));
//                ei.setValue(rs.getString("optionValue"));
//                return ei;
//            });
//            as.setAllQuestionAnswer(new ArrayList<>(all));
//            allScript.add(as);
//        }
//        return allScript;
//    }
    public AnswerScript allStudentPendingScript(String stdId, int examId) {
        AnswerScript as = new AnswerScript();

        String q1 = "select questionNo,optionNo,optionValue from stdansscript where examId=" + examId + " and stdId='" + stdId + "'";
        as.setExamId(examId);
        as.setStdId(stdId);

        List<QuestionOptionPair> all = jdbcTemplate.query(q1, (rs, rn) -> {
            QuestionOptionPair ei = new QuestionOptionPair();
            ei.setQuestionNo(rs.getInt("questionNo"));
            ei.setOptionNo(rs.getInt("optionNo"));
            ei.setValue(rs.getString("optionValue"));
            return ei;
        });
        as.setAllQuestionAnswer(new ArrayList<>(all));
        return as;
    }
}


















