package com.solvd.army.service.impl;

import com.solvd.army.domain.Army;
import com.solvd.army.domain.exception.EntityNotFoundException;
import com.solvd.army.persistence.ArmyRepository;
import com.solvd.army.persistence.impl.ArmyDbImpl;
import com.solvd.army.service.ArmyService;
import com.solvd.army.service.GeneralsService;

import java.util.List;
import java.util.Optional;

public class ArmyServiceImpl implements ArmyService {

    private final ArmyRepository armyRepository;
    private final GeneralsService generalsService;

    public ArmyServiceImpl() {
        this.armyRepository = new ArmyDbImpl();
        this.generalsService = new GeneralsServiceImpl();
    }

    @Override
    public void update(Long id, String newMeaning) {
        armyRepository.update(id, newMeaning);
    }

    @Override
    public void deleteById(Long id) {
        armyRepository.deleteById(id);
    }

    @Override
    public void insert(Army meaning) {
        armyRepository.insert(meaning);
        generalsService.insert(meaning.getGeneral(), meaning.getId());
    }

    @Override
    public Army findById(Long id) {
        Optional<Army> optional = armyRepository.findById(id);
        return optional.orElseThrow(() -> new EntityNotFoundException("Not found"));
    }

    @Override
    public List<Army> findAll() {
        return armyRepository.findAll();
    }
}
