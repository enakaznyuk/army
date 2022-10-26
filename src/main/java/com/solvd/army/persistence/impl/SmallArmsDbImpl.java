package com.solvd.army.persistence.impl;

import com.solvd.army.domain.weapon.SmallArm;
import com.solvd.army.persistence.Search;
import com.solvd.army.persistence.SmallArmsRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.solvd.army.persistence.ConnectionPool.CONNECTION_POOL;

public class SmallArmsDbImpl extends Search implements SmallArmsRepository {

    private final String SELECT = "select id, name, number from small_arms ";

    @Override
    public List findAll() {
        List<SmallArm> smallArms = new ArrayList<>();
        ResultSet resultSet = select(SELECT);
        try {
            while (resultSet.next()) {
                Long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                int number = resultSet.getInt(3);
                SmallArm smallArm = new SmallArm();
                smallArm.setNumber(number);
                smallArm.setName(name);
                smallArm.setId(id);
                smallArms.add(smallArm);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return smallArms;
    }

    @Override
    public Optional<SmallArm> findById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Optional<SmallArm> optional;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT + "where id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return Optional.empty();
            }

            SmallArm smallArm = new SmallArm();
            Long idBd = resultSet.getLong(1);
            String name = resultSet.getString(2);
            int number = resultSet.getInt(3);
            smallArm.setNumber(number);
            smallArm.setName(name);
            smallArm.setId(idBd);

            optional = Optional.of(smallArm);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.returnConnection(connection);
        }
        return optional;
    }

    @Override
    public void update(Long id, String newMeaning) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("update small_arms set name = ? where id = ?");
            statement.setString(1, newMeaning);
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.returnConnection(connection);
        }
    }

    @Override
    public void deleteById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("delete from small_arms where id = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.returnConnection(connection);
        }
    }

    @Override
    public void insert(SmallArm smallArm) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into small_arms(name, number) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, smallArm.getName());
            statement.setInt(2, smallArm.getNumber());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()){
                smallArm.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.returnConnection(connection);
        }
    }
}
