package com.example.examen_javafx;

import com.example.examen_javafx.model.UserModel;
import com.example.examen_javafx.repository.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    private UserRepository userRepository;

    @FXML
    private TextField cEmail;

    @FXML
    private TextField cLogin;

    @FXML
    private TextField cNom;

    @FXML
    private TextField cPassword;

    @FXML
    private TextField cPrenom;

    @FXML
    private TextField cTelephone;

    @FXML
    void btn_Inscription(ActionEvent event) throws IOException {
        UserModel user = new UserModel(cNom.getText(),cPrenom.getText(),cTelephone.getText(),cEmail.getText(),cLogin.getText(),cPassword.getText());
        userRepository.AjoutUser(user);

        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.close();

        // Show the windows
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 450, 400);
        stage.setTitle("Gestion Produit");
        stage.setScene(scene);
        stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.userRepository = new UserRepository();
    }
}
