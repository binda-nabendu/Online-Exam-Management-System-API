package com.oems.home.controller;

import com.oems.home.dao.ExamManagerJdbcDao;
import com.oems.home.dao.TeacherJdbcDao;
import com.oems.home.model.*;

import com.oems.home.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class TeacherController {
	@Autowired
    TeacherJdbcDao teacherDao;
    @Autowired
    ExamManagerJdbcDao examDao;
    @Autowired
    JwtUtil jwtUtil;
	
    @GetMapping("/teacher/board")
    public HashMap<String, Integer> TeacherBoardManager(@RequestHeader(value = "Authorization") String token){
        String tId = jwtUtil.extractUsername(token.substring(7));
    	return teacherDao.teacherBoardManager(tId);
    }
    @GetMapping("/teacher/all-teachers")
    public List<Teacher> allTeachers(){
    	return teacherDao.listOfAllTeacher();
    }
    @GetMapping("/teacher/all-courses")
    public List<CourseDetails> allCoursesOfAllDept(){
    	return teacherDao.listOfAllCoursesOfAllDept();
    }
    @GetMapping("/teacher/courses")
    public List<CourseDetails> allCurrentCrsOfThatTeacher(@RequestHeader(value = "Authorization") String token){
        String tId = jwtUtil.extractUsername(token.substring(7));
    	return teacherDao.currCoursesOfTeacher(tId);
    }
    @GetMapping("/teacher/courses/my-students/{course-code}")
    public List<Student> allStudentOfThatCourse(@PathVariable("course-code")String courseCode,String deptId){
    	return teacherDao.listOfAllStudentOfThatCourse(courseCode, deptId);
    }

    @GetMapping("/teacher/all-students")
    public List<Student> allStudents(){
        return teacherDao.listOfAllStudent();
    }
    // !---------- must be full details of exam paper needed-----------!
    @PostMapping("/teacher/create-exams/question")
    public QuestionPaper QuestionPaperHandler(@RequestBody QuestionPaper questionPaper){
        questionPaper.setExamId(examDao.getLastExamId()+1);
        examDao.create(questionPaper);
        return questionPaper;
    }

    @GetMapping("/teacher/see-questions/{question-paper-id}")
    public Optional<QuestionPaper> QuestionPaperHandler(@PathVariable("question-paper-id") String qId){
        return examDao.get(qId);
    }

    @GetMapping("/teacher/all-questions")
    public List<QuestionSummery> getAllQuestionThatTeacherMade(@RequestHeader(value = "Authorization") String token){
        String tId = jwtUtil.extractUsername(token.substring(7));
        return examDao.returnAllQuestionAccordingToTeacher(tId);
    }
  
    @GetMapping("/teacher/all-pending-result")
    public List<QuestionSummery> allPendingResult(@RequestHeader(value = "Authorization") String token){
        String tId = jwtUtil.extractUsername(token.substring(7));
        return teacherDao.listOfAllPendingResult(tId);
    }
  
    @GetMapping("/teacher/all-pending-result/student-list/{exam-id}")
    public List<Student> allPendingResultStdList(@PathVariable("exam-id") int examId){
        return teacherDao.listOfAllPendingResultStdList(examId);
    }
    @GetMapping("/teacher/assign-cgpa/{course-code}")
    public List<Student> readyStudentForCgpaOfThatCourse(@PathVariable("course-code") String courseCode){
        return teacherDao.listOfReadyStudentForCgpaOfThatCourse(courseCode);
    }
    @PostMapping("/teacher/assign-cgpa")
    public void assignCgpa(String stdId, String courseCode, String deptId, String cgpa, String grade) {
    	teacherDao.assignStdCgpa(stdId, courseCode, deptId, cgpa, grade);
    }
    
    
    
    @GetMapping("/teacher/receive-review")
    public List<Review> ReviewList(@RequestHeader(value = "Authorization") String token){
        String tId = jwtUtil.extractUsername(token.substring(7));
        return teacherDao.studentExamPaperReviewList(tId);
    }
    @GetMapping("/public/terms-and-condition")
    public TandC termsAndCondition(){
        return new TandC();
    }
    @GetMapping("/public/faq")
    public Map<String, String> faq(){
        FAQ faq = new FAQ();
        return faq.generate();
    }
}
