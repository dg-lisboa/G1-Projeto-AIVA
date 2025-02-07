package com.seuprojeto.aiva.servicos;

import com.seuprojeto.aiva.utilitarios.BancoDeDados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Stack;

public class ServicoVendasHistorico {

    private final Stack<Integer> historicoVendas = new Stack<>(); // ✅ Agora armazena IDs das vendas

    // ✅ Método para registrar o ID da venda na pilha
    public void registrarVenda(int vendaId) {
        historicoVendas.push(vendaId); // Adiciona ID da venda à pilha
        System.out.println("Venda registrada no histórico: ID " + vendaId);
    }

    // ✅ Método para desfazer a última venda (remover do banco)
    public boolean desfazerVenda() {
        if (!historicoVendas.isEmpty()) {
            int vendaId = historicoVendas.pop(); // Obtém o ID da última venda
            try (Connection conn = BancoDeDados.conectar();
                 PreparedStatement stmt = conn.prepareStatement("DELETE FROM vendas WHERE id = ?")) {
                stmt.setInt(1, vendaId);
                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Venda desfeita com sucesso: ID " + vendaId);
                    return true;
                }
            } catch (SQLException e) {
                System.out.println("Erro ao desfazer venda: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean podeDesfazer() {
        return !historicoVendas.isEmpty();
    }
}
