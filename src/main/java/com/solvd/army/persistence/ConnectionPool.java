package com.solvd.army.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    public static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private int POOL_SIZE = GetProperty.getPOOL_SIZE();
    private Queue<Connection> freeConnections;
    private Queue<Connection> engageConnections;
    private boolean poolLock = true;

    private volatile static ConnectionPool instance;

    private ConnectionPool() {
        freeConnections = new ConcurrentLinkedQueue<>();
        engageConnections = new ConcurrentLinkedQueue<>();
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                    instance.setFreeConnections();
                }
            }
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        Connection connection = null;
        while (connection == null) {
            if (freeConnections.isEmpty()) {
                pause();
            } else {
                connection = freeConnections.poll();
                engageConnections.add(connection);
            }
        }
        return connection;
    }

    public void returnConnection(Connection conn) {
        engageConnections.remove(conn);
        freeConnections.add(conn);
    }

    public int getPOOL_SIZE() {
        return POOL_SIZE;
    }

    public void setFreeConnections() {
        try {
            Class.forName(GetProperty.getDriver());
            for (int i = 0; i < getPOOL_SIZE(); i++) {
                freeConnections.add(DriverManager.getConnection(GetProperty.getUrl(), GetProperty.getUsername(), GetProperty.getPassword()));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void pause() {
        try {
            Thread.sleep(2 * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
