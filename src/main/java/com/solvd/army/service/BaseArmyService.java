package com.solvd.army.service;

import java.util.List;

public interface BaseArmyService<T> {

    void update(Long id, String newMeaning);

    void deleteById(Long id);

    void insert(T meaning);

    T findById(Long id);

    List<T> findAll();
}
