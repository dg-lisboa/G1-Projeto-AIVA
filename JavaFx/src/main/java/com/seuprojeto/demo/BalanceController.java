package com.seuprojeto.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import java.sql.SQLException;

public class BalanceController {

    @FXML
    private Text saldoText; // Exibição do saldo total 

    @FXML
    private Text vendasText; // Exibição do valor de vendas 

    @FXML
    private Text despesasText; // Exibição do valor de despesas 

    @FXML
    private TextField vendasField; // Campo para inserção do valor de vendas 

    @FXML
    private TextField despesasField; // Campo para inserção do valor de despesas 

    @FXML
    private ImageView aivaImage; // Imagem Aiva no canto superior direito 

    private double totalVendas = 0.0; // Variável para armazenar o total de vendas 
    private double totalDespesas = 0.0; // Variável para armazenar o total de despesas 

    // Método chamado pelo LoginController para passar os valores de saldo, vendas e despesas 
    public void updateSaldoView(double saldo, double vendas, double despesas) {
        totalVendas = vendas;
        totalDespesas = despesas;
        saldoText.setText("Saldo Atual: R$ " + saldo);
        vendasText.setText("Vendas: R$ " + totalVendas);
        despesasText.setText("Despesas: R$ " + totalDespesas);

        // Ajuste de cores para vendas e despesas 
        vendasText.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #4CAF50;"); // Verde para vendas 
        despesasText.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #FF0000;"); // Vermelho para despesas 
    }

    @FXML
    private void initialize() {
        // Carregar a imagem do logo Aiva no canto superior direito 
        Image image = new Image(getClass().getResourceAsStream("/com/seuprojeto/demo/aiva.png"));
        aivaImage.setImage(image);
    }

    // Método para tratar a ação do botão "Venda Rápida" 
    @FXML
    public void handleVendaRapida() {
        try {
            double vendas = Double.parseDouble(vendasField.getText()); // Obter o valor da venda 
            if (vendas < 0) {
                showAlert("Erro", "O valor da venda não pode ser negativo.", AlertType.ERROR);
                return;
            }

            // Salvar no banco de dados 
            String email = "usuario@exemplo.com"; // Substitua pelo email do usuário 
            UserDAO.salvarTransacao("venda", vendas, email);

            // Atualiza o valor das vendas 
            totalVendas += vendas;
            vendasText.setText("Vendas: R$ " + totalVendas);
            vendasText.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #4CAF50;"); // Verde para valores positivos 
            vendasField.clear();

            // Atualiza o saldo 
            atualizarSaldo(email);

        } catch (NumberFormatException e) {
            showAlert("Erro", "Por favor, insira um valor válido para vendas.", AlertType.ERROR);
        } catch (SQLException e) {
            showAlert("Erro", "Erro ao salvar a transação de venda.", AlertType.ERROR);
        }
    }

    // Método para tratar a ação do botão "Despesas" 
    @FXML
    public void handleDespesas() {
        try {
            double despesas = Double.parseDouble(despesasField.getText()); // Obter o valor das despesas 
            if (despesas < 0) {
                showAlert("Erro", "O valor das despesas não pode ser negativo.", AlertType.ERROR);
                return;
            }

            // Salvar no banco de dados 
            String email = "usuario@exemplo.com"; // Substitua pelo email do usuário 
            UserDAO.salvarTransacao("despesa", despesas, email);

            // Atualiza o valor das despesas 
            totalDespesas += despesas;
            despesasText.setText("Despesas: R$ " + totalDespesas);
            despesasText.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #FF0000;"); // Vermelho para valores negativos 
            despesasField.clear();

            // Atualiza o saldo 
            atualizarSaldo(email);

        } catch (NumberFormatException e) {
            showAlert("Erro", "Por favor, insira um valor válido para despesas.", AlertType.ERROR);
        } catch (SQLException e) {
            showAlert("Erro", "Erro ao salvar a transação de despesa.", AlertType.ERROR);
        }
    }

    // Método para atualizar o saldo total 
    private void atualizarSaldo(String email) {
        try {
            // Calcular o saldo baseado na soma das vendas e subtração das despesas 
            double saldo = totalVendas - totalDespesas;
            saldoText.setText("Saldo Atual: R$ " + saldo);

            // Atualiza o saldo no banco de dados 
            UserDAO.salvarSaldoNoBanco(saldo, email);  // Agora o saldo será salvo no banco de dados 

        } catch (SQLException e) {
            showAlert("Erro", "Erro ao calcular o saldo.", AlertType.ERROR);
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
