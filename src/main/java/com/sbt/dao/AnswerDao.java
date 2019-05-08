package com.sbt.dao;

import com.sbt.entity.Answer;
import com.sbt.exceptions.*;

import java.util.List;

public interface AnswerDao extends Dao<Answer> {
    List<Answer> selectByIdQuestionId(int questionId) throws DBSystemException, NoSuchEntityException;
}
