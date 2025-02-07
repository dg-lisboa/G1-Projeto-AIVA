package com.seuprojeto.aiva.servicos;

import com.seuprojeto.aiva.utilitarios.BancoDeDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServicoCadastro {

    // ✅ Verifica se o e-mail já está cadastrado antes de tentar inserir
    public boolean emailExiste(String email) {
        String sql = "SELECT COUNT(*) AS total FROM usuarios WHERE email = ?";
        try (Connection conn = BancoDeDados.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt("total") > 0) {
                return true; // ✅ E-mail já cadastrado
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar e-mail: " + e.getMessage());
        }
        return false; // ✅ E-mail não cadastrado
    }

    // ✅ Realiza o cadastro do usuário
    public boolean cadastrarUsuario(String nome, String email, String senha) {
        if (emailExiste(email)) {
            return false; // ❌ Não permite cadastrar se o e-mail já existir
        }

        String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";
        try (Connection conn = BancoDeDados.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, senha); // ⚠️ No futuro, podemos criptografar a senha
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
            return false;
        }
    }
}
