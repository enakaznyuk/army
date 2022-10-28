package com.solvd.army.persistence.impl;

import com.solvd.army.domain.staff.Soldier;
import com.solvd.army.persistence.MyBatisConfig;
import com.solvd.army.persistence.SmallArmMapper;
import com.solvd.army.persistence.SoldierMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class SoldierMapperImpl implements SoldierMapper {

    @Override
    public List<Soldier> findAll() {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            SoldierMapper mapper = session.getMapper(SoldierMapper.class);
            return mapper.findAll();
        }
    }

    @Override
    public Optional<Soldier> findById(Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            SoldierMapper mapper = session.getMapper(SoldierMapper.class);
            return mapper.findById(id);
        }
    }

    @Override
    public void update(Soldier newMeaning) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            SoldierMapper mapper = session.getMapper(SoldierMapper.class);
            mapper.update(newMeaning);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            SoldierMapper mapper = session.getMapper(SoldierMapper.class);
            mapper.deleteById(id);
        }
    }

    @Override
    public void insert(Soldier meaning) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            SoldierMapper mapper = session.getMapper(SoldierMapper.class);
            mapper.insert(meaning);
        }
    }
}
