package com.solvd.army.service.impl;

import com.solvd.army.domain.Army;
import com.solvd.army.domain.exception.EntityNotFoundException;
import com.solvd.army.domain.staff.General;
import com.solvd.army.domain.staff.Officer;
import com.solvd.army.domain.staff.Soldier;
import com.solvd.army.persistence.GeneralsRepository;
import com.solvd.army.persistence.impl.GeneralsDbImpl;
import com.solvd.army.service.GeneralsService;
import com.solvd.army.service.OfficersService;
import com.solvd.army.service.SmallArmsService;

import java.util.List;
import java.util.Optional;

public class GeneralsServiceImpl implements GeneralsService {

    private final GeneralsRepository generalsRepository;
    private final SmallArmsService smallArmsService;
    private final OfficersService officersService;

    public GeneralsServiceImpl() {
        this.generalsRepository = new GeneralsDbImpl();
        this.smallArmsService = new SmallArmsServiceImpl();
        this.officersService = new OfficersServiceImpl();
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
    public void insert(General meaning, Long id) {
        smallArmsService.insert(meaning.getPistol());
        generalsRepository.insert(meaning, id);
        if(meaning.getDivision() != null){
            for(Officer officer : meaning.getDivision()){
                officersService.insert(officer, meaning.getId());
            }
        }
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
