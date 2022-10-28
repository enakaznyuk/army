package com.solvd.army.persistence;

import java.util.List;
import java.util.Optional;

public interface BaseArmyRepositoryMapper<T> {

    List<T> findAll();

    Optional<T> findById(Long id);

    void update(T newMeaning);

    void deleteById(Long id);

    void insert(T meaning);
}
