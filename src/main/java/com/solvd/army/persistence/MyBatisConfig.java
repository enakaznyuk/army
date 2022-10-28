package com.solvd.army.persistence;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class MyBatisConfig {

    public static final String CONFIG_FILE_NAME = "mybatis-config.xml";
    public static final SqlSessionFactory SQL_SESSION_FACTORY;

    static {
        //String path = Objects.requireNonNull(MyBatisConfig.class.getClassLoader().getResource(CONFIG_FILE_NAME)).getFile();
        String path = MyBatisConfig.class.getClassLoader().getResource(CONFIG_FILE_NAME).getFile();
        if (path.contains("jar!")) {
            path = CONFIG_FILE_NAME;
        }
        File propertiesFile = new File(path);
        try (FileInputStream fis = new FileInputStream(propertiesFile)) {
            SQL_SESSION_FACTORY = new SqlSessionFactoryBuilder().build(fis);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load mybatis config", e);
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return SQL_SESSION_FACTORY;
    }
}
