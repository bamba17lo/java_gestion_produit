module com.example.examen_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.pdfbox;
    requires kernel;
    requires layout;
    requires io;
    requires org.apache.poi.ooxml;


    opens com.example.examen_javafx to javafx.fxml;
    exports com.example.examen_javafx;
    exports com.example.examen_javafx.model;

}