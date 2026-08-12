package com.mycompany.libraryproject.infrastructure.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConexion {

    private static final String DB_URL = Config.get("db.url");
    private static final String DB_USER = Config.get("db.user");
    private static final String DB_PASSWORD = Config.get("db.password");

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL Driver cargado correctamente.");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No se pudo cargar el driver MySQL", e);
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DB_URL,
                DB_USER,
                DB_PASSWORD
        );
    }
}
