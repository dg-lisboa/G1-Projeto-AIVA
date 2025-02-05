module com.seuprojeto.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; // Adiciona o módulo java.sql
    requires com.dlsc.formsfx;

    opens com.seuprojeto.demo to javafx.fxml;
    exports com.seuprojeto.demo;
}
