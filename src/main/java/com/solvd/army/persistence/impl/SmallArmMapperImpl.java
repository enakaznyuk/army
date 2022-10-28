package com.solvd.army.persistence.impl;

import com.solvd.army.domain.weapon.SmallArm;
import com.solvd.army.persistence.BaseArmyRepositoryMapper;
import com.solvd.army.persistence.MyBatisConfig;
import com.solvd.army.persistence.SmallArmMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class SmallArmMapperImpl implements SmallArmMapper {

    @Override
    public List<SmallArm> findAll() {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            SmallArmMapper mapper = session.getMapper(SmallArmMapper.class);
            return mapper.findAll();
        }
    }

    @Override
    public Optional<SmallArm> findById(Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            SmallArmMapper mapper = session.getMapper(SmallArmMapper.class);
            return mapper.findById(id);
        }
    }

    @Override
    public void update(SmallArm meaning) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            SmallArmMapper mapper = session.getMapper(SmallArmMapper.class);
            mapper.update(meaning);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            SmallArmMapper mapper = session.getMapper(SmallArmMapper.class);
            mapper.deleteById(id);
        }
    }

    @Override
    public void insert(SmallArm meaning) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            SmallArmMapper mapper = session.getMapper(SmallArmMapper.class);
            mapper.insert(meaning);
        }
    }
}
