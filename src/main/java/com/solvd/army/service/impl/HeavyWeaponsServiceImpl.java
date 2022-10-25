package com.solvd.army.service.impl;

import com.solvd.army.domain.exception.EntityNotFoundException;
import com.solvd.army.domain.weapon.HeavyWeapon;
import com.solvd.army.persistence.HeavyWeaponsRepository;
import com.solvd.army.persistence.impl.HeavyWeaponsDbImpl;
import com.solvd.army.service.HeavyWeaponsService;

import java.util.List;
import java.util.Optional;

public class HeavyWeaponsServiceImpl implements HeavyWeaponsService {

    private final HeavyWeaponsRepository heavyWeaponsRepository;

    public HeavyWeaponsServiceImpl(){
        this.heavyWeaponsRepository = new HeavyWeaponsDbImpl();
    }

    @Override
    public void update(Long id, String newMeaning) {
        heavyWeaponsRepository.update(id, newMeaning);
    }

    @Override
    public void deleteById(Long id) {
        heavyWeaponsRepository.deleteById(id);
    }

    @Override
    public void insert(HeavyWeapon meaning) {
        heavyWeaponsRepository.insert(meaning);
    }

    @Override
    public HeavyWeapon findById(Long id) {
        Optional<HeavyWeapon> optional = heavyWeaponsRepository.findById(id);
        return optional.orElseThrow(() -> new EntityNotFoundException("Not found"));
    }

    @Override
    public List<HeavyWeapon> findAll() {
        return heavyWeaponsRepository.findAll();
    }
}
