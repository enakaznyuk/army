package com.solvd.army.service.impl;

import com.solvd.army.domain.Army;
import com.solvd.army.domain.exception.EntityNotFoundException;
import com.solvd.army.domain.weapon.SmallArm;
import com.solvd.army.persistence.ArmyMapper;
import com.solvd.army.persistence.SmallArmMapper;
import com.solvd.army.persistence.impl.ArmyMapperImpl;
import com.solvd.army.persistence.impl.SmallArmMapperImpl;
import com.solvd.army.service.ArmyMapperService;

import java.util.List;
import java.util.Optional;

public class ArmyMapServiceImpl implements ArmyMapperService {

    private final ArmyMapper armyMapper;

    public ArmyMapServiceImpl() {
        this.armyMapper = new ArmyMapperImpl();
    }

    @Override
    public void update(Army newMeaning) {
        armyMapper.update(newMeaning);
    }

    @Override
    public void deleteById(Long id) {
        armyMapper.deleteById(id);
    }

    @Override
    public void insert(Army meaning) {
        armyMapper.insert(meaning);
    }

    @Override
    public Army findById(Long id) {
        Optional<Army> optional = armyMapper.findById(id);
        return optional.orElseThrow(() -> new EntityNotFoundException("Not found"));
    }

    @Override
    public List<Army> findAll() {
        return armyMapper.findAll();
    }
}
