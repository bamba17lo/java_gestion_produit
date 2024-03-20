package com.example.examen_javafx;

import com.example.examen_javafx.model.CategorieModel;
import com.example.examen_javafx.model.ProduitModel;
import com.example.examen_javafx.repository.CategorieRepository;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CategorieController implements Initializable {
    @FXML
    private TextField cLibelle;
    @FXML
    private TextField cId;
    private CategorieRepository categorieRepository;
    @FXML
    private TableColumn<CategorieModel, Integer> col_Id;

    @FXML
    private TableColumn<CategorieModel, String> col_lib;

    @FXML
    private TableColumn<?, ?> col_option;

    @FXML
    private TableView<CategorieModel> tableCategorie;


    // les methodes
    @FXML


    void btn_Ajout(ActionEvent event) {
        CategorieModel categorie = new CategorieModel(cLibelle.getText());
        categorieRepository.AjoutCategorie(categorie);
        // effacer les valeurs
        cLibelle.setText("");
        // affichage des valeus
        this.affiche();

    }

    @FXML
    void btn_deleteCategorie(ActionEvent event) {
        int id  =this.tableCategorie.getSelectionModel().getSelectedItem().getId();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Attention");
        alert.setHeaderText("Voulez-vous supprimer ce categorie ?");
//        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            this.categorieRepository.delete(id);
            cLibelle.setText("");
            cId.setText("");
        } else {
            cLibelle.setText("");
            cId.setText("");
            // ... user chose CANCEL or closed the dialog
        }


        this.affiche();
    }

    @FXML
    void btn_modifierCategorie(ActionEvent event) {
        CategorieModel categorie = new CategorieModel(cLibelle.getText());
        categorie.setId(Integer.parseInt(cId.getText()));
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Attention");
        alert.setHeaderText("Voulez-vous modifier ces informations ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            categorieRepository.update(categorie);
            cLibelle.setText("");
            cId.setText("");
        } else {
            // ... user chose CANCEL or closed the dialog
        }
        this.affiche();

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.categorieRepository = new CategorieRepository();
        this.affiche();
    }

    void affiche(){
        ObservableList<CategorieModel> list =  categorieRepository.getAll();
        col_Id.setCellValueFactory(new PropertyValueFactory<CategorieModel,Integer>("id"));
        col_lib.setCellValueFactory(new PropertyValueFactory<CategorieModel,String>("libelle"));
        tableCategorie.setItems(list);
    }

    @FXML
    void doubleClick(MouseEvent event) {
        CategorieModel categorie = new CategorieModel();
        categorie=tableCategorie.getSelectionModel().getSelectedItem();
        if(event.getClickCount()==2){
            cLibelle.setText(categorie.getLibelle());
            cId.setText(categorie.getId()+"");

        }
    }

}
