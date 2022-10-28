package com.solvd.army.service;

import java.util.List;

public interface BaseArmyMapService<T> {
    void update(T newMeaning);

    void deleteById(Long id);

    void insert(T meaning);

    T findById(Long id);

    List<T> findAll();
}
