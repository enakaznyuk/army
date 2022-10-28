package com.solvd.army.persistence.impl;

import com.solvd.army.domain.staff.General;
import com.solvd.army.persistence.GeneralMapper;
import com.solvd.army.persistence.MyBatisConfig;
import com.solvd.army.persistence.OfficerMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class GeneralMapperImpl implements GeneralMapper {

    @Override
    public List<General> findAll() {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            GeneralMapper mapper = session.getMapper(GeneralMapper.class);
            return mapper.findAll();
        }
    }

    @Override
    public Optional<General> findById(Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            GeneralMapper mapper = session.getMapper(GeneralMapper.class);
            return mapper.findById(id);
        }
    }

    @Override
    public void update(General newMeaning) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            GeneralMapper mapper = session.getMapper(GeneralMapper.class);
            mapper.update(newMeaning);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            GeneralMapper mapper = session.getMapper(GeneralMapper.class);
            mapper.deleteById(id);
        }
    }

    @Override
    public void insert(General meaning) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            GeneralMapper mapper = session.getMapper(GeneralMapper.class);
            mapper.insert(meaning);
        }
    }
}
