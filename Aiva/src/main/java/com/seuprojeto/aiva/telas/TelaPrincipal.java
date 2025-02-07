package com.seuprojeto.aiva.telas;

import com.seuprojeto.aiva.utilitarios.BancoDeDados;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.io.IOException;
import javafx.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TelaPrincipal {
    @FXML
    private Label totalVendasLabel;

    @FXML
    private Label totalDespesasLabel;

    @FXML
    private Label saldoLabel;

    @FXML
    private void initialize() {
        atualizarValores();
    }
    @FXML
    private void abrirTelaVendas(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/seuprojeto/aiva/telas/vendas.fxml"));
            Parent root = fxmlLoader.load();
            Scene vendasScene = new Scene(root, 400, 700);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(vendasScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar tela de vendas.");
        }
    }

    @FXML
    private void goToDespesas(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/seuprojeto/aiva/telas/despesas.fxml"));
            Parent root = fxmlLoader.load();
            Scene despesasScene = new Scene(root, 400, 700);

            // Obtendo a Stage corretamente a partir do botão clicado
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(despesasScene);
        } catch (IOException e) {
            System.out.println("Erro ao carregar tela de despesas: " + e.getMessage());
        }
    }

    @FXML
    private void logout(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/seuprojeto/aiva/telas/login.fxml"));
            Parent root = fxmlLoader.load();
            Scene loginScene = new Scene(root, 400, 700);

            // Obtendo a Stage corretamente a partir do botão clicado
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(loginScene);
        } catch (IOException e) {
            System.out.println("Erro ao voltar para tela de login: " + e.getMessage());
        }
    }
    @FXML
    private void abrirRelatorios(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/seuprojeto/aiva/telas/relatorio.fxml"));
            Parent root = fxmlLoader.load();

            TelaRelatorio controller = fxmlLoader.getController();
            controller.carregarDados(TelaLogin.getUsuarioLogadoId()); // ✅ Passando o ID do usuário logado

            Scene relatorioScene = new Scene(root, 400, 700);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(relatorioScene);
        } catch (IOException e) {
            System.out.println("Erro ao abrir relatórios: " + e.getMessage());
        }
    }

    @FXML
    private void atualizarValores() {
        double totalVendas = calcularTotal("vendas", "valor");
        double totalDespesas = calcularTotal("despesas", "valor");
        double saldo = totalVendas - totalDespesas;

        totalVendasLabel.setText(String.format("R$ %.2f", totalVendas));
        totalDespesasLabel.setText(String.format("R$ %.2f", totalDespesas));
        saldoLabel.setText(String.format("R$ %.2f", saldo));
    }
    private void trocarTela(ActionEvent event, String caminhoFXML) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(caminhoFXML));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // ✅ Mantém o tamanho atual da tela ao trocar de cena
            Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Erro ao carregar tela: " + e.getMessage());
        }
    }

    private double calcularTotal(String tabela, String coluna) {
        int usuarioId = TelaLogin.getUsuarioLogadoId(); // ✅ Pegando o ID do usuário logado
        String sql = "SELECT SUM(" + coluna + ") AS total FROM " + tabela + " WHERE usuario_id = " + usuarioId;
        try (Connection conn = BancoDeDados.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (Exception e) {
            System.out.println("Erro ao calcular total de " + tabela + " para o usuário " + usuarioId + ": " + e.getMessage());
        }
        return 0.0;
    }

}
