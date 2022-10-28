package com.solvd.army.service.impl;

import com.solvd.army.domain.exception.EntityNotFoundException;
import com.solvd.army.domain.staff.General;
import com.solvd.army.domain.weapon.SmallArm;
import com.solvd.army.persistence.GeneralMapper;
import com.solvd.army.persistence.SmallArmMapper;
import com.solvd.army.persistence.impl.GeneralMapperImpl;
import com.solvd.army.persistence.impl.SmallArmMapperImpl;
import com.solvd.army.service.GeneralMapService;

import java.util.List;
import java.util.Optional;

public class GeneralMapServiceImpl implements GeneralMapService {

    private final GeneralMapper generalMapper;

    public GeneralMapServiceImpl() {
        this.generalMapper = new GeneralMapperImpl();
    }

    @Override
    public void update(General newMeaning) {
        generalMapper.update(newMeaning);
    }

    @Override
    public void deleteById(Long id) {
        generalMapper.deleteById(id);
    }

    @Override
    public void insert(General meaning) {
        generalMapper.insert(meaning);
    }

    @Override
    public General findById(Long id) {
        Optional<General> optional = generalMapper.findById(id);
        return optional.orElseThrow(() -> new EntityNotFoundException("Not found"));
    }

    @Override
    public List<General> findAll() {
        return generalMapper.findAll();
    }
}
