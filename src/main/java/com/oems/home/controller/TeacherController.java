package com.oems.home.controller;

import com.oems.home.model.IndividualQuestion;
import com.oems.home.model.QuestionAnswer;
import com.oems.home.model.QuestionPaper;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;

@RestController
public class TeacherController {
    @GetMapping("/teacher-board/{id}")
    public String TeacherBoardManager(@PathVariable("id") String id){

        return "Teacher board manager";
    }
    @GetMapping("/teachers/all-teachers")
    public String allTeachers(){

        return "All teacher list";
    }
    @GetMapping("/all-courses")
    public String allCoursesOfAllDept(){

        return "All Courses of all department";
    }
    @GetMapping("/courses/{teacherId}")
    public String allCurrentCrsOfThatTeacher(@PathVariable("teacherId")String tId){

        return "All Current Courses of That teacher";
    }
    @GetMapping("/courses/my-students/{course-code}")
    public String allStudentOfThatCourse(@PathVariable("course-code")String courseCode){

        return "All student list of that Courses of That teacher";
    }

    @PostMapping("/exams/question")
    public QuestionPaper QuestionPaper(@RequestBody QuestionPaper questionPaper){
//        questionPaper.setExamId(121);
//        questionPaper.setTeacherId("2323");
//        questionPaper.setCourseCode("CSE121");
//        questionPaper.setPercentageValue(0.5);
//        questionPaper.setStartingDateTime("2020-03-04 18:12:30");
//
//        questionPaper.setEndingDateTime("2020-03-04 18:12:30");
//        questionPaper.setCourseSession(2);
//        questionPaper.setTotal(5);
//        IndividualQuestion iq = new IndividualQuestion();
//        iq.question="what is your name";
//        iq.questionNo=1;
//        iq.mark =1;
//        iq.allOptions.add(new QuestionAnswer(1,"erio",true));
//        iq.allOptions.add(new QuestionAnswer(2,"dfdf",false));
//        iq.allOptions.add(new QuestionAnswer(3,"fdfer",false));
//        questionPaper.allIndividualQuestions.add(iq);
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
    @GetMapping("/exams/receive-review/{courseId}/{examId}")
    public String RequestReview(@PathVariable("courseId")String id ,@PathVariable("examId") int examId){
        return "Review Request Send..";
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
