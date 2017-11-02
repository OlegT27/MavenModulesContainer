package com.company.webapp.daoutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
public interface GenericDao<T> {

    Logger logger = LoggerFactory.getLogger(GenericDao.class);

    List<T> selectData();

    int updateData(T record);

    int deleteData(T record);

    int createData(T record);

    int getCount();

}
