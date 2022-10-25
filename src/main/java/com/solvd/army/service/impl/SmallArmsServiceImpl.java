package com.solvd.army.service.impl;

import com.solvd.army.domain.exception.EntityNotFoundException;
import com.solvd.army.domain.weapon.SmallArm;
import com.solvd.army.persistence.SmallArmsRepository;
import com.solvd.army.persistence.impl.SmallArmsDbImpl;
import com.solvd.army.service.SmallArmsService;

import java.util.List;
import java.util.Optional;

public class SmallArmsServiceImpl implements SmallArmsService {

    private final SmallArmsRepository smallArmsRepository;

    public SmallArmsServiceImpl() {
        this.smallArmsRepository = new SmallArmsDbImpl();
    }

    @Override
    public void update(Long id, String newMeaning) {
        smallArmsRepository.update(id, newMeaning);
    }

    @Override
    public void deleteById(Long id) {
        smallArmsRepository.deleteById(id);
    }

    @Override
    public void insert(SmallArm meaning) {
        smallArmsRepository.insert(meaning);
    }

    @Override
    public SmallArm findById(Long id) {
        Optional<SmallArm> optional = smallArmsRepository.findById(id);
        return optional.orElseThrow(() -> new EntityNotFoundException("Not found"));
    }

    @Override
    public List<SmallArm> findAll() {
        return smallArmsRepository.findAll();
    }
}
