package com.seuprojeto.demo;

import java.sql.*;
import java.util.UUID;

public class UserDAO {

    private static final String URL = "jdbc:sqlite:usuarios.db"; // Caminho do banco de dados SQLite 

    // Método para conectar ao banco de dados SQLite 
    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // Método para verificar se o e-mail está registrado 
    public static boolean isEmailRegistered(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Se o e-mail for encontrado, retorna true 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para validar a senha do usuário 
    public static boolean isPasswordValid(String email, String senha) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ? AND senha = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, senha);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Se encontrar um usuário com o e-mail e senha corretos, retorna true 
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao validar a senha.");
        }
    }

    // Método para cadastrar um novo usuário 
    public static void cadastrarUsuario(String email, String senha) throws SQLException {
        String sql = "INSERT INTO users (email, senha) VALUES (?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, senha);
            pstmt.executeUpdate(); // Executa a inserção no banco de dados 
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao cadastrar o usuário.");
        }
    }

    // Método para salvar uma transação (venda ou despesa) 
    public static void salvarTransacao(String tipo, double valor, String email) throws SQLException {
        String sql = "INSERT INTO transacoes (tipo, valor, usuario_email) VALUES (?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tipo); // Tipo da transação (venda ou despesa) 
            pstmt.setDouble(2, valor); // Valor da transação 
            pstmt.setString(3, email); // E-mail do usuário 
            pstmt.executeUpdate(); // Executa a inserção no banco de dados 
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao salvar a transação.");
        }
    }

    // Método para calcular o saldo total de um usuário (vendas - despesas) 
    public static double calcularSaldo(String email) throws SQLException {
        double saldo = 0.0;
        String sqlVendas = "SELECT SUM(valor) FROM transacoes WHERE tipo = 'venda' AND usuario_email = ?";
        String sqlDespesas = "SELECT SUM(valor) FROM transacoes WHERE tipo = 'despesa' AND usuario_email = ?";

        try (Connection conn = connect(); PreparedStatement pstmtVendas = conn.prepareStatement(sqlVendas);
             PreparedStatement pstmtDespesas = conn.prepareStatement(sqlDespesas)) {

            pstmtVendas.setString(1, email); // Define o e-mail do usuário 
            pstmtDespesas.setString(1, email); // Define o e-mail do usuário 

            ResultSet rsVendas = pstmtVendas.executeQuery();
            ResultSet rsDespesas = pstmtDespesas.executeQuery();

            // Se houver vendas registradas, soma os valores 
            if (rsVendas.next()) {
                saldo += rsVendas.getDouble(1); // Soma os valores de vendas 
            }

            // Se houver despesas registradas, subtrai os valores 
            if (rsDespesas.next()) {
                saldo -= rsDespesas.getDouble(1); // Subtrai os valores de despesas 
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return saldo; // Retorna o saldo total 
    }

    // Método para gerar o código de recuperação de senha 
    public static String gerarCodigoRecuperacao(String email) {
        String codigo = UUID.randomUUID().toString(); // Gerar código único 

        // Armazenar o código no banco de dados 
        String sql = "UPDATE users SET recovery_code = ? WHERE email = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codigo);
            pstmt.setString(2, email);
            pstmt.executeUpdate(); // Armazena o código no banco de dados 
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return codigo; // Retorna o código gerado 
    }

    // Método para validar o código de recuperação de senha 
    public static boolean validarCodigoRecuperacao(String email, String codigo) {
        String sql = "SELECT * FROM users WHERE email = ? AND recovery_code = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email); // Define o e-mail do usuário 
            pstmt.setString(2, codigo); // Define o código de recuperação 
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Se o código for válido, retorna true 
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Caso contrário, retorna false 
        }
    }

    // Método para alterar a senha do usuário 
    public static void alterarSenha(String email, String novaSenha) throws SQLException {
        String sql = "UPDATE users SET senha = ?, recovery_code = NULL WHERE email = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, novaSenha); // Define a nova senha 
            pstmt.setString(2, email);     // Define o e-mail do usuário 
            pstmt.executeUpdate(); // Executa a atualização da senha 
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao alterar a senha."); // Lança exceção caso ocorra erro 
        }
    }
    // Método para salvar o saldo no banco de dados 
    public static void salvarSaldoNoBanco(double saldo, String email) throws SQLException {
        String sql = "UPDATE users SET saldo = ? WHERE email = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, saldo); // Define o saldo atualizado 
            pstmt.setString(2, email);  // Define o e-mail do usuário 
            pstmt.executeUpdate(); // Executa a atualização do saldo no banco de dados 
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao salvar o saldo no banco de dados.");
        }
    }

} 
 