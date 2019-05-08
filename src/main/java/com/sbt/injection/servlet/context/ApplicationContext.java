package com.sbt.injection.servlet.context;

public interface ApplicationContext {
    void init(String xmlFile);

    Object getBean(String name);

    <T> T getBean(String name, Class<T> clazz);
}
