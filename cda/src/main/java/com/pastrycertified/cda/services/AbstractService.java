package com.pastrycertified.cda.services;

import java.util.List;

public interface AbstractService<T>{

    Integer save(T dto);

    List<T> findAll();

    T findById(Integer id);

    Integer updateByid(Integer id, T dto);

    void delete(Integer id);
}
