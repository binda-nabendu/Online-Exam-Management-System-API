package com.oems.home.model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class QuestionPaper {
    private String courseCode;
    private Double percentageValue;
    private Date startingDate;
    private Time startingTime;
    private Date endingDate;
    private Time endingTime;
    private int examId;
    private double total;
    ArrayList<IndividualQuestion> allIndividualQuestions;


}
