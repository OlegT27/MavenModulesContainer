package com.company.webapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public interface GenericDao<T> {

    Logger logger = LoggerFactory.getLogger(GenericDao.class);

    List<T> selectData();

    void updateData(T record);

    void deleteData(T record);

    void createData(T record);

    long getCount();

}
