package com.oems.home.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FAQ {
    Map<String, String> questionAnswer = new HashMap<>();
    public FAQ(){
    }
    public Map<String, String> generate(){
        questionAnswer.put("What is online exam management system? ","Online Exam Management is Quiz & Education Results Management system. You can create and manage the unlimited exams for students and publish exam results. Student can give exam according to pre-set time by the professor. Teacher can manage students and assign roll numbers to each student.");
        questionAnswer.put("What are the type of Exam Management System? ","Multiple choice exams, Pdf based, text based. Multiple choice questions usually include a phrase or stem followed by three to five options. 1. Problem or case-based exams, 2.Oral exams, 3.Open-book but very secure and strict and take-home exams. 4.Essay exams, but provide feel in on campus exam.");
        questionAnswer.put("Is online Examination batter then offline Examination?", "Online examinations are comparatively manageable than offline exams as a dedicated team handles the entire process, even providing a secure connection and webcam. These facilities ensure that teachers have to be less concerned about fraudulent practices during an online examination.");
        questionAnswer.put("What should I bring with me to my exam?","This depends on your exam. Your educational institution will provide you with these details ahead of exam day.");
        questionAnswer.put("How easy it is to use?","It is very easy to use and very important for the digital world.");
        return questionAnswer;
    }
}
