package com.seuprojeto.aiva.telas;

import com.seuprojeto.aiva.servicos.ServicoCadastro;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class TelaCadastro {

    @FXML
    private TextField nomeField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField senhaField;

    private final ServicoCadastro servicoCadastro = new ServicoCadastro(); // ✅ Chamando a classe de serviços

    @FXML
    private void handleCadastro(ActionEvent event) {
        String nome = nomeField.getText();
        String email = emailField.getText();
        String senha = senhaField.getText();

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            mostrarAlerta("Erro", "Todos os campos devem ser preenchidos!", false);
            return;
        }

        boolean sucesso = servicoCadastro.cadastrarUsuario(nome, email, senha);
        if (sucesso) {
            mostrarAlerta("Sucesso", "Cadastro realizado! Redirecionando...", true, event);
        } else {
            mostrarAlerta("Erro", "Este e-mail já está cadastrado!", false);
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

    private void mostrarAlerta(String titulo, String mensagem, boolean redirecionar, ActionEvent... event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.show();

        if (redirecionar && event.length > 0) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    // ✅ Rodar na thread correta do JavaFX
                    Platform.runLater(() -> {
                        alert.close(); // ✅ Fecha o alerta automaticamente
                        voltarParaLogin(event[0]); // ✅ Redireciona para a tela de login
                    });
                }
            }, 1000); // 🔥 Agora o alerta será exibido por **1 segundo (1000ms)**
        }
    }

    @FXML
    private void voltarParaLogin(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/seuprojeto/aiva/telas/login.fxml"));
            Parent root = fxmlLoader.load();
            Scene loginScene = new Scene(root, 400, 800);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(loginScene);
        } catch (IOException e) {
            System.out.println("Erro ao voltar para a tela de login: " + e.getMessage());
        }
    }
}