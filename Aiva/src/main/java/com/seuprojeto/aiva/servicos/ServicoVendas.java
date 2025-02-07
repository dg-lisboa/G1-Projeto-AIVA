package com.seuprojeto.aiva.servicos;

import com.seuprojeto.aiva.utilitarios.BancoDeDados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ServicoVendas {

    public int registrarVenda(int usuarioId, String cliente, double valor, String produto, int parcelas, LocalDate dataVenda) {
        String sql = "INSERT INTO vendas (usuario_id, cliente, valor, produto, parcelas, data) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = BancoDeDados.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, usuarioId);
            stmt.setString(2, cliente);
            stmt.setDouble(3, valor);
            stmt.setString(4, produto);
            stmt.setInt(5, parcelas);
            stmt.setString(6, dataVenda.toString());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // ✅ Retorna o ID gerado da venda
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao registrar venda: " + e.getMessage());
        }
        return -1; // ❌ Indica falha
    }
}
