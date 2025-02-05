package com.seuprojeto.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;

public class BalanceViewController {

    @FXML
    private Label positiveBalanceLabel;

    @FXML
    private Label negativeBalanceLabel;

    // Método para inicializar os saldos (exemplo, você pode pegar isso do banco de dados) 
    public void initialize() {
        // Aqui você deve buscar os valores reais do banco de dados 
        double positiveBalance = 1000.00; // Saldo positivo de exemplo 
        double negativeBalance = -200.00; // Saldo negativo de exemplo 

        positiveBalanceLabel.setText(String.format("R$ %.2f", positiveBalance));
        negativeBalanceLabel.setText(String.format("R$ %.2f", negativeBalance));
    }

    // Método para tratar a ação do botão "Venda Rápida" 
    @FXML
    public void handleQuickSale() {
        // Aqui você pode abrir a tela de "Venda Rápida" 
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/seuprojeto/demo/quick-sale-view.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));
            newStage.setTitle("Venda Rápida");
            newStage.show();
        } catch (IOException e) {
            showAlert("Erro", "Erro ao abrir a tela de Venda Rápida.", AlertType.ERROR);
        }
    }

    // Método para tratar a ação do botão "Despesas" 
    @FXML
    public void handleExpenses() {
        // Aqui você pode abrir a tela de "Despesas" 
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/seuprojeto/demo/expenses-view.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));
            newStage.setTitle("Despesas");
            newStage.show();
        } catch (IOException e) {
            showAlert("Erro", "Erro ao abrir a tela de Despesas.", AlertType.ERROR);
        }
    }

    // Método para exibir alertas 
    private void showAlert(String title, String message, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
} 
 