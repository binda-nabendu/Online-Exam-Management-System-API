package com.oems.home.controller;
import com.oems.home.dao.ExamManagerJdbcDao;
import com.oems.home.dao.StudentJdbcDao;
import com.oems.home.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    StudentJdbcDao studentDao;
    @Autowired
    ExamManagerJdbcDao examDao;

    @GetMapping("/student/board/{id}")
    public Dashboard studentDashBoard(@PathVariable("id") String id){

        return studentDao.studentBoardManager(id);
    }
    @PostMapping("/student/request-for-courses")
    public void reqCourseList(List<CourseDetails> courses, String stdId){
        studentDao.requestedCourseAdd(courses, stdId);
    }
    @GetMapping("/student/my-courses/{studentId}")
    public List<CourseDetails> studentCurrentSubject(@PathVariable("studentId") String stdId){
        return studentDao.allRunningCourseDetails(stdId);
    }
    @GetMapping("/student/departmental-course/{deptId}")
    public List<CourseDetails> allCourseOfThatDepartment(@PathVariable("deptId") String deptId){
        return studentDao.departmentalCourseSet(deptId);
    }
    @GetMapping("/student/departmental-course/completed/{studentId}")
    public List<CourseDetails> allCompletedCourseOfThatStudent(@PathVariable("studentId") String stdId){
        return studentDao.completedCoursesByStudent(stdId);
    }
    @GetMapping("/student/exams/upcoming/{studentId}")
    public List<QuestionSummery> upComingExamStudent(@PathVariable("studentId") String stdId){
    	return studentDao.upcomingExamForStudent(stdId);
    }
    @GetMapping("/student/exams/previous/{studentId}")
    public List<QuestionSummery> previousExamStudent(@PathVariable("studentId") String stdId){
    	return studentDao.prevExamForStudent(stdId);
    }
    @PostMapping("/student/exams/send-review/")
    public void RequestReview(String stdId ,int examId){
        studentDao.requestForReview(stdId,examId);
    }
    
    @GetMapping("/student/give-post-exam/")
    public Optional<QuestionPaper> getQuestion(String questionId) {
        Optional<QuestionPaper> questionPaper = examDao.get(questionId);
        questionPaper.ifPresent(QuestionPaper::removeAnsStatus);
    	return questionPaper;
    }
    @PostMapping("/student/give-post-exam/")
    public void sendAnswer(AnswerScript answerScript) {
        studentDao.ReceiveAnswer(answerScript);
    }

    @GetMapping("/get-answer-script/")
    public Optional<QuestionPaper> provideAnswerScript(String stdId, String examId){
        return examDao.answerScriptCreator(stdId,examId);
    }
}
