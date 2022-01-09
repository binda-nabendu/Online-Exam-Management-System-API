package com.oems.home.controller;

import com.oems.home.dao.TeacherJdbcDao;
import com.oems.home.model.CourseDetails;
import com.oems.home.model.Dashboard;
import com.oems.home.model.IndividualQuestion;
import com.oems.home.model.QuestionAnswer;
import com.oems.home.model.QuestionPaper;

import com.oems.home.model.Student;
import com.oems.home.model.Teacher;

import org.springframework.beans.factory.annotation.Autowired;
import com.oems.home.model.Review;

import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TeacherController {
	@Autowired
    TeacherJdbcDao teacherDao;
	
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

    @PostMapping("/exams/question")
    public QuestionPaper QuestionPaper(@RequestBody QuestionPaper questionPaper){

        return questionPaper;
    }

    @GetMapping("/exams/questions/{question-paper-id}")
    public String QuestionPaper(@PathVariable("question-paper-id") int qId){
        return "Your"+qId+" Question is here";
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
        return teacherDao.studentReviewList(tid);
    }
    @GetMapping("/terms-and-condition")
    public String termsAndCondition(){
        return "All terms and condition here";
    }
    @GetMapping("/faq")
    public String faq(){
        return "Your Question and Answer here...";
    }
}
