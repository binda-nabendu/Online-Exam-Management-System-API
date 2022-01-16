package com.oems.home.model;

import org.springframework.stereotype.Component;

@Component
public class QuestionAnswer {
    private int optionNo;
    private String optionValue;
    private boolean ansStatus=false;

    public int getOptionNo() {
        return optionNo;
    }

    public void setOptionNo(int optionNo) {
        this.optionNo = optionNo;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    public boolean isAnsStatus() {
        return ansStatus;
    }

    public void setAnsStatus(boolean ansStatus) {
        this.ansStatus = ansStatus;
    }
}
