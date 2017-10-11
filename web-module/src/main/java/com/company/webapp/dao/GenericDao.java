package com.company.webapp.dao;

import java.util.List;

public interface GenericDao<T> {

    List<T> selectData(String sqlQuery, Object... queryArg);

    boolean updateData(T object, String sqlQuery);

    boolean deleteData(T object, String sqlQuery);

    boolean insertData(T object, String sqlQuery);
}
