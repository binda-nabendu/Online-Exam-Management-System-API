package com.oems.home.controller;

import com.oems.home.dao.ExamManagerJdbcDao;
import com.oems.home.dao.TeacherJdbcDao;
import com.oems.home.model.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TeacherController {
	@Autowired
    TeacherJdbcDao teacherDao;
    @Autowired
    ExamManagerJdbcDao examDao;
	
    @GetMapping("/teacher/board/{id}")
    public Dashboard TeacherBoardManager(@PathVariable("id") String tId){
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
    @GetMapping("/teacher/courses/{teacherId}")
    public List<CourseDetails> allCurrentCrsOfThatTeacher(@PathVariable("teacherId")String tId){
    	return teacherDao.currCoursesOfTeacher(tId);
    }
    @GetMapping("/teacher/courses/my-students/{course-code}")
    public List<Student> allStudentOfThatCourse(@PathVariable("course-code")String courseCode,String deptId){
    	return teacherDao.listOfAllStudentOfThatCourse(courseCode, deptId);
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

    @GetMapping("/teacher/all-questions/{teacher-id}")
    public List<QuestionSummery> getAllQuestionThatTeacherMade(@PathVariable("teacher-id") String tId){
        return examDao.returnAllQuestionAccordingToTeacher(tId);
    }
    //t
    @GetMapping("/teacher/all-pending-result/{teacher-id}")
    public List<QuestionSummery> allPendingResult(@PathVariable("teacher-id") String tId){
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
    
    
    
    @GetMapping("/teacher/receive-review/{teacher-id}")
    public List<Review> ReviewList(@PathVariable("teacher-id") String tid){
        return teacherDao.studentExamPaperReviewList(tid);
    }
    @GetMapping("/public/terms-and-condition")
    public String termsAndCondition(){
        return new FAQAndTAndC().termsAndCondition;
    }
    @GetMapping("/public/faq")
    public String faq(){
        return new FAQAndTAndC().faq;
    }
}
