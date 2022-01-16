package com.oems.home.model;

import org.springframework.stereotype.Component;

@Component
public class QuestionOptionPair {
    int questionNo;
    int optionNo;

    public int getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }

    public int getOptionNo() {
        return optionNo;
    }

    public void setOptionNo(int optionNo) {
        this.optionNo = optionNo;
    }
}
