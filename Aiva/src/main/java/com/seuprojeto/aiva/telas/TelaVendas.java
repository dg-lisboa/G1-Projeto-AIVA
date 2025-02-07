package com.seuprojeto.aiva.telas;

import com.seuprojeto.aiva.servicos.ServicoVendasHistorico;
import com.seuprojeto.aiva.dados.DadoRelatorio;
import com.seuprojeto.aiva.servicos.ServicoVendas;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Timer;
import java.util.TimerTask;

public class TelaVendas {

    @FXML
    private TextField clienteField;

    @FXML
    private TextField valorField;

    @FXML
    private TextField produtoField;

    @FXML
    private ComboBox<Integer> parcelasComboBox;

    @FXML
    private DatePicker dataVendaPicker;

    @FXML
    private Button btnDesfazer; // Botão para desfazer venda

    private final ServicoVendasHistorico servicoVendasHistorico = new ServicoVendasHistorico();
    private final ServicoVendas servicoVendas = new ServicoVendas(); // ✅ Mantido apenas uma vez

    @FXML
    private TableView<DadoRelatorio> tabelaVendas;

    @FXML
    private TableColumn<DadoRelatorio, String> colCliente;

    @FXML
    private TableColumn<DadoRelatorio, Double> colValor;

    @FXML
    private TableColumn<DadoRelatorio, String> colProduto;

    @FXML
    private TableColumn<DadoRelatorio, String> colParcelas;

    @FXML
    private TableColumn<DadoRelatorio, String> colData;

    private final ObservableList<DadoRelatorio> listaVendas = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        parcelasComboBox.getItems().addAll(1, 2, 3, 4, 5);
        parcelasComboBox.getSelectionModel().selectFirst();
        dataVendaPicker.setValue(LocalDate.now());

        btnDesfazer.setDisable(!servicoVendasHistorico.podeDesfazer()); // ✅ Atualiza o botão no início

        colCliente.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colProduto.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colParcelas.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));

        carregarVendas();
    }

    @FXML
    private void handleVenda(ActionEvent event) {
        String cliente = clienteField.getText();
        String valorTexto = valorField.getText();
        String produto = produtoField.getText();
        Integer parcelas = parcelasComboBox.getValue();
        LocalDate dataVenda = dataVendaPicker.getValue();

        if (cliente.isEmpty() || valorTexto.isEmpty() || produto.isEmpty()) {
            mostrarAlerta("Erro", "Todos os campos devem ser preenchidos!");
            return;
        }

        try {
            double valor = Double.parseDouble(valorTexto);
            int usuarioId = TelaLogin.getUsuarioLogadoId();

            int vendaId = servicoVendas.registrarVenda(usuarioId, cliente, valor, produto, parcelas, dataVenda);
            if (vendaId > 0) { // ✅ Se a venda foi salva com sucesso, armazenamos o ID na pilha
                servicoVendasHistorico.registrarVenda(vendaId);
                mostrarAlerta("Sucesso", "Venda registrada com sucesso!");
                carregarVendas();
                limparCampos();
                atualizarBotaoDesfazer();
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "O valor deve ser um número válido.");
        }
    }


    private void carregarVendas() {
        listaVendas.clear();
        int usuarioId = TelaLogin.getUsuarioLogadoId();
        try (Connection conn = BancoDeDados.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT cliente, valor, produto, parcelas, data FROM vendas WHERE usuario_id = " + usuarioId)) {

            while (rs.next()) {
                listaVendas.add(new DadoRelatorio(
                        rs.getString("cliente"),
                        rs.getDouble("valor"),
                        rs.getString("produto"),
                        rs.getString("parcelas"),
                        rs.getString("data")
                ));
            }
            tabelaVendas.setItems(listaVendas);
        } catch (Exception e) {
            System.out.println("Erro ao carregar vendas: " + e.getMessage());
        }
    }

    @FXML
    private void desfazerUltimaVenda(ActionEvent event) {
        boolean vendaDesfeita = servicoVendasHistorico.desfazerVenda();
        if (vendaDesfeita) {
            mostrarAlerta("Desfazer", "Última venda removida com sucesso!");
            carregarVendas(); // ✅ Atualiza a tabela após desfazer
        } else {
            mostrarAlerta("Erro", "Não há vendas para desfazer.");
        }
        atualizarBotaoDesfazer();
    }


    private void atualizarBotaoDesfazer() {
        btnDesfazer.setDisable(!servicoVendasHistorico.podeDesfazer());
    }

    private void limparCampos() { // ✅ Mantendo apenas um método limparCampos()
        clienteField.clear();
        valorField.clear();
        produtoField.clear();
        parcelasComboBox.getSelectionModel().selectFirst();
        dataVendaPicker.setValue(LocalDate.now());
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
