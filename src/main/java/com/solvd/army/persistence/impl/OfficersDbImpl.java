package com.solvd.army.persistence.impl;

import com.solvd.army.domain.exception.EntityNotFoundException;
import com.solvd.army.domain.staff.Officer;
import com.solvd.army.domain.staff.Soldier;
import com.solvd.army.domain.weapon.SmallArm;
import com.solvd.army.persistence.OfficersRepository;
import com.solvd.army.persistence.Search;

import java.sql.*;
import java.util.*;

import static com.solvd.army.persistence.ConnectionPool.CONNECTION_POOL;

public class OfficersDbImpl extends Search implements OfficersRepository {

    private static final String SELECT = new String("select o.id as officers_id, o.first_name as first_name, o.last_name as last_name, o.military_badge as military_badge,\n" +
            "s.id as small_arm_id, s.name, s.number,\n" +
            "ss.id as soldiers_id, ss.first_name as first_name, ss.last_name as last_name, ss.military_badge as military_badge, ss.demobilization,\n" +
            "sm.id as small_arm_id, sm.name, sm.number\n" +
            "from officers o left join small_arms s on o.small_arm_id = s.id\n" +
            "left join soldiers ss on ss.officer_id = o.id\n" +
            "left join small_arms sm on ss.small_arm_id = sm.id");

    @Override
    public List<Officer> findAll() {
        List<Officer> officers = new ArrayList<>();
        for(Map.Entry<Long, Officer> entry : parseDataBaseResult().entrySet())
            officers.add(entry.getValue());
        return officers;
    }

    @Override
    public Optional<Officer> findById(Long id) {
        return Optional.of(parseDataBaseResult().get(id));
    }

    @Override
    public void update(Long id, String newMeaning) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("update officers set first_name = ? where id = ?");
            statement.setString(1, newMeaning);
            statement.setLong(2, id);
            statement.executeUpdate();
            statement.close();
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
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.returnConnection(connection);
        }
    }

    @Override
    public void insert(Officer meaning, Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into officers(general_id, small_arm_id, " +
                    "first_name, last_name, military_badge) " +
                    "values(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, id);
            statement.setLong(2, meaning.getRifle().getId());
            statement.setString(3, meaning.getFirstName());
            statement.setString(4, meaning.getLastName());
            statement.setInt(5, meaning.getMilitaryBadge());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()){
                meaning.setId(resultSet.getLong(1));
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.returnConnection(connection);
        }
    }

    private Map<Long, Officer> parseDataBaseResult(){
        Connection connection = CONNECTION_POOL.getConnection();
        Map<Long, Officer> proba = new HashMap<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT);
            ResultSet resultSet = statement.executeQuery();
            Officer officerProba;
            Soldier soldierProba;
            SmallArm smallArm;
            SmallArm smallArmSoldier = new SmallArm();

            while (resultSet.next()) {
                long officerId = resultSet.getLong(1);
                Long soldierId = resultSet.getLong(8);
                if(proba.containsKey(officerId)) {
                    officerProba = proba.get(officerId);
                } else{
                    String nameOfficer = resultSet.getString(2);
                    String lastNameOfficer = resultSet.getString(3);
                    int military_badgeOfficer = resultSet.getInt(4);
                    officerProba = new Officer(nameOfficer, lastNameOfficer, military_badgeOfficer);
                    officerProba.setId(officerId);
                    officerProba.setId(officerId);
                    proba.putIfAbsent(officerId, officerProba);
                    smallArm = new SmallArm();
                    long smallArmIdOfficer = resultSet.getLong(5);
                    String smallArmNameOfficer = resultSet.getString(6);
                    int smallArmNumberOfficer = resultSet.getInt(7);
                    smallArm.setId(smallArmIdOfficer);
                    smallArm.setName(smallArmNameOfficer);
                    smallArm.setNumber(smallArmNumberOfficer);
                    officerProba.setRifle(smallArm);
                }
                if(soldierId != 0){
                    String nameSoldier = resultSet.getString(9);
                    String lastNameSoldier = resultSet.getString(10);
                    int militaryBadgeSoldier = resultSet.getInt(11);
                    long smallArmIdSoldier = resultSet.getLong(13);
                    String smallArmNameSoldier = resultSet.getString(14);
                    int smallArmNumberSoldier = resultSet.getInt(15);
                    soldierProba = new Soldier(nameSoldier, lastNameSoldier, militaryBadgeSoldier);
                    smallArmSoldier.setId(smallArmIdSoldier);
                    smallArmSoldier.setName(smallArmNameSoldier);
                    smallArmSoldier.setNumber(smallArmNumberSoldier);
                    soldierProba.setRifle(smallArmSoldier);
                    soldierProba.setDemobilization(resultSet.getDate(12).
                            toLocalDate());
                    soldierProba.setId(soldierId);
                    officerProba.addSoldier(soldierProba);
                }
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.returnConnection(connection);
        }
        return proba;
    }
}
