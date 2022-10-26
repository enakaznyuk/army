package com.solvd.army.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.solvd.army.persistence.ConnectionPool.CONNECTION_POOL;

public abstract class Search {

    protected ResultSet select(String str){
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(str);
            statement.close();
            return statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.returnConnection(connection);
        }
    }
}
