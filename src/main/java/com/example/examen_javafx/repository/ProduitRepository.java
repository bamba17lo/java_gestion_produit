package com.example.examen_javafx.repository;

import com.example.examen_javafx.model.BD;
import com.example.examen_javafx.model.ProduitModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

            String sql =  "SELECT p.*, c.libelle AS categorie_libelle " +
                    "FROM produit p " +
                    "JOIN categorie c ON p.IdCategorie = c.id";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                ProduitModel c =  new ProduitModel();
                c.setId(result.getInt("id"));
                c.setLibelle(result.getString("libelle"));
                c.setQuantite(result.getString("quantite"));
                c.setPrixU(result.getString("prixU"));
                c.setIdCategorie(result.getString("IdCategorie"));

                String categorieLibelle = result.getString("categorie_libelle");
                c.setCategorieLibelle(categorieLibelle);

                list.add(c);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void delete(int id) {
        try {
            String sql = "DELETE from produit  where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Produit supprimer");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(ProduitModel produit) {
        try {
            String sql = "UPDATE produit SET libelle = ? , quantite = ?, prixU = ? , idCategorie = ? where id =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, produit.getLibelle());
            statement.setString(2, produit.getQuantite());
            statement.setString(3, produit.getPrixU());
            statement.setString(4, produit.getIdCategorie());
            statement.setInt(5, produit.getId());
            statement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportProduitsToExcel() {
            }

}
