package com.solvd.army.persistence.impl;

import com.solvd.army.domain.exception.EntityNotFoundException;
import com.solvd.army.domain.staff.General;
import com.solvd.army.domain.staff.Officer;
import com.solvd.army.domain.staff.Soldier;
import com.solvd.army.domain.weapon.SmallArm;
import com.solvd.army.persistence.Search;
import com.solvd.army.persistence.SmallArmsRepository;
import com.solvd.army.persistence.SoldiersRepository;

import java.sql.*;
import java.sql.Date;
import java.util.*;

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

            ///////////////////////////////////////

            Map<General, Officer> proba = new HashMap<>();
            General general;
            Officer officer;
            SmallArm smallArm = new SmallArm();

            /*while (resultSet.next()) {
                if (id == resultSet.getLong(1)) {
                    String name = resultSet.getString(2);
                    String lastName = resultSet.getString(3);
                    int military_badge = resultSet.getInt(4);
                    long smallArmId = resultSet.getLong(5);
                    String smallArmName = resultSet.getString(6);
                    int smallArmNumber = resultSet.getInt(7);
                    general = new General(name, lastName, military_badge);
                    smallArm.setId(smallArmId);
                    smallArm.setName(smallArmName);
                    smallArm.setNumber(smallArmNumber);
                    general.setPistol(smallArm);

                    long officerId = resultSet.getLong(8);
                    String nameOfficer = resultSet.getString(9);
                    String lastNameOfficer = resultSet.getString(10);
                    int militaryBadgeOfficer = resultSet.getInt(11);
                    long smallArmIdOfficer = resultSet.getLong(12);
                    officer = new Officer(nameOfficer, lastNameOfficer, militaryBadgeOfficer);
                }
            }

            String trigger = "";
            while (resultSet.next()) {
                if (id == resultSet.getLong(1)) {
                    if (!trigger.equals(resultSet.getString(2))){
                        String name = resultSet.getString(2);
                        String lastName = resultSet.getString(3);
                        int military_badge = resultSet.getInt(4);
                        long smallArmId = resultSet.getLong(5);
                        String smallArmName = resultSet.getString(6);
                        int smallArmNumber = resultSet.getInt(7);
                        general = new General(name, lastName, military_badge);
                        smallArm.setId(smallArmId);
                        smallArm.setName(smallArmName);
                        smallArm.setNumber(smallArmNumber);
                        general.setPistol(smallArm);
                        trigger = name;
                    }
                    long officerId = resultSet.getLong(8);
                    String nameOfficer = resultSet.getString(9);
                    String lastNameOfficer = resultSet.getString(10);
                    int militaryBadgeOfficer = resultSet.getInt(11);
                    long smallArmIdOfficer = resultSet.getLong(12);
                    officer = new Officer(nameOfficer, lastNameOfficer, militaryBadgeOfficer);

                }
            }*/




            //////////////////////////////////////////

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
    public void insert(Soldier meaning, Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into soldiers(officer_id, small_arm_id, " +
                    "first_name, last_name, demobilization, military_badge) " +
                    "values(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, id);
            statement.setLong(2, meaning.getRifle().getId());
            statement.setString(3, meaning.getFirstName());
            statement.setString(4, meaning.getLastName());
            statement.setDate(5, Date.valueOf(meaning.getDemobilization()));
            statement.setInt(6, meaning.getMilitaryBadge());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                meaning.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.returnConnection(connection);
        }
    }
}
