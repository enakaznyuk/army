package com.solvd.army.persistence.impl;

import com.solvd.army.domain.staff.Officer;
import com.solvd.army.persistence.MyBatisConfig;
import com.solvd.army.persistence.OfficerMapper;
import com.solvd.army.persistence.SmallArmMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class OfficerMapperImpl implements OfficerMapper {

    @Override
    public List<Officer> findAll() {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            OfficerMapper mapper = session.getMapper(OfficerMapper.class);
            return mapper.findAll();
        }
    }

    @Override
    public Optional<Officer> findById(Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            OfficerMapper mapper = session.getMapper(OfficerMapper.class);
            return mapper.findById(id);
        }
    }

    @Override
    public void update(Officer newMeaning) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            OfficerMapper mapper = session.getMapper(OfficerMapper.class);
            mapper.update(newMeaning);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            OfficerMapper mapper = session.getMapper(OfficerMapper.class);
            mapper.deleteById(id);
        }
    }

    @Override
    public void insert(Officer meaning) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            OfficerMapper mapper = session.getMapper(OfficerMapper.class);
            mapper.insert(meaning);
        }
    }
}
