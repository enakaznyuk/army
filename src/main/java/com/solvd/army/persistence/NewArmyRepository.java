package com.solvd.army.persistence;

import java.util.List;
import java.util.Optional;

public interface NewArmyRepository<T> {

    List<T> findAll();

    Optional<T> findById(Long id);

    void update(Long id, String newMeaning);

    void deleteById(Long id);

    void insert(T meaning, Long id);

}
