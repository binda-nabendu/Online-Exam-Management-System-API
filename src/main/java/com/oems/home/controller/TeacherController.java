package com.oems.home.controller;

import com.oems.home.model.QuestionPaper;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/exams/questions/{question-paper}")
    public String QuestionPaper(@PathVariable("question-paper") QuestionPaper questionPaper){
        return "Your Question will set successful";
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
