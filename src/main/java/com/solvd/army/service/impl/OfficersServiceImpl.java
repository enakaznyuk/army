package com.solvd.army.service.impl;

import com.solvd.army.domain.exception.EntityNotFoundException;
import com.solvd.army.domain.staff.General;
import com.solvd.army.domain.staff.Officer;
import com.solvd.army.domain.staff.Soldier;
import com.solvd.army.persistence.OfficersRepository;
import com.solvd.army.persistence.impl.OfficersDbImpl;
import com.solvd.army.service.OfficersService;
import com.solvd.army.service.SmallArmsService;
import com.solvd.army.service.SoldiersService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OfficersServiceImpl implements OfficersService {

    private final OfficersRepository officersRepository;
    private final SmallArmsService smallArmsService;
    private final SoldiersService soldiersService;

    public OfficersServiceImpl() {
        this.officersRepository = new OfficersDbImpl();
        this.smallArmsService = new SmallArmsServiceImpl();
        this.soldiersService = new SoldiersServiceImpl();
    }

    @Override
    public void update(Long id, String newMeaning) {
        officersRepository.update(id, newMeaning);
    }

    @Override
    public void deleteById(Long id) {
        officersRepository.deleteById(id);
    }

    @Override
    public void insert(Officer meaning, Long id) {
        smallArmsService.insert(meaning.getRifle());
        officersRepository.insert(meaning, id);
        if(meaning.getBattalion() != null){
            for(Soldier soldier : meaning.getBattalion()){
                soldiersService.insert(soldier, meaning.getId());
            }
        }
    }

    @Override
    public Officer findById(Long id) {
        Optional<Officer> optionalOfficer = officersRepository.findById(id);
        return optionalOfficer.orElseThrow(() -> new EntityNotFoundException("Not found"));
    }

    @Override
    public List<Officer> findAll() {
        return officersRepository.findAll();
    }
}
