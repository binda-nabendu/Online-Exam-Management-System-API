package com.oems.home.model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class QuestionPaper {
    private int examId;
    private String teacherId;
    private String courseCode;
    private Double percentageValue;
    private String startingDateTime;
    private String endingDateTime;
    private int courseSession;
    private double total;
    private List<IndividualQuestion> allIndividualQuestions= new ArrayList<>();

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Double getPercentageValue() {
        return percentageValue;
    }

    public void setPercentageValue(Double percentageValue) {
        this.percentageValue = percentageValue;
    }

    public String getStartingDateTime() {
        return startingDateTime;
    }

    public void setStartingDateTime(String startingDateTime) {
        this.startingDateTime = startingDateTime;
    }

    public String getEndingDateTime() {
        return endingDateTime;
    }

    public void setEndingDateTime(String endingDateTime) {
        this.endingDateTime = endingDateTime;
    }

    public int getCourseSession() {
        return courseSession;
    }

    public void setCourseSession(int courseSession) {
        this.courseSession = courseSession;
    }

    public double getTotal() {
        return total;
    }
    public void calculateTotal() {
        total = 0;
        for (IndividualQuestion question: allIndividualQuestions) {
            total+=question.getMark();
        }
    }
    public void setTotal(double total) {
        this.total = total;
    }

    public List<IndividualQuestion> getAllIndividualQuestions() {
        return allIndividualQuestions;
    }

    public void setAllIndividualQuestions(List<IndividualQuestion> allIndividualQuestions) {
        this.allIndividualQuestions = allIndividualQuestions;
    }
    public void removeAnsStatus(){
        for(IndividualQuestion question : allIndividualQuestions){
            for(QuestionAnswer qa:question.getAllOptions())
                qa.setAnsStatus(false);
        }
    }
}
