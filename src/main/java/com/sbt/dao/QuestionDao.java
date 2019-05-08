package com.sbt.dao;

import com.sbt.entity.Question;
import com.sbt.exceptions.*;

import java.util.List;

public interface QuestionDao extends Dao<Question> {
    List<Question> selectByIdQuizId(int quizId) throws DBSystemException, NoSuchEntityException;
}