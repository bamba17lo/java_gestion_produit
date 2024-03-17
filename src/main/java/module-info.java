module com.example.examen_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.examen_javafx to javafx.fxml;
    exports com.example.examen_javafx;
    exports com.example.examen_javafx.model;
}