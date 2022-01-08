package com.oems.home.dao;

import com.oems.home.dao.Dao;
import com.oems.home.model.QuestionPaper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Component
//@Repository
public class ExaminationManagerJdbcDao implements Dao<QuestionPaper> {

    @Override
    public List<QuestionPaper> listOfAll() {
        return null;
    }

    @Override
    public void create(QuestionPaper questionPaper) {

    }

    @Override
    public Optional<QuestionPaper> get(String target) {
        return Optional.empty();
    }

    @Override
    public void update(QuestionPaper questionPaper, String target) {

    }

    @Override
    public void delete(String target) {

    }
}
