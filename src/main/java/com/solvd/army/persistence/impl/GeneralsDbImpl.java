package com.solvd.army.persistence.impl;

import com.solvd.army.domain.Army;
import com.solvd.army.domain.exception.EntityNotFoundException;
import com.solvd.army.domain.staff.General;
import com.solvd.army.domain.staff.Officer;
import com.solvd.army.domain.weapon.SmallArm;
import com.solvd.army.persistence.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.solvd.army.persistence.ConnectionPool.CONNECTION_POOL;

public class GeneralsDbImpl extends Search implements GeneralsRepository {

    private final String SELECT = new String("select g.id as general_id, g.first_name, g.last_name, g.military_badge, g.small_arm_id, \n" +
            "GROUP_CONCAT(o.id) as officers_id \n" +
            "from generals g left join officers o on g.id = o.general_id where g.id = ?\n" +
            "group by g.id");
    private final String SELECT_FOR_ALL = new String("select g.id as general_id, g.first_name, g.last_name, g.military_badge, g.small_arm_id, \n" +
            "GROUP_CONCAT(o.id) as officers_id \n" +
            "from generals g left join officers o on g.id = o.general_id\n" +
            "group by g.id");
    private final SmallArmsRepository smallArmsRepository;
    private final OfficersRepository officersRepository;

    public GeneralsDbImpl() {
        this.smallArmsRepository = new SmallArmsDbImpl();
        this.officersRepository = new OfficersDbImpl();
    }

    @Override
    public List<General> findAll() {
        List<General> generals = new ArrayList<>();
        ResultSet resultSet = select(SELECT_FOR_ALL);
        Optional<General> generalOptional;
        try {
            while (resultSet.next()) {
                General general;
                generalOptional = findById(resultSet.getLong(1));
                general = generalOptional.orElseThrow(() -> new EntityNotFoundException("Not found"));
                generals.add(general);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return generals;
    }

    @Override
    public Optional<General> findById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Optional<General> optional;
        Optional<SmallArm> smallArmOptional;
        Optional<Officer> officerOptional;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return Optional.empty();
            }

            General general;
            long idBd = resultSet.getLong(1);
            String name = resultSet.getString(2);
            String last_name = resultSet.getString(3);
            int military_badge = resultSet.getInt(4);
            general = new General(name, last_name, military_badge);
            smallArmOptional = smallArmsRepository.findById(resultSet.getLong(5));
            general.setPistol(smallArmOptional.orElseThrow(() -> new EntityNotFoundException("Not found")));
            general.setId(idBd);
            List<Officer> officers = new ArrayList<>();
            String str = resultSet.getString(6);
            String[] arr = str.split(",");
            for (String word : arr){
                officerOptional = officersRepository.findById(Long.parseLong(word));
                Officer officer = officerOptional.orElseThrow(() -> new EntityNotFoundException("Not found"));
                officers.add(officer);
            }
            general.setDivision(officers);

            optional = Optional.of(general);
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
            PreparedStatement statement = connection.prepareStatement("update generals set first_name = ? where id = ?");
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
            PreparedStatement statement = connection.prepareStatement("delete from generals where id = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.returnConnection(connection);
        }
    }

    @Override
    public void insert(General meaning, Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into generals(army_id, small_arm_id, " +
                    "first_name, last_name, military_badge) " +
                    "values(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, id);
            statement.setLong(2, meaning.getPistol().getId());
            statement.setString(3, meaning.getFirstName());
            statement.setString(4, meaning.getLastName());
            statement.setInt(5, meaning.getMilitaryBadge());
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
