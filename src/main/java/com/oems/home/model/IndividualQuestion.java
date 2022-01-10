package com.oems.home.model;

import org.springframework.stereotype.Component;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Component
public class IndividualQuestion {
    private int questionNo;
    private String question;
    //Blob questionImage;
    private double mark;
    private List<QuestionAnswer> allOptions=new ArrayList<>();
    private List<Integer> selectedOption;
    private List<Integer> correctOption;

    public int getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public List<QuestionAnswer> getAllOptions() {
        return allOptions;
    }

    public void setAllOptions(List<QuestionAnswer> allOptions) {
        this.allOptions = allOptions;
    }

    public List<Integer> getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(List<Integer> selectedOption) {
        this.selectedOption = selectedOption;
    }

    public List<Integer> getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(List<Integer> correctOption) {
        this.correctOption = correctOption;
    }
}
