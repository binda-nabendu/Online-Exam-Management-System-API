package com.oems.home.model;

import java.sql.Blob;
import java.util.ArrayList;

public class IndividualQuestion {
    private int examId;
    private String question;
    private Blob questionImage;
    private double mark;
    ArrayList<QuestionAnswer> allOptions;
}
