package com.ggw.travel.dao;

import java.util.List;

public interface BaseMapper<T, K> {
    void save(T t);
    void update(T t);
    void delete(K k);
    List<T> findAll();
    T findById(K k);
}
