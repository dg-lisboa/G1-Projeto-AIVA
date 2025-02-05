package com.seuprojeto.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {

    private static final String URL = "jdbc:sqlite:usuarios.db";

    // Método para conectar ao banco de dados
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // Método para fechar a conexão com o banco de dados
    public static void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
