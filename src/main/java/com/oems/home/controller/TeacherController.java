package com.oems.home.controller;

import com.oems.home.ExamManagementSystemApplication;
import com.oems.home.dao.ExaminationManagerJdbcDao;
import com.oems.home.dao.TeacherJdbcDao;
import com.oems.home.model.CourseDetails;
import com.oems.home.model.Dashboard;
import com.oems.home.model.QuestionPaper;

import com.oems.home.model.Student;
import com.oems.home.model.Teacher;

import org.springframework.beans.factory.annotation.Autowired;
import com.oems.home.model.Review;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TeacherController {
	@Autowired
    TeacherJdbcDao teacherDao;
    @Autowired
    ExaminationManagerJdbcDao examDao;
	
    @GetMapping("/teacher-board/{id}")
    public Dashboard TeacherBoardManager(@PathVariable("id") String id){
    	return teacherDao.teacherBoardManager(id);
    }
    @GetMapping("/teachers/all-teachers")
    public List<Teacher> allTeachers(){
    	return teacherDao.listOfAllTeacher();
    }
    @GetMapping("/all-courses")
    public List<CourseDetails> allCoursesOfAllDept(){
    	return teacherDao.listOfAllCoursesOfAllDept();
    }
    @GetMapping("/courses/{teacherId}")
    public List<CourseDetails> allCurrentCrsOfThatTeacher(@PathVariable("teacherId")String tId){
    	return teacherDao.currCoursesOfTeacher(tId);
    }
    @GetMapping("/courses/my-students/{course-code}")
    public List<Student> allStudentOfThatCourse(@PathVariable("course-code")String courseCode){
    	return teacherDao.listOfAllStudentOfThatCourse(courseCode);
    }
    // !---------- must be full details of exam paper needed-----------!
    @PostMapping("/exams/question")
    public QuestionPaper QuestionPaperHandler(@RequestBody QuestionPaper questionPaper){
        questionPaper.setExamId(examDao.getLastExamId()+1);
        examDao.create(questionPaper);
        return questionPaper;
    }

    @GetMapping("/exams/questions/{question-paper-id}")
    public Optional<QuestionPaper> QuestionPaperHandler(@PathVariable("question-paper-id") String qId){
        return examDao.get(qId);
    }

    @GetMapping("/exams/all-exams/{teacher-id}")
    public String getAllQuestionThatTeacherMade(@PathVariable("teacher-id") String tid){
        return "Here all of your question paper that teacher made can see";
    }
    @GetMapping("/exams/all-result/{teacher-id}")
    public String getAllResultThatTeacherPublished(@PathVariable("teacher-id") String tid){
        return "Here all of your subject result you can see";
    }
    @GetMapping("/exams/receive-review/{teacher-id}")
    public List<Review> ReviewList(@PathVariable("teacher-id") String tid){
        return teacherDao.studentExamPaperReviewList(tid);
    }
    @GetMapping("/terms-and-condition")
    public String termsAndCondition(){
        return "All terms and condition here done by afzal";
    }
    @GetMapping("/faq")
    public String faq(){
        return "Your Question and Answer here... done by afzal";
    }
}
