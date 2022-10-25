package com.solvd.army.service.impl;

import com.solvd.army.domain.exception.EntityNotFoundException;
import com.solvd.army.domain.staff.General;
import com.solvd.army.domain.staff.Officer;
import com.solvd.army.persistence.OfficersRepository;
import com.solvd.army.persistence.impl.OfficersDbImpl;
import com.solvd.army.service.OfficersService;

import java.util.List;
import java.util.Optional;

public class OfficersServiceImpl implements OfficersService {

    private final OfficersRepository officersRepository;

    public OfficersServiceImpl() {
        this.officersRepository = new OfficersDbImpl();
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
    public void insert(Officer meaning, General id) {
        officersRepository.insert(meaning, id);
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
