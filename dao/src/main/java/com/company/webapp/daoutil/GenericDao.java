package com.company.webapp.daoutil;

import java.util.List;

public interface GenericDao<T> {

    List<T> selectData();

    int updateData(T record);

    int deleteData(T record);

    int createData(T record);

    int getCount();

}
