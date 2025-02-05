

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

public class CadastroController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField senhaField;

    @FXML
    private PasswordField confirmarSenhaField;

    // Método para cadastrar o usuário 
    @FXML
    public void handleCadastro() {
        String email = emailField.getText();
        String senha = senhaField.getText();
        String confirmarSenha = confirmarSenhaField.getText();

        // Verificar se todos os campos foram preenchidos 
        if (email.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
            showAlert("Erro", "Todos os campos são obrigatórios!", AlertType.ERROR);
            return;
        }

        // Validação de e-mail 
        if (!isValidEmail(email)) {
            showAlert("Erro", "Por favor, insira um e-mail válido.", AlertType.ERROR);
            return;
        }

        // Verificar se as senhas coincidem 
        if (!senha.equals(confirmarSenha)) {
            showAlert("Erro", "As senhas não coincidem!", AlertType.ERROR);
            return;
        }

        // Validação de senha (mínimo de 6 caracteres) 
        if (senha.length() < 6) {
            showAlert("Erro", "A senha deve ter pelo menos 6 caracteres.", AlertType.ERROR);
            return;
        }

        // Lógica de salvar o usuário no banco de dados (metodo `cadastrarUsuario`) 
        try {
            UserDAO.cadastrarUsuario(email, senha);
            showAlert("Sucesso", "Cadastro realizado com sucesso!", AlertType.INFORMATION);

            // Fechar a tela de cadastro após o cadastro ser feito 
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.close(); // Fecha a janela de cadastro 

            // Abrir a tela de login novamente 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/seuprojeto/demo/login-view.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));
            newStage.setTitle("Tela de Login");
            newStage.show(); // Exibir a tela de login 
        } catch (Exception e) {
            showAlert("Erro", "Falha ao cadastrar o usuário. Tente novamente.", AlertType.ERROR);
        }
    }

    // Método para validar o formato do e-mail 
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    // Método para exibir alertas 
    private void showAlert(String title, String message, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Método para voltar para a tela de login 
    @FXML
    public void handleBackToLogin() {
        try {
            // Fechar a tela de cadastro 
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.close();

            // Abrir a tela de login 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/seuprojeto/demo/login-view.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));
            newStage.setTitle("Tela de Login");
            newStage.show(); // Exibir a tela de login 
        } catch (IOException e) {
            showAlert("Erro", "Erro ao voltar para a tela de login.", AlertType.ERROR);
        }
    }
} 
 