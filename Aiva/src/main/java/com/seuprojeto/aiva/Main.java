package com.seuprojeto.aiva;

import com.seuprojeto.aiva.utilitarios.BancoDeDados;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/seuprojeto/aiva/telas/login.fxml"));
        Scene scene = new Scene(root, 400, 700); // ✅ Define tamanho inicial

        stage.setScene(scene);
        stage.setMinWidth(400);
        stage.setMinHeight(800);
        stage.setResizable(true); // ✅ Permite redimensionamento, mas mantém a proporção

        stage.show();
    }

    public static void main(String[] args) {
        BancoDeDados.criarTabelaUsuarios();
        BancoDeDados.criarTabelaVendas();
        BancoDeDados.criarTabelaDespesas();
        launch(args); // Inicia a aplicação
    }
}
