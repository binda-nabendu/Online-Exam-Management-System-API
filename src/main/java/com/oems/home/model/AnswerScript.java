package com.oems.home.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnswerScript {
    private String stdId;
    private int examId;
    private List<QuestionOptionPair> allQuestionAnswer = new ArrayList<>();

    public String getStdId() {
        return stdId;
    }

    public void setStdId(String stdId) {
        this.stdId = stdId;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public List<QuestionOptionPair> getAllQuestionAnswer() {
        return allQuestionAnswer;
    }

    public void setAllQuestionAnswer(ArrayList<QuestionOptionPair> allQuestionAnswer) {
        this.allQuestionAnswer = allQuestionAnswer;
    }
}
