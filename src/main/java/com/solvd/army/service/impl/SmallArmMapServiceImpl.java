package com.solvd.army.service.impl;

import com.solvd.army.domain.Army;
import com.solvd.army.domain.exception.EntityNotFoundException;
import com.solvd.army.domain.weapon.SmallArm;
import com.solvd.army.persistence.SmallArmMapper;
import com.solvd.army.persistence.impl.SmallArmMapperImpl;
import com.solvd.army.service.SmallArmMapService;

import java.util.List;
import java.util.Optional;

public class SmallArmMapServiceImpl implements SmallArmMapService {

    private final SmallArmMapper smallArmMapper;

    public SmallArmMapServiceImpl() {
        this.smallArmMapper = new SmallArmMapperImpl();
    }

    @Override
    public void update(SmallArm newMeaning) {
        smallArmMapper.update(newMeaning);
    }

    @Override
    public void deleteById(Long id) {
        smallArmMapper.deleteById(id);
    }

    @Override
    public void insert(SmallArm meaning) {
        smallArmMapper.insert(meaning);
    }

    @Override
    public SmallArm findById(Long id) {
        Optional<SmallArm> optional = smallArmMapper.findById(id);
        return optional.orElseThrow(() -> new EntityNotFoundException("Not found"));
    }

    @Override
    public List<SmallArm> findAll() {
        return smallArmMapper.findAll();
    }
}
