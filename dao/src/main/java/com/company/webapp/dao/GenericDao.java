package com.company.webapp.dao;

import java.util.List;

public interface GenericDao<T> {

    List<T> selectData(String sqlQuery, Object... queryArg);

    boolean updateData(String sqlQuery, Object... queryArg);

}
