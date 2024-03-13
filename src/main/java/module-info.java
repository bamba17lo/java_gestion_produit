module com.example.examen_javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.examen_javafx to javafx.fxml;
    exports com.example.examen_javafx;
}