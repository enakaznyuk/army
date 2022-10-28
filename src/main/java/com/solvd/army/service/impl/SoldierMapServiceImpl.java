package com.solvd.army.service.impl;

import com.solvd.army.domain.exception.EntityNotFoundException;
import com.solvd.army.domain.staff.Soldier;
import com.solvd.army.domain.weapon.SmallArm;
import com.solvd.army.persistence.SmallArmMapper;
import com.solvd.army.persistence.SoldierMapper;
import com.solvd.army.persistence.impl.SmallArmMapperImpl;
import com.solvd.army.persistence.impl.SoldierMapperImpl;
import com.solvd.army.service.SoldiersMapService;

import java.util.List;
import java.util.Optional;

public class SoldierMapServiceImpl implements SoldiersMapService {

    private final SoldierMapper soldierMapper;

    public SoldierMapServiceImpl() {
        this.soldierMapper = new SoldierMapperImpl();
    }

    @Override
    public void update(Soldier newMeaning) {
        soldierMapper.update(newMeaning);
    }

    @Override
    public void deleteById(Long id) {
        soldierMapper.deleteById(id);
    }

    @Override
    public void insert(Soldier meaning) {
        soldierMapper.insert(meaning);
    }

    @Override
    public Soldier findById(Long id) {
        Optional<Soldier> optional = soldierMapper.findById(id);
        return optional.orElseThrow(() -> new EntityNotFoundException("Not found"));
    }

    @Override
    public List<Soldier> findAll() {
        return soldierMapper.findAll();
    }
}
