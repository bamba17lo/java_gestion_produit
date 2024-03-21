package com.example.examen_javafx;

import com.example.examen_javafx.model.SessionModel;
import com.example.examen_javafx.repository.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {

    UserRepository userRepository;
    @FXML
    private TextField cLogin;

    @FXML
    private TextField cpassword;
    @FXML
    void btnConnecter(ActionEvent event) throws IOException {
        // Login and password
        String login = cLogin.getText();
        String password = cpassword.getText();

       int verif =  userRepository.login(login,password);
       if (verif!=-1){
           SessionModel.getInstance().login(String.valueOf(verif));

        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.close();

        // Show the windows
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("accueil-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 550);
        stage.setTitle("Gestion Produit");
        stage.setScene(scene);
        stage.show();
       }else {
           // Aucun enregistrement correspondant trouvé, afficher une alerte
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Erreur");
           alert.setHeaderText(null);
           alert.setContentText("Identifiant ou mot de passe incorrect");
           alert.showAndWait();
       }
    }

    @FXML
    void btn_compte(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.close();

        // Show the windows
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("register-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("Gestion Produit");
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.userRepository = new UserRepository();
    }


}
