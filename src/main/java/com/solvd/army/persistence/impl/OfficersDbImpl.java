package com.solvd.army.persistence.impl;

import com.solvd.army.domain.exception.EntityNotFoundException;
import com.solvd.army.domain.staff.General;
import com.solvd.army.domain.staff.Officer;
import com.solvd.army.domain.staff.Soldier;
import com.solvd.army.domain.weapon.SmallArm;
import com.solvd.army.persistence.OfficersRepository;
import com.solvd.army.persistence.Search;
import com.solvd.army.persistence.SmallArmsRepository;
import com.solvd.army.persistence.SoldiersRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.solvd.army.persistence.ConnectionPool.CONNECTION_POOL;

public class OfficersDbImpl extends Search implements OfficersRepository {

    private final String SELECT = new String("select o.id as officer_id, o.first_name, o.last_name, o.military_badge, o.small_arm_id,\n" +
            "#s.id as soldiers_id\n" +
            "GROUP_CONCAT(s.id) as soldiers_id\n" +
            "from officers o left join soldiers s on o.id = s.officer_id where o.id = ?\n" +
            "group by o.id");
    private final String SELECT_FOR_ALL = new String("select o.id as officer_id, o.first_name, o.last_name, o.military_badge, o.small_arm_id,\n" +
            "#s.id as soldiers_id\n" +
            "GROUP_CONCAT(s.id) as soldiers_id\n" +
            "from officers o left join soldiers s on o.id = s.officer_id\n" +
            "group by o.id");
    private final SmallArmsRepository smallArmsRepository;
    private final SoldiersRepository soldiersRepository;

    public OfficersDbImpl() {
        this.smallArmsRepository = new SmallArmsDbImpl();
        this.soldiersRepository = new SoldiersDbImpl();
    }

    @Override
    public List<Officer> findAll() {
        List<Officer> officers = new ArrayList<>();
        ResultSet resultSet = select(SELECT_FOR_ALL);
        Optional<Officer> officerOptional;

        try {
            while (resultSet.next()) {
                Officer officer;
                officerOptional = findById(resultSet.getLong(1));
                officer = officerOptional.orElseThrow(() -> new EntityNotFoundException("Not found"));
                officers.add(officer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return officers;
    }

    @Override
    public Optional<Officer> findById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Optional<Officer> optional;
        Optional<SmallArm> smallArmOptional;
        Optional<Soldier> soldierOptional;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return Optional.empty();
            }

            Officer officer;
            long idBd = resultSet.getLong(1);
            String name = resultSet.getString(2);
            String last_name = resultSet.getString(3);
            int military_badge = resultSet.getInt(4);
            officer = new Officer(name, last_name, military_badge);
            smallArmOptional = smallArmsRepository.findById(resultSet.getLong(5));
            officer.setRifle(smallArmOptional.orElseThrow(() -> new EntityNotFoundException("Not found")));
            officer.setId(idBd);

            String str = resultSet.getString(6);
            if(str != null) {
                String[] arr = str.split(",");
                List<Soldier> soldiers = new ArrayList<>();
                for (String word : arr) {
                    if (word == null)
                        break;
                    soldierOptional = soldiersRepository.findById(Long.parseLong(word));
                    Soldier soldier = soldierOptional.orElseThrow();
                    soldiers.add(soldier);
                }
                officer.setBattalion(soldiers);
            }
            optional = Optional.of(officer);
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
            PreparedStatement statement = connection.prepareStatement("update officers set first_name = ? where id = ?");
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
            PreparedStatement statement = connection.prepareStatement("delete from officers where id = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.returnConnection(connection);
        }
    }

    @Override
    public void insert(Officer meaning, General id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into officers(general_id, small_arm_id, " +
                    "first_name, last_name, military_badge) " +
                    "values(?, ?, ?, ?, ?)");
            statement.setLong(1, id.getId());
            statement.setLong(2, meaning.getRifle().getId());
            statement.setString(3, meaning.getFirstName());
            statement.setString(4, meaning.getLastName());
            statement.setInt(5, meaning.getMilitaryBadge());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.returnConnection(connection);
        }
    }
}
