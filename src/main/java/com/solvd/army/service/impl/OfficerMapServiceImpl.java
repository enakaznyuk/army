package com.solvd.army.service.impl;

import com.solvd.army.domain.exception.EntityNotFoundException;
import com.solvd.army.domain.staff.Officer;
import com.solvd.army.domain.weapon.SmallArm;
import com.solvd.army.persistence.OfficerMapper;
import com.solvd.army.persistence.SmallArmMapper;
import com.solvd.army.persistence.impl.OfficerMapperImpl;
import com.solvd.army.persistence.impl.SmallArmMapperImpl;
import com.solvd.army.service.OfficerMapService;

import java.util.List;
import java.util.Optional;

public class OfficerMapServiceImpl implements OfficerMapService {

    private final OfficerMapper officerMapper;

    public OfficerMapServiceImpl() {
        this.officerMapper = new OfficerMapperImpl();
    }

    @Override
    public void update(Officer newMeaning) {
        officerMapper.update(newMeaning);
    }

    @Override
    public void deleteById(Long id) {
        officerMapper.deleteById(id);
    }

    @Override
    public void insert(Officer meaning) {
        officerMapper.insert(meaning);
    }

    @Override
    public Officer findById(Long id) {
        Optional<Officer> optional = officerMapper.findById(id);
        return optional.orElseThrow(() -> new EntityNotFoundException("Not found"));
    }

    @Override
    public List<Officer> findAll() {
        return officerMapper.findAll();
    }
}
