package com.seuprojeto.aiva.telas;

import com.seuprojeto.aiva.dados.DadoRelatorio;
import com.seuprojeto.aiva.servicos.ServicoDespesas;
import com.seuprojeto.aiva.utilitarios.BancoDeDados;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Timer;
import java.util.TimerTask;

public class TelaDespesas {

    @FXML
    private TextField descricaoField;

    @FXML
    private TextField valorField;

    @FXML
    private TextField categoriaField;

    @FXML
    private DatePicker dataDespesaPicker;

    @FXML
    private TableView<DadoRelatorio> tabelaDespesas;

    @FXML
    private TableColumn<DadoRelatorio, String> colDescricao;

    @FXML
    private TableColumn<DadoRelatorio, Double> colValor;

    @FXML
    private TableColumn<DadoRelatorio, String> colCategoria;

    @FXML
    private TableColumn<DadoRelatorio, String> colData;

    private final ObservableList<DadoRelatorio> listaDespesas = FXCollections.observableArrayList();
    private final ServicoDespesas servicoDespesas = new ServicoDespesas();

    @FXML
    private void initialize() {
        dataDespesaPicker.setValue(LocalDate.now());

        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));

        carregarDespesas();
    }

    @FXML
    private void handleDespesa(ActionEvent event) {
        String descricao = descricaoField.getText();
        String valorTexto = valorField.getText();
        String categoria = categoriaField.getText();
        LocalDate dataDespesa = dataDespesaPicker.getValue();

        if (descricao.isEmpty() || valorTexto.isEmpty() || categoria.isEmpty()) {
            mostrarAlerta("Erro", "Todos os campos devem ser preenchidos!");
            return;
        }

        try {
            double valor = Double.parseDouble(valorTexto);
            int usuarioId = TelaLogin.getUsuarioLogadoId();

            boolean sucesso = servicoDespesas.registrarDespesa(usuarioId, descricao, valor, categoria, dataDespesa);
            if (sucesso) {
                mostrarAlerta("Sucesso", "Despesa registrada com sucesso!");
                carregarDespesas(); // ✅ Atualiza a tabela automaticamente
                limparCampos(); // ✅ Limpa os campos, mas mantém na tela
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "O valor deve ser um número válido.");
        }
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

    private void carregarDespesas() {
        listaDespesas.clear();
        int usuarioId = TelaLogin.getUsuarioLogadoId();
        try (Connection conn = BancoDeDados.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT descricao, valor, categoria, data FROM despesas WHERE usuario_id = " + usuarioId)) {

            while (rs.next()) {
                listaDespesas.add(new DadoRelatorio(
                        rs.getString("descricao"),
                        rs.getDouble("valor"),
                        rs.getString("categoria"),
                        "Despesa",
                        rs.getString("data")
                ));
            }

            tabelaDespesas.setItems(listaDespesas);
        } catch (Exception e) {
            System.out.println("Erro ao carregar despesas: " + e.getMessage());
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.show();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(alert::close);
            }
        }, 500);
    }

    private void limparCampos() {
        descricaoField.clear();
        valorField.clear();
        categoriaField.clear();
        dataDespesaPicker.setValue(LocalDate.now());
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
