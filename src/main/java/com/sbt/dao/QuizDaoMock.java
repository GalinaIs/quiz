package com.sbt.dao;

import com.sbt.entity.Quiz;
import com.sbt.exceptions.*;

import java.util.*;

public class QuizDaoMock implements QuizDao {
    private Map<Integer, Quiz> memory = new HashMap<>();

    public QuizDaoMock() {
        this.memory.put(1, new Quiz(1, "Question #1", "10%3==3?", null, "10%3==1"));
        this.memory.put(2, new Quiz(2, "Question #2", "1/1==0?", null, "1/1==0"));
        this.memory.put(3, new Quiz(1, "Question #3", "Float.NaN==Float.NaN?", null,
                "Float.NaN!=Float.NaN"));
    }

    @Override
    public Quiz selectById(int id) throws DBSystemException, NoSuchEntityException {
        return memory.get(id);
    }

    @Override
    public List<Quiz> selectAll() throws DBSystemException, NoSuchEntityException {
        return new ArrayList<>(memory.values());
    }
}
