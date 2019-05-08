package com.sbt.dao;

import com.sbt.exceptions.*;

import java.util.List;

public interface Dao<T> {
    T selectById(int id) throws DBSystemException, NoSuchEntityException;

    List<T> selectAll() throws DBSystemException, NoSuchEntityException;

    //void insert(T newEntity) throws DBSystemException;

    //void update(T newEntity) throws DBSystemException;

    //void deleteById(int id) throws DBSystemException, NoSuchEntityException;
}