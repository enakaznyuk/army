package com.solvd.army.service.impl;

import com.solvd.army.domain.exception.EntityNotFoundException;
import com.solvd.army.domain.staff.Officer;
import com.solvd.army.domain.staff.Soldier;
import com.solvd.army.persistence.SoldiersRepository;
import com.solvd.army.persistence.impl.SoldiersDbImpl;
import com.solvd.army.service.SoldiersService;

import java.util.List;
import java.util.Optional;

public class SoldiersServiceImpl implements SoldiersService {

    private final SoldiersRepository soldiersRepository;

    public SoldiersServiceImpl() {
        this.soldiersRepository = new SoldiersDbImpl();
    }

    @Override
    public void update(Long id, String newMeaning) {
        soldiersRepository.update(id, newMeaning);
    }

    @Override
    public void deleteById(Long id) {
        soldiersRepository.deleteById(id);
    }

    @Override
    public void insert(Soldier meaning, Officer id) {
        soldiersRepository.insert(meaning, id);
    }

    @Override
    public Soldier findById(Long id) {
        Optional<Soldier> optionalSoldier = soldiersRepository.findById(id);
        return optionalSoldier.orElseThrow(() -> new EntityNotFoundException("Not found"));
    }

    @Override
    public List<Soldier> findAll() {
        return soldiersRepository.findAll();
    }
}
