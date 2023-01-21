package com.oems.home.controller;
import com.oems.home.dao.ExamManagerJdbcDao;
import com.oems.home.dao.StudentJdbcDao;
import com.oems.home.model.*;

import com.oems.home.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    StudentJdbcDao studentDao;
    @Autowired
    ExamManagerJdbcDao examDao;

    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("/student/board")
    public HashMap<String, Integer> studentDashBoard(@RequestHeader(value = "Authorization") String token){
        String id = jwtUtil.extractUsername(token.substring(7));
        return studentDao.studentBoardManager(id);
    }
    @GetMapping("/student/my-courses")
    public List<CourseDetails> studentCurrentSubject(@RequestHeader(value = "Authorization") String token){
        String stdId = jwtUtil.extractUsername(token.substring(7));
        return studentDao.allRunningCourseDetails(stdId);
    }
    @GetMapping("/student/departmental-course")
    public List<CourseDetails> allCourseOfThatDepartment(@RequestHeader(value = "Authorization") String token){
        String stdId = jwtUtil.extractUsername(token.substring(7));

        String deptId = studentDao.getDeptId(stdId);

        return studentDao.departmentalCourseSet(deptId);
    }
    @GetMapping("/student/departmental-course/completed")
    public List<CourseDetails> allCompletedCourseOfThatStudent(@RequestHeader(value = "Authorization") String token){
        String stdId = jwtUtil.extractUsername(token.substring(7));
        return studentDao.completedCoursesByStudent(stdId);
    }
    @PostMapping("/student/request-for-courses")
    public List<CourseDetails> reqCourseList(@RequestHeader(value = "Authorization") String token, @RequestBody List<CourseDetails> courses){
        String stdId = jwtUtil.extractUsername(token.substring(7));
        studentDao.requestedCourseAdd(courses, stdId);
        return courses;
    }
    @GetMapping("/student/exams/upcoming")
    public List<QuestionSummery> upComingExamStudent(@RequestHeader(value = "Authorization") String token){
        String stdId = jwtUtil.extractUsername(token.substring(7));
    	return studentDao.upcomingExamForStudent(stdId);
    }
    @GetMapping("/student/exams/previous")
    public List<QuestionSummery> previousExamStudent(@RequestHeader(value = "Authorization") String token){
        String stdId = jwtUtil.extractUsername(token.substring(7));
    	return studentDao.prevExamForStudent(stdId);
    }
    @PostMapping("/student/exams/send-review/{examId}")
    public void RequestReview(@RequestHeader(value = "Authorization") String token, @PathVariable("examId") int examId){
        String stdId = jwtUtil.extractUsername(token.substring(7));
        studentDao.requestForReview(stdId,examId);
    }
    
    @GetMapping("/student/give-post-exam/{questionId}")
    public Optional<QuestionPaper> getQuestion(@PathVariable("questionId") String questionId) {
        // String examDateTime = examDao.getExamDate(questionId);
        // if(examDateTime>presentDateTime) return Optional.empty();
        Optional<QuestionPaper> questionPaper = examDao.get(questionId);
        questionPaper.ifPresent(QuestionPaper::removeAnsStatus);
    	return questionPaper;
    }
    @PostMapping("/student/give-post-exam")
    public void sendAnswer(@RequestHeader(value = "Authorization") String token, @RequestBody AnswerScript answerScript) {
        String stdId = jwtUtil.extractUsername(token.substring(7));
        answerScript.setStdId(stdId);
        studentDao.ReceiveAnswer(answerScript);
    }

    @GetMapping("/get-answer-script/{questionId}")
    public Optional<QuestionPaper> provideAnswerScript(@RequestHeader(value = "Authorization") String token, @PathVariable("questionId") String questionId){
        String stdId = jwtUtil.extractUsername(token.substring(7));
        return examDao.answerScriptCreator(stdId,questionId);
    }
}
