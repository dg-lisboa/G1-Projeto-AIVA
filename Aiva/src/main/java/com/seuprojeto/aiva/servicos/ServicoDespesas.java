package com.seuprojeto.aiva.servicos;

import com.seuprojeto.aiva.utilitarios.BancoDeDados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Queue;

public class ServicoDespesas {

    private final Queue<String> filaDespesas = new LinkedList<>();

    public boolean registrarDespesa(int usuarioId, String descricao, double valor, String categoria, LocalDate dataDespesa) {
        filaDespesas.offer(descricao);

        String sql = "INSERT INTO despesas (usuario_id, descricao, valor, categoria, data) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = BancoDeDados.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setString(2, descricao);
            stmt.setDouble(3, valor);
            stmt.setString(4, categoria);
            stmt.setString(5, dataDespesa.toString());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao registrar despesa: " + e.getMessage());
            return false;
        }
    }
}
