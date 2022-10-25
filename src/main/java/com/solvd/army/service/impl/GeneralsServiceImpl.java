package com.solvd.army.service.impl;

import com.solvd.army.domain.Army;
import com.solvd.army.domain.exception.EntityNotFoundException;
import com.solvd.army.domain.staff.General;
import com.solvd.army.persistence.GeneralsRepository;
import com.solvd.army.persistence.impl.GeneralsDbImpl;
import com.solvd.army.service.GeneralsService;

import java.util.List;
import java.util.Optional;

public class GeneralsServiceImpl implements GeneralsService {

    private final GeneralsRepository generalsRepository;

    public GeneralsServiceImpl() {
        this.generalsRepository = new GeneralsDbImpl();
    }

    @Override
    public void update(Long id, String newMeaning) {
        generalsRepository.update(id, newMeaning);
    }

    @Override
    public void deleteById(Long id) {
        generalsRepository.deleteById(id);
    }

    @Override
    public void insert(General meaning, Army id) {
        generalsRepository.insert(meaning, id);
    }

    @Override
    public General findById(Long id) {
        Optional<General> optionalGeneral = generalsRepository.findById(id);
        return optionalGeneral.orElseThrow(() -> new EntityNotFoundException("Not found"));
    }

    @Override
    public List<General> findAll() {
        return generalsRepository.findAll();
    }
}
