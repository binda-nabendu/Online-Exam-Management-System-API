package com.oems.home.dao;

import com.oems.home.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Component
@Repository
public class ExamManagerJdbcDao implements Dao<QuestionPaper> {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public ExamManagerJdbcDao(JdbcTemplate jdbcTemplate) {
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

    protected final RowMapper<QuestionSummery> questionSummaryMapper = (rs,rn)->{
        QuestionSummery question = new QuestionSummery();
        question.setExamId(rs.getInt("examId"));
        question.setCourseCode(rs.getString("courseCode"));
        question.setTeacherId(rs.getString("teacherId"));
        question.setPercentageValue(rs.getDouble("percentageValue"));
        question.setStartingDateTime(rs.getString("startingDateTime"));
        question.setEndingDateTime(rs.getString("endingDateTime"));
        question.setTotal(rs.getDouble("total"));
        return question;
    };

    @Override
    public Optional<QuestionPaper> get(String questionId) {
        int examId = Integer.parseInt(questionId);
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

    public int getLastExamId() {
        String s = "select max(examId) from examPaper";
        int i=jdbcTemplate.queryForObject(s,Integer.class);
        return i;
    }

    public List<QuestionSummery> returnAllQuestionAccordingToTeacher(String tid) {
        String queryForReturnQuestionHeader = "select * from examPaper " +
                "where teacherId="+tid+" order by startingDateTime";
        return jdbcTemplate.query(queryForReturnQuestionHeader,questionSummaryMapper);
    }

    public Optional<QuestionPaper> answerScriptCreator(String stdId, String examId) {
        Optional<QuestionPaper> questionPaper = get(examId);

        questionPaper.ifPresent(e->{
            List<IndividualQuestion> l=e.getAllIndividualQuestions();
            for(IndividualQuestion iq: l){
                String s1="select optionNo from questionAns where examId="+e.getExamId()
                        +" and questionNo="+iq.getQuestionNo()+" and ansStatus= true";
                String s2="select optionNo from stdAnsScript where stdId="+stdId+
                        " and examId="+e.getExamId()+" and questionNo="+iq.getQuestionNo();
                iq.setCorrectOption(jdbcTemplate.query(s1,(rs,rn)->{
                    return rs.getInt("optionNo");
                }));
                iq.setSelectedOption(jdbcTemplate.query(s2,(rs,rn)->{
                    return rs.getInt("optionNo");
                }));
            }
        });
        return questionPaper;

    }
}
