package com.seuprojeto.aiva.telas;

import com.seuprojeto.aiva.dados.DadoRelatorio;
import com.seuprojeto.aiva.utilitarios.BancoDeDados;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TelaRelatorio {

    @FXML
    private TableView<DadoRelatorio> tabelaRelatorio;

    @FXML
    private TableColumn<DadoRelatorio, String> colDescricao;

    @FXML
    private TableColumn<DadoRelatorio, Double> colValor;

    @FXML
    private TableColumn<DadoRelatorio, String> colCategoria;

    @FXML
    private TableColumn<DadoRelatorio, String> colTipo; // ✅ Nova coluna para "Venda" ou "Despesa"

    @FXML
    private TableColumn<DadoRelatorio, String> colData; // ✅ Nova coluna para a data

    private final ObservableList<DadoRelatorio> listaDados = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria")); // ✅ Agora mostra Produto ou Categoria corretamente
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
    }

    public void carregarDados(int usuarioId) {
        listaDados.clear();

        try (Connection conn = BancoDeDados.conectar(); Statement stmt = conn.createStatement()) {
            // ✅ Buscar vendas do usuário logado e armazenar Produto na coluna Categoria
            ResultSet rsVendas = stmt.executeQuery(
                    "SELECT cliente AS descricao, valor, produto AS categoria, 'Venda' AS tipo, data " +
                            "FROM vendas WHERE usuario_id = " + usuarioId
            );
            while (rsVendas.next()) {
                listaDados.add(new DadoRelatorio(
                        rsVendas.getString("descricao"), // Cliente
                        rsVendas.getDouble("valor"),
                        rsVendas.getString("categoria"), // Produto no lugar de categoria
                        "Venda",
                        rsVendas.getString("data")
                ));
            }

            // ✅ Buscar despesas do usuário logado mantendo Categoria
            ResultSet rsDespesas = stmt.executeQuery(
                    "SELECT descricao, valor, categoria, 'Despesa' AS tipo, data " +
                            "FROM despesas WHERE usuario_id = " + usuarioId
            );
            while (rsDespesas.next()) {
                listaDados.add(new DadoRelatorio(
                        rsDespesas.getString("descricao"),
                        rsDespesas.getDouble("valor"),
                        rsDespesas.getString("categoria"), // Mantém categoria normal
                        "Despesa",
                        rsDespesas.getString("data")
                ));
            }

            tabelaRelatorio.setItems(listaDados);
        } catch (Exception e) {
            System.out.println("Erro ao carregar dados do usuário: " + e.getMessage());
        }
    }

    @FXML
    private void voltarTelaPrincipal(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/seuprojeto/aiva/telas/principal.fxml"));
            Parent root = fxmlLoader.load();
            Scene principalScene = new Scene(root, 400, 750);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(principalScene);
        } catch (IOException e) {
            System.out.println("Erro ao voltar para a tela principal: " + e.getMessage());
        }
    }
}
