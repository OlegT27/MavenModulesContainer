package com.company.webapp.daoutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public interface GenericDao<T> {

    Logger logger = LoggerFactory.getLogger(GenericDao.class);

    List<T> selectData();

    long updateData(T record);

    long deleteData(T record);

    long createData(T record);

    long getCount();

}
