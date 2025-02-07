module com.seuprojeto.aiva {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;  // Caso esteja usando banco de dados

    opens com.seuprojeto.aiva to javafx.fxml;  // Necessário para carregar arquivos FXML
    exports com.seuprojeto.aiva;
    exports com.seuprojeto.aiva.dados;
    opens com.seuprojeto.aiva.dados to javafx.fxml;
    exports com.seuprojeto.aiva.servicos;
    opens com.seuprojeto.aiva.servicos to javafx.fxml;
    exports com.seuprojeto.aiva.telas;
    opens com.seuprojeto.aiva.telas to javafx.fxml;
    exports com.seuprojeto.aiva.utilitarios;
    opens com.seuprojeto.aiva.utilitarios to javafx.fxml;  // Expõe o pacote para o uso
}
