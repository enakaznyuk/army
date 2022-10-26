package com.solvd.army.persistence.impl;

import com.solvd.army.domain.Army;
import com.solvd.army.domain.exception.EntityNotFoundException;
import com.solvd.army.domain.staff.General;
import com.solvd.army.persistence.ArmyRepository;
import com.solvd.army.persistence.GeneralsRepository;
import com.solvd.army.persistence.Search;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.solvd.army.persistence.ConnectionPool.CONNECTION_POOL;

public class ArmyDbImpl extends Search implements ArmyRepository {

    private final String SELECT = new String("select a.id as army_id, a.number as army_number, " +
            "g.id as general_id from armies a inner join generals g on g.army_id = a.id ");
    private final GeneralsRepository generalsRepository;

    public ArmyDbImpl() {
        this.generalsRepository = new GeneralsDbImpl();
    }

    @Override
    public List<Army> findAll() {
        List<Army> armies = new ArrayList<>();
        ResultSet resultSet = select(SELECT);
        Optional<General> generalOptional;
        try {
            while (resultSet.next()) {
                Long id = resultSet.getLong(1);
                int number = resultSet.getInt(2);
                Army army = new Army();
                army.setNumber(number);
                army.setId(id);
                generalOptional = generalsRepository.findById(resultSet.getLong(3));
                General general = generalOptional.orElseThrow(() -> new EntityNotFoundException("Not found"));
                army.setGeneral(general);
                armies.add(army);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return armies;
    }

    @Override
    public Optional<Army> findById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Optional<Army> optional;
        Optional<General> generalOptional;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT + "where id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return Optional.empty();
            }

            Army army = new Army();
            Long idBd = resultSet.getLong(1);
            int number = resultSet.getInt(2);
            army.setNumber(number);
            army.setId(idBd);
            generalOptional = generalsRepository.findById(resultSet.getLong(3));
            General general = generalOptional.orElseThrow(() -> new EntityNotFoundException("Not found"));
            army.setGeneral(general);

            optional = Optional.of(army);
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
            PreparedStatement statement = connection.prepareStatement("update armies set number = ? where id = ?");
            statement.setLong(1, Long.parseLong(newMeaning));
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
            PreparedStatement statement = connection.prepareStatement("delete from armies where id = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.returnConnection(connection);
        }
    }

    @Override
    public void insert(Army meaning) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into armies(number) values (?)", Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, meaning.getNumber());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()){
                meaning.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.returnConnection(connection);
        }
    }
}
