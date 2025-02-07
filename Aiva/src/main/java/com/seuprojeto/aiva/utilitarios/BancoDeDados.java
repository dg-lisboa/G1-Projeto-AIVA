package com.seuprojeto.aiva.utilitarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class BancoDeDados {

    private static final String URL = "jdbc:sqlite:aiva.db";

    // Conectar ao banco de dados SQLite
    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL);
        } catch (Exception e) {
            System.out.println("Erro de conexão: " + e.getMessage());
            return null;
        }
    }

    // Criar a tabela de usuários, se não existir
    public static void criarTabelaUsuarios() {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "email TEXT NOT NULL UNIQUE," +
                "senha TEXT NOT NULL)";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela de usuários: " + e.getMessage());
        }
    }

    // Método para cadastrar usuário
    public static boolean cadastrarUsuario(String nome, String email, String senha) {
        String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, senha); // Futuramente, podemos criptografar essa senha
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
            return false;
        }
    }

    public static void criarTabelaVendas() {
        String sql = "CREATE TABLE IF NOT EXISTS vendas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "usuario_id INTEGER NOT NULL," +
                "cliente TEXT NOT NULL," + // ✅ Alterado de 'produto' para 'cliente'
                "valor REAL NOT NULL," +
                "produto TEXT NOT NULL," + // ✅ Alterado de 'fornecedor' para 'produto'
                "parcelas INTEGER NOT NULL," +
                "data TEXT NOT NULL," +
                "FOREIGN KEY(usuario_id) REFERENCES usuarios(id))"; // Relacionando com usuários
        try (Connection conn = conectar(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (Exception e) {
            System.out.println("Erro ao criar tabela de vendas: " + e.getMessage());
        }
    }



    // ✅ Criar tabela de despesas com DATA
    public static void criarTabelaDespesas() {
        String sql = "CREATE TABLE IF NOT EXISTS despesas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "usuario_id INTEGER NOT NULL," +
                "descricao TEXT NOT NULL," +
                "valor REAL NOT NULL," +
                "categoria TEXT NOT NULL," +
                "data TEXT NOT NULL," + // ✅ Novo campo de data
                "FOREIGN KEY(usuario_id) REFERENCES usuarios(id))"; // Relacionando com usuários
        try (Connection conn = conectar(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (Exception e) {
            System.out.println("Erro ao criar tabela de despesas: " + e.getMessage());
        }
    }
    public static int verificarLogin(String email, String senha) {
        String sql = "SELECT id FROM usuarios WHERE email = ? AND senha = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id"); // ✅ Retorna o ID do usuário logado
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar login: " + e.getMessage());
        }
        return -1; // ✅ Retorna -1 se o login falhar
    }


}
