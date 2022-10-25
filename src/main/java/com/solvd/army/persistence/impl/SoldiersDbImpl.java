package com.solvd.army.persistence.impl;

import com.solvd.army.domain.exception.EntityNotFoundException;
import com.solvd.army.domain.staff.Officer;
import com.solvd.army.domain.staff.Soldier;
import com.solvd.army.domain.weapon.SmallArm;
import com.solvd.army.persistence.Search;
import com.solvd.army.persistence.SmallArmsRepository;
import com.solvd.army.persistence.SoldiersRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.solvd.army.persistence.ConnectionPool.CONNECTION_POOL;

public class SoldiersDbImpl extends Search implements SoldiersRepository {

    private final String SELECT = new String("select id, first_name, last_name, " +
            "military_badge, demobilization, small_arm_id from soldiers ");
    private final SmallArmsRepository smallArmsRepository;


    public SoldiersDbImpl() {
        this.smallArmsRepository = new SmallArmsDbImpl();
    }

    @Override
    public List<Soldier> findAll() {
        List<Soldier> soldiers = new ArrayList<>();
        ResultSet resultSet = select(SELECT);
        Optional<SmallArm> smallArmOptional;
        try {
            while (resultSet.next()) {
                Soldier soldier;
                Long idBd = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String last_name = resultSet.getString(3);
                int military_badge = resultSet.getInt(4);
                soldier = new Soldier(name, last_name, military_badge);
                soldier.setDemobilization(resultSet.getDate(5).
                        toLocalDate());
                smallArmOptional = smallArmsRepository.findById(resultSet.getLong(6));
                SmallArm smallArm = smallArmOptional.orElseThrow(() -> new EntityNotFoundException("Not found"));
                soldier.setId(idBd);
                soldier.setRifle(smallArm);
                soldiers.add(soldier);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return soldiers;
    }

    @Override
    public Optional<Soldier> findById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Optional<Soldier> optional;
        Optional<SmallArm> smallArmOptional;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT + "where id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return Optional.empty();
            }

            Soldier soldier;
            Long idBd = resultSet.getLong(1);
            String name = resultSet.getString(2);
            String last_name = resultSet.getString(3);
            int military_badge = resultSet.getInt(4);
            soldier = new Soldier(name, last_name, military_badge);
            soldier.setDemobilization(resultSet.getDate(5).
                    toLocalDate());
            smallArmOptional = smallArmsRepository.findById(resultSet.getLong(6));
            soldier.setRifle(smallArmOptional.orElseThrow(() -> new EntityNotFoundException("Not found")));
            soldier.setId(idBd);

            optional = Optional.of(soldier);
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
            PreparedStatement statement = connection.prepareStatement("update soldiers set first_name = ? where id = ?");
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
            PreparedStatement statement = connection.prepareStatement("delete from soldiers where id = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.returnConnection(connection);
        }
    }

    @Override
    public void insert(Soldier meaning, Officer id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into soldiers(officer_id, small_arm_id, " +
                    "first_name, last_name, demobilization, military_badge) " +
                    "values(?, ?, ?, ?, ?, ?)");
            statement.setLong(1, id.getId());
            statement.setLong(2, meaning.getRifle().getId());
            statement.setString(3, meaning.getFirstName());
            statement.setString(4, meaning.getLastName());
            statement.setDate(5, Date.valueOf(meaning.getDemobilization()));
            statement.setInt(6, meaning.getMilitaryBadge());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.returnConnection(connection);
        }
    }
}
