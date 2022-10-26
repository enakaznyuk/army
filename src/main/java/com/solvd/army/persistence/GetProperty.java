package com.solvd.army.persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GetProperty {

    public static String getDriver(){
        return getFile("driver");
    }

    public static String getUrl(){
        return getFile("url");
    }

    public static String getUsername(){
        return getFile("username");
    }

    public static String getPassword(){
        return getFile("password");
    }

    public static Integer getPOOL_SIZE(){
        return Integer.valueOf(getFile("POOL_SIZE"));
    }

    private static String getFile(String str){
        try {
            FileInputStream fileInputStream;
            Properties properties = new Properties();
            fileInputStream = new FileInputStream("./src/main/resources/config.properties");
            properties.load(fileInputStream);
            return properties.getProperty(str);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
