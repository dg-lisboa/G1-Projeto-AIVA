package com.seuprojeto.demo;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class UserTable {

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "email TEXT NOT NULL UNIQUE, "
                + "senha TEXT NOT NULL);";

        try (Connection conn = SQLiteConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);  // Cria a tabela de usuários se ela não existir
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }
}