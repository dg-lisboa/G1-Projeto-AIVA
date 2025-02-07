package com.seuprojeto.aiva.telas;

import com.seuprojeto.aiva.utilitarios.BancoDeDados;
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

public class TelaLogin {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField senhaField;

    private static int usuarioLogadoId;


    @FXML
    private void handleLogin(ActionEvent event) {
        String email = emailField.getText();
        String senha = senhaField.getText();

        int userId = BancoDeDados.verificarLogin(email, senha);
        if (userId > 0) { // ✅ Agora userId é um int, e não boolean
            usuarioLogadoId = userId; // ✅ Salvando ID do usuário logado
            mostrarAlerta("Sucesso", "Login realizado com sucesso!", true, event);
        } else {
            mostrarAlerta("Erro", "Email ou senha incorretos.", false);
        }
    }

    public static int getUsuarioLogadoId() {
        return usuarioLogadoId;
    }

    private void abrirTelaPrincipal(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/seuprojeto/aiva/telas/principal.fxml"));
            Parent root = fxmlLoader.load();
            Scene principalScene = new Scene(root, 400, 750);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(principalScene);
        } catch (IOException e) {
            System.out.println("Erro ao carregar tela principal: " + e.getMessage());
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

    @FXML
    private void abrirTelaCadastro(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/seuprojeto/aiva/telas/cadastro.fxml"));
            Parent root = fxmlLoader.load();
            Scene cadastroScene = new Scene(root, 400, 750);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(cadastroScene);
        } catch (IOException e) {
            System.out.println("Erro ao carregar tela de cadastro: " + e.getMessage());
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
                    Platform.runLater(() -> {
                        alert.close(); // ✅ Fecha o alerta automaticamente
                        abrirTelaPrincipal(event[0]); // ✅ Redireciona para a tela principal
                    });
                }
            }, 500); // 🔥 Agora o alerta será exibido por **1 segundo (1000ms)**
        }
    }
}
