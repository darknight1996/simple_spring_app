package com.demo.configuration;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.util.Properties;

@Component
@Getter
public class DatabaseConfig {

    private String driver;

    private String database;

    private String user;

    private String password;

    public DatabaseConfig() {
        init();
    }

    private void init() {

        FileInputStream fis;
        Properties property = new Properties();

        try {

            fis = new FileInputStream(ResourceUtils.getURL("classpath:").getPath() + "config.properties");
            property.load(fis);

            driver = property.getProperty("driver");
            database = property.getProperty("database");
            user = property.getProperty("user");
            password = property.getProperty("password");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
