package com.example.examen_javafx;

import com.example.examen_javafx.model.BD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    private static Connection connection;
    @FXML
    private AnchorPane dynamique;
    @FXML
    void btn_Statistique(ActionEvent event) throws IOException {
        this.connection = new BD().getConnection();
        System.out.println("statistique");
        // affichage de la page statistique
        Parent fxml = FXMLLoader.load(getClass().getResource("statistique-view.fxml"));
        // Supprime tout les element du children
        dynamique.getChildren().removeAll();
        // Affiche les element du statistique
        dynamique.getChildren().setAll(fxml);
    }

    @FXML
    void btn_Categorie(ActionEvent event) throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("categorie-view.fxml"));
        // Supprime tout les element du children
        dynamique.getChildren().removeAll();
        // Affiche les element du statistique
        dynamique.getChildren().setAll(fxml);
    }

    @FXML
    void btn_Document(ActionEvent event) throws IOException {
        System.out.println("Document");
        System.out.println("Produit");
        Parent fxml = FXMLLoader.load(getClass().getResource("document-view.fxml"));
        // Supprime tout les element du children
        dynamique.getChildren().removeAll();
        // Affiche les element du statistique
        dynamique.getChildren().setAll(fxml);
    }

    @FXML
    void btn_Product(ActionEvent event) throws IOException {
        System.out.println("Produit");
        Parent fxml = FXMLLoader.load(getClass().getResource("produit-view.fxml"));
        // Supprime tout les element du children
        dynamique.getChildren().removeAll();
        // Affiche les element du statistique
        dynamique.getChildren().setAll(fxml);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }
}