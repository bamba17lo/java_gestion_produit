package com.example.examen_javafx.repository;

import com.example.examen_javafx.model.BD;
import com.example.examen_javafx.model.CategorieModel;
import com.example.examen_javafx.model.ProduitModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProduitRepository {
    private static Connection connection;

    public ProduitRepository() {
        this.connection = new BD().getConnection();
    }

    public static void AjoutProduit(ProduitModel produit){
        try {
            String sql = "INSERT produit (libelle,quantite,prixU,idCategorie) values (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, produit.getLibelle());
            statement.setString(2, produit.getQuantite());
            statement.setString(3, produit.getPrixU());
            statement.setString(4, produit.getIdCategorie());
            statement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<ProduitModel> getAll() {
        ObservableList<ProduitModel> list = FXCollections.observableArrayList();
        try {

            String sql =  "SELECT * FROM produit ";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                ProduitModel c =  new ProduitModel();
                c.setId(result.getInt("id"));
                c.setLibelle(result.getString("libelle"));
                c.setQuantite(result.getString("quantite"));
                c.setPrixU(result.getString("prixU"));
                c.setIdCategorie(result.getString("IdCategorie"));

                list.add(c);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
