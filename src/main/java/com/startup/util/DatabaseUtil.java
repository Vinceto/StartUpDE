package com.startup.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        // Si la conexión ya está establecida, devolverla
        if (connection != null && !connection.isClosed()) {
            return connection;
        }

        // Configuración de la conexión
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/startup?characterEncoding=UTF-8";
        String user = "postgres";
        String password = "1234";

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Error al cargar el driver de PostgreSQL");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al conectar con la base de datos");
        }

        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}