package com.seuprojeto.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseInitializer {

    private static final String URL = "jdbc:sqlite:usuarios.db"; // Caminho do banco de dados

    public static void initialize() {
        String createUsersTable = "CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "email TEXT NOT NULL UNIQUE, " +
                "senha TEXT NOT NULL, " +
                "recovery_code TEXT);";

        String createTransacoesTable = "CREATE TABLE IF NOT EXISTS transacoes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tipo TEXT NOT NULL, " +
                "valor REAL NOT NULL, " +
                "usuario_email TEXT NOT NULL, " +
                "FOREIGN KEY (usuario_email) REFERENCES users(email));";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            // Criação das tabelas
            stmt.execute(createUsersTable);
            stmt.execute(createTransacoesTable);
            System.out.println("Tabelas criadas com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        initialize();  // Chama a inicialização
    }
}
