package com.oems.home.dao;

import com.oems.home.model.IndividualQuestion;
import com.oems.home.model.QuestionAnswer;
import com.oems.home.model.QuestionPaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Component
@Repository
public class ExaminationManagerJdbcDao implements Dao<QuestionPaper> {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public ExaminationManagerJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<QuestionPaper> listOfAll() {
        return null;
    }

    @Override
    public void create(QuestionPaper questionPaper) {

        questionPaper.calculateTotal();

        String sqlQueryForExamPaper = "insert into examPaper" +
                "(examId, courseCode, teacherId, percentageValue, startingDateTime, endingDateTime, courseSession, total)" +
                "values("+questionPaper.getExamId()+","+questionPaper.getCourseCode()+","+questionPaper.getTeacherId()+","+
                    questionPaper.getPercentageValue()+","+questionPaper.getStartingDateTime()+","+questionPaper.getEndingDateTime()+","+
                    questionPaper.getCourseSession()+","+questionPaper.getTotal()+")";
        int addingStatus = jdbcTemplate.update(sqlQueryForExamPaper);
        if(addingStatus>0){
            addAllIndividualQuestions(questionPaper.getExamId(), questionPaper.getAllIndividualQuestions());
        }
    }

    private void addAllIndividualQuestions(int examId, List<IndividualQuestion> allIndividualQuestions) {
        for (IndividualQuestion question : allIndividualQuestions) {
            String sqlQueryForIndividualQuestions = "insert into question" +
                    "(examId, questionNo, question, mark)" +
                    "values("+examId+","+question.getQuestionNo()+","+question.getQuestion()+","+question.getMark()+")";
            int status = jdbcTemplate.update(sqlQueryForIndividualQuestions);
            if(status>0){
                addAllOptionOfThatQuestion(examId, question.getQuestionNo(), question.getAllOptions());
            }
        }

    }

    private void addAllOptionOfThatQuestion(int examId, int questionNo, List<QuestionAnswer> allOptions) {
        for (QuestionAnswer option:allOptions) {
            String sqlQueryFoAddAllOption = "insert into questionAns" +
                    "(examId,questionNo,optionNo,optionValue,ansStatus)" +
                    "values("+examId+","+questionNo+","+option.getOptionNo()+","+option.getOptionValue()+","+option.isAnsStatus()+")";
            int optionAddingStatus = jdbcTemplate.update(sqlQueryFoAddAllOption);
        }
    }

    @Override
    public Optional<QuestionPaper> get(String questionId) {
        long examId = Long.parseLong(questionId);
        String queryForQuestionHeader = "select * from examPaper where examId="+examId;
        QuestionPaper finalQuestion = null;
        try {
            finalQuestion = jdbcTemplate.queryForObject(queryForQuestionHeader, (rs, rowNumber) -> {
                QuestionPaper questionPaper=new QuestionPaper();
                questionPaper.setExamId(rs.getInt("examId"));
                questionPaper.setCourseCode(rs.getString("courseCode"));
                questionPaper.setTeacherId(rs.getString("teacherId"));
                questionPaper.setPercentageValue(rs.getDouble("percentageValue"));//may create problem
                questionPaper.setStartingDateTime(rs.getString("startingDateTime"));//may create problem
                questionPaper.setEndingDateTime(rs.getString("endingDateTime"));
                questionPaper.setCourseSession(rs.getInt("courseSession"));
                questionPaper.setTotal(rs.getDouble("total"));
                return questionPaper;
            });
            if(finalQuestion != null){
                finalQuestion.setAllIndividualQuestions(getAllQuestions(examId));
                for (IndividualQuestion question:finalQuestion.getAllIndividualQuestions()) {
                    //todo continue
                    String queryForRetrieveOption = "select * from questionAns where examId="+finalQuestion.getExamId()+" and questionNo="+question.getQuestionNo();
                    question.setAllOptions(jdbcTemplate.query(queryForRetrieveOption,(rss,rnn)->{
                        QuestionAnswer answer = new QuestionAnswer();
                        answer.setOptionNo(rss.getInt("optionNo"));
                        answer.setOptionValue(rss.getString("optionValue"));
                        answer.setAnsStatus(rss.getBoolean("ansStatus"));
                        return answer;
                    }));
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(finalQuestion);
    }

    private List<IndividualQuestion> getAllQuestions(long examId) {
        String queryForQuestions = "select * from question where examId="+examId;
        return jdbcTemplate.query(queryForQuestions,(rp,rn)->{
            IndividualQuestion individualQuestion = new IndividualQuestion();
            individualQuestion.setQuestionNo(rp.getInt("questionNo"));
            individualQuestion.setQuestion(rp.getString("question"));
            individualQuestion.setMark(rp.getDouble("mark"));
            return individualQuestion;
        });
    }

    @Override
    public void update(QuestionPaper questionPaper, String target) {

    }

    @Override
    public void delete(String target) {

    }
}
