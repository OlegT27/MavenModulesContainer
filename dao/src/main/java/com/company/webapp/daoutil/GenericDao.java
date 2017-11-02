package com.company.webapp.daoutil;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.REQUIRED)
public interface GenericDao<T> {

    List<T> selectData();

    int updateData(T record);

    int deleteData(T record);

    int createData(T record);

    int getCount();

}
