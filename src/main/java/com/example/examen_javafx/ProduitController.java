package com.example.examen_javafx;

import com.example.examen_javafx.model.BD;
import com.example.examen_javafx.model.CategorieModel;
import com.example.examen_javafx.model.ProduitModel;
import com.example.examen_javafx.repository.CategorieRepository;
import com.example.examen_javafx.repository.PDFGenerate;
import com.example.examen_javafx.repository.ProduitRepository;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProduitController implements Initializable {
    @FXML
    private ComboBox<String> cCategorie;

    private CategorieRepository categorieRepository;
    private ProduitRepository produitRepository;
    @FXML
    private TextField cLibelle;
    @FXML
    private TextField cId;
    @FXML
    private TextField cPrix;

    @FXML
    private TextField cMois;

    @FXML
    private TextField cQuantite;



    @FXML
    private TableView<ProduitModel> cTable;

    @FXML
    private TableColumn<CategorieModel, String> col_categorie;

    @FXML
    private TableColumn<CategorieModel, Integer> col_id;

    @FXML
    private TableColumn<CategorieModel, String> col_lib;

    @FXML
    private TableColumn<CategorieModel, String> col_pU;

    @FXML
    private TableColumn<CategorieModel, String> col_quantite;

    @FXML
    private TableColumn<?, ?> colo_option;

    @FXML
    void btn_ajoutProduit(ActionEvent event) {
        int IdCategorie=0;
        String selectLib = cCategorie.getSelectionModel().getSelectedItem();
        ObservableList<CategorieModel> categories = categorieRepository.getAll();
        for (CategorieModel categorie : categories){
                if (categorie.getLibelle().equals(selectLib)){
                     IdCategorie = categorie.getId();
                     break;
                }

            }
        // ajouter dans la table
        ProduitModel produit = new ProduitModel(cLibelle.getText(),cQuantite.getText(),cPrix.getText(),String.valueOf(IdCategorie));
        produitRepository.AjoutProduit(produit);

        cLibelle.setText("");
        cQuantite.setText("");
        cPrix.setText("");
        cCategorie.setValue("");
        this.affiche();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.categorieRepository = new CategorieRepository();
        this.produitRepository = new ProduitRepository();

        ObservableList<String>  label = categorieRepository.getLib();
        cCategorie.setItems(label);
        this.affiche();
    }

    @FXML
    void btn_PDF(ActionEvent event) throws IOException {
        List<ProduitModel> produits = produitRepository.getAll();
        PDFGenerate pdfGenerator = new PDFGenerate();
        String filePath = "liste_produits.pdf";
        pdfGenerator.generatePDF(produits, filePath);

    }


    @FXML
    void btn_Excel(ActionEvent event) throws SQLException {
        // Récupérer la liste des produits depuis votre modèle ou service
        List<ProduitModel> produits = produitRepository.getAll();
        List<CategorieModel> categories = categorieRepository.getNombreProduitsParCategorie();

        // Créer le fichier Excel
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Liste des produits");

            // Créer l'en-tête du tableau Excel
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Libellé");
            headerRow.createCell(2).setCellValue("Nombre de Produit");
            // Ajoutez d'autres en-têtes de colonnes au besoin...

            // Remplir le tableau avec les données des produits
            int rowNum = 1;
            for (CategorieModel categorie : categories) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(categorie.getId());
                row.createCell(1).setCellValue(categorie.getLibelle());
                row.createCell(2).setCellValue(categorie.getNombreProduits());
                // Ajoutez d'autres données de produit au besoin...
            }

            // Écrire le contenu dans le fichier Excel
            try (FileOutputStream fileOut = new FileOutputStream("liste_produits.xlsx")) {
                workbook.write(fileOut);
            }

            System.out.println("Fichier Excel créé avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void btn_deleteProduit(ActionEvent event) {
        int id  =this.cTable.getSelectionModel().getSelectedItem().getId();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Attention");
        alert.setHeaderText("Voulez-vous supprimer ce produit ?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK){
            this.produitRepository.delete(id);
            cId.setText("");
            cLibelle.setText("");
            cQuantite.setText("");
            cPrix.setText("");
            cCategorie.setValue("");
        } else {
            cId.setText("");
            cLibelle.setText("");
            cQuantite.setText("");
            cPrix.setText("");
            cCategorie.setValue("");
            // ... user chose CANCEL or closed the dialog
        }

        this.affiche();
    }

    @FXML
    void btn_modifierProduit(ActionEvent event) {
        int IdCategorie=0;
        String selectLib = cCategorie.getSelectionModel().getSelectedItem();
        ObservableList<CategorieModel> categories = categorieRepository.getAll();
        for (CategorieModel categorie : categories){
            if (categorie.getLibelle().equals(selectLib)){
                IdCategorie = categorie.getId();
                break;
            }
        }

        // Modification
        ProduitModel produit = new ProduitModel(cLibelle.getText(),cQuantite.getText(),cPrix.getText(),String.valueOf(IdCategorie));
        produit.setId(Integer.parseInt(cId.getText()));

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Attention");
        alert.setHeaderText("Voulez-vous modifier ces informations ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            produitRepository.update(produit);

        } else {
            // ... user chose CANCEL or closed the dialog
        }





        this.affiche();

        cId.setText("");
        cLibelle.setText("");
        cQuantite.setText("");
        cPrix.setText("");
        cCategorie.setValue("");

    }

    @FXML
    void doubleclique(MouseEvent event) {
        ProduitModel produit = new ProduitModel();
        produit=cTable.getSelectionModel().getSelectedItem();
        if(event.getClickCount()==2){
            cLibelle.setText(produit.getLibelle());
            cQuantite.setText(produit.getQuantite());
            cPrix.setText(produit.getPrixU());
            cCategorie.setValue(produit.getCategorieLibelle());
            cId.setText(produit.getId()+"");

        }
    }

    void affiche(){
        ObservableList<ProduitModel> list =  produitRepository.getAll();
        col_id.setCellValueFactory(new PropertyValueFactory<CategorieModel,Integer>("id"));
        col_lib.setCellValueFactory(new PropertyValueFactory<CategorieModel,String>("libelle"));
        col_quantite.setCellValueFactory(new PropertyValueFactory<CategorieModel,String>("quantite"));
        col_pU.setCellValueFactory(new PropertyValueFactory<CategorieModel,String>("prixU"));
        col_categorie.setCellValueFactory(new PropertyValueFactory<CategorieModel,String>("categorieLibelle"));
        cTable.setItems(list);
    }
}
