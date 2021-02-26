package com.demo.util;

import com.demo.configuration.DatabaseConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class ConnectionHelper {

    private final DatabaseConfig databaseConfig;

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(databaseConfig.getDriver());
        return DriverManager.getConnection(
                databaseConfig.getDatabase(),
                databaseConfig.getUser(),
                databaseConfig.getPassword());
    }
}
