package com.solvd.army.persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GetProperty {

    public static String getDriver(){
        try {
            FileInputStream str;
            Properties properties = new Properties();
            str = new FileInputStream("./src/main/resources/config.properties");
            properties.load(str);
            return properties.getProperty("driver");
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static String getUrl(){
        try {
            FileInputStream str;
            Properties properties = new Properties();
            str = new FileInputStream("./src/main/resources/config.properties");
            properties.load(str);
            return properties.getProperty("url");
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static String getUsername(){
        try {
            FileInputStream str;
            Properties properties = new Properties();
            str = new FileInputStream("./src/main/resources/config.properties");
            properties.load(str);
            return properties.getProperty("username");
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static String getPassword(){
        try {
            FileInputStream str;
            Properties properties = new Properties();
            str = new FileInputStream("./src/main/resources/config.properties");
            properties.load(str);
            return properties.getProperty("password");
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static Integer getPOOL_SIZE(){
        try {
            FileInputStream str;
            Properties properties = new Properties();
            str = new FileInputStream("./src/main/resources/config.properties");
            properties.load(str);
            return Integer.valueOf(properties.getProperty("POOL_SIZE"));
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
