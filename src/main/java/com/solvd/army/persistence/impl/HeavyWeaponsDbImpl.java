package com.solvd.army.persistence.impl;

import com.solvd.army.domain.weapon.HeavyWeapon;
import com.solvd.army.persistence.HeavyWeaponsRepository;
import com.solvd.army.persistence.Search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.solvd.army.persistence.ConnectionPool.CONNECTION_POOL;

public class HeavyWeaponsDbImpl extends Search implements HeavyWeaponsRepository {

    private final String SELECT = "select id, name, number from heavy_weapons ";

    @Override
    public List<HeavyWeapon> findAll() {
        List<HeavyWeapon> heavyWeapons = new ArrayList<>();
        ResultSet resultSet = select(SELECT);
        try {
            while (resultSet.next()) {
                Long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                int number = resultSet.getInt(3);
                HeavyWeapon heavyWeapon = new HeavyWeapon();
                heavyWeapon.setNumber(number);
                heavyWeapon.setName(name);
                heavyWeapon.setId(id);
                heavyWeapons.add(heavyWeapon);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return heavyWeapons;
    }

    @Override
    public Optional<HeavyWeapon> findById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Optional<HeavyWeapon> optional;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT + "where id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return Optional.empty();
            }

            HeavyWeapon heavyWeapon = new HeavyWeapon();
            Long idBd = resultSet.getLong(1);
            String name = resultSet.getString(2);
            int number = resultSet.getInt(3);
            heavyWeapon.setNumber(number);
            heavyWeapon.setName(name);
            heavyWeapon.setId(idBd);

            optional = Optional.of(heavyWeapon);
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
            PreparedStatement statement = connection.prepareStatement("update heavy_weapons set name = ? where id = ?");
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
            PreparedStatement statement = connection.prepareStatement("delete from heavy_weapons where id = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.returnConnection(connection);
        }
    }

    @Override
    public void insert(HeavyWeapon meaning) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into heavy_weapons(name, number) values (?, ?)");
            statement.setString(1, meaning.getName());
            statement.setLong(2, meaning.getNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.returnConnection(connection);
        }
    }
}
