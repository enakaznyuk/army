package com.solvd.army.persistence.impl;

import com.solvd.army.domain.Army;
import com.solvd.army.persistence.ArmyMapper;
import com.solvd.army.persistence.MyBatisConfig;
import com.solvd.army.persistence.SmallArmMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class ArmyMapperImpl implements ArmyMapper {

    @Override
    public List<Army> findAll() {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            ArmyMapper mapper = session.getMapper(ArmyMapper.class);
            return mapper.findAll();
        }
    }

    @Override
    public Optional<Army> findById(Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            ArmyMapper mapper = session.getMapper(ArmyMapper.class);
            return mapper.findById(id);
        }
    }

    @Override
    public void update(Army newMeaning) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            ArmyMapper mapper = session.getMapper(ArmyMapper.class);
            mapper.update(newMeaning);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            ArmyMapper mapper = session.getMapper(ArmyMapper.class);
            mapper.deleteById(id);
        }
    }

    @Override
    public void insert(Army meaning) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            ArmyMapper mapper = session.getMapper(ArmyMapper.class);
            mapper.insert(meaning);
        }
    }
}
