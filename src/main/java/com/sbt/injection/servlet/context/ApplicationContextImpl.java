package com.sbt.injection.servlet.context;

import com.sbt.dao.QuizDaoMock;
import com.sbt.transactionManager.TransactionManagerMock;

public class ApplicationContextImpl implements ApplicationContext {
    @Override
    public void init(String xmlFile) {
        /*NOP*/
    }

    @Override
    public Object getBean(String name) {
        return getBean(name, Object.class);
    }

    @Override
    public <T> T getBean(String name, Class<T> clazz) {
        if ("quizDao".equals(name))
            return (T) new QuizDaoMock();

        if ("txManager".equals(name))
            return (T) new TransactionManagerMock();

        throw new IllegalArgumentException("No entry for name '" + name + "'");
    }
}
