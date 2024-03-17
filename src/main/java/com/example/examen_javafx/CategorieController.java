package com.example.examen_javafx;

import com.example.examen_javafx.model.CategorieModel;
import com.example.examen_javafx.repository.CategorieRepository;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CategorieController implements Initializable {
    @FXML
    private TextField cLibelle;
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


}
