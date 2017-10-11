package com.company.dao.interfaces;

import java.util.List;

public interface GenericDAO<T> {

    boolean addData(T record, String query);

    boolean deleteData(T record, String query);

    List<T> getAllData(String query);

    T getDataById(int key, String query);

    T updateData(T record, String query);


}
