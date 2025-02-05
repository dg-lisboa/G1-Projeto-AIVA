package com.seuprojeto.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField senhaField;

    // Método para tratar o login do usuário 
    @FXML
    public void handleLogin() {
        String email = emailField.getText();
        String senha = senhaField.getText();

        // Validação simples do e-mail e senha 
        if (email.isEmpty() || senha.isEmpty()) {
            showAlert("Erro", "Por favor, preencha todos os campos.", AlertType.ERROR);
            return;
        }

        // Aqui você faz a validação do login, por exemplo, no banco de dados 
        try {
            if (UserDAO.isEmailRegistered(email) && UserDAO.isPasswordValid(email, senha)) {
                // Se o login for bem-sucedido, carrega a tela de saldo 
                loadBalanceView();
            } else {
                showAlert("Erro", "E-mail ou senha inválidos.", AlertType.ERROR);
            }
        } catch (SQLException e) {
            showAlert("Erro", "Erro ao validar a senha.", AlertType.ERROR);
        }
    }

    // Método para carregar a tela de saldo 
    private void loadBalanceView() {
        try {
            // Fechar a tela de login 
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.close();

            // Carregar a tela de saldo 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/seuprojeto/demo/balance-view.fxml"));
            Stage newStage = new Stage();
            Scene scene = new Scene(loader.load());

            // Passar os valores para a tela de saldo 
            BalanceController balanceController = loader.getController();
            balanceController.updateSaldoView(1000.00, 200.00, -50.00); // Exemplo de valores 

            newStage.setScene(scene);
            newStage.setTitle("Tela de Saldo");
            newStage.show(); // Exibir a tela de saldo 

        } catch (IOException e) {
            showAlert("Erro", "Erro ao carregar a tela de saldo.", AlertType.ERROR);
        }
    }

    // Método para navegar para a tela de cadastro 
    @FXML
    public void handleCadastro() {
        try {
            // Abrir a tela de cadastro 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/seuprojeto/demo/cadastro-view.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));
            newStage.setTitle("Cadastro");
            newStage.show(); // Exibir a tela de cadastro 
        } catch (IOException e) {
            showAlert("Erro", "Erro ao abrir a tela de cadastro.", AlertType.ERROR);
        }
    }

    // Método para navegar para a tela de recuperação de senha 
    @FXML
    public void handleForgotPassword() {
        try {
            // Abrir a tela de recuperação de senha 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/seuprojeto/demo/forgot-password-view.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));
            newStage.setTitle("Recuperação de Senha");
            newStage.show(); // Exibir a tela de recuperação 
        } catch (IOException e) {
            showAlert("Erro", "Erro ao abrir a tela de recuperação de senha.", AlertType.ERROR);
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