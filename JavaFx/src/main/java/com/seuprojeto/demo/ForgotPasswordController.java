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

public class ForgotPasswordController {

    @FXML
    private TextField emailField;

    @FXML
    private TextField codigoField;

    @FXML
    private PasswordField senhaField;

    // Método para voltar para a tela de login
    @FXML
    public void handleBackToLogin() {
        try {
            // Fechar a tela de recuperação de senha
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.close();

            // Tentar carregar a tela de login
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/seuprojeto/demo/login-view.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));  // Lança IOException aqui se o arquivo FXML não for encontrado
            newStage.setTitle("Tela de Login");
            newStage.show(); // Exibe a tela de login

        } catch (IOException e) {
            // Tratar a exceção caso o arquivo FXML não seja encontrado ou outro erro de I/O
            showAlert("Erro", "Erro ao voltar para a tela de login: " + e.getMessage(), AlertType.ERROR);
        }
    }

    // Método para solicitar a recuperação de senha
    @FXML
    public void handleRecoverPassword() {
        String email = emailField.getText();

        // Verificar se o campo de e-mail não está vazio
        if (email.isEmpty()) {
            showAlert("Erro", "Por favor, insira o e-mail.", AlertType.ERROR);
            return;
        }

        // Verificar se o e-mail está registrado
        if (UserDAO.isEmailRegistered(email)) {
            // Gerar o código de recuperação
            String codigo = UserDAO.gerarCodigoRecuperacao(email);
            showAlert("Sucesso", "Um link de recuperação foi enviado para seu e-mail.\nCódigo: " + codigo, AlertType.INFORMATION);
        } else {
            showAlert("Erro", "O e-mail informado não está registrado.", AlertType.ERROR);
        }
    }

    // Método para redefinir a senha
    @FXML
    public void handleResetPassword() {
        String email = emailField.getText();
        String codigo = codigoField.getText();
        String novaSenha = senhaField.getText();

        if (email.isEmpty() || codigo.isEmpty() || novaSenha.isEmpty()) {
            showAlert("Erro", "Todos os campos devem ser preenchidos.", AlertType.ERROR);
            return;
        }

        // Validar o código de recuperação e a senha
        if (UserDAO.validarCodigoRecuperacao(email, codigo)) {
            try {
                // Atualizar a senha no banco de dados
                UserDAO.alterarSenha(email, novaSenha);
                showAlert("Sucesso", "Senha redefinida com sucesso!", AlertType.INFORMATION);

                // Fechar a tela de redefinir senha
                Stage stage = (Stage) emailField.getScene().getWindow();
                stage.close();

                // Após redefinir, você pode abrir a tela de login novamente
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/seuprojeto/demo/login-view.fxml"));
                Stage newStage = new Stage();
                newStage.setScene(new Scene(loader.load()));
                newStage.setTitle("Tela de Login");
                newStage.show();
            } catch (SQLException e) {
                showAlert("Erro", "Erro ao redefinir a senha: " + e.getMessage(), AlertType.ERROR);
            } catch (IOException e) {
                showAlert("Erro", "Erro ao carregar a tela de login: " + e.getMessage(), AlertType.ERROR);
            }
        } else {
            showAlert("Erro", "Código de recuperação inválido.", AlertType.ERROR);
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
