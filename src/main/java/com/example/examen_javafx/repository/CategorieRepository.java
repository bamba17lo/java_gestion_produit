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
import java.util.ArrayList;
import java.util.List;

public class CategorieRepository {
    private static Connection connection;

    public CategorieRepository() {
        this.connection = new BD().getConnection();
    }

    public static void AjoutCategorie(CategorieModel categorieModel){
        try {
            String sql = "INSERT categorie (libelle) values (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, categorieModel.getLibelle());
            statement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<CategorieModel> getAll() {
        ObservableList<CategorieModel> list = FXCollections.observableArrayList();
        try {

            String sql =  "SELECT * from categorie ";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                CategorieModel c =  new CategorieModel();
                c.setId(result.getInt("id"));
                c.setLibelle(result.getString("libelle"));

                list.add(c);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ObservableList<String> getLib() {
        ObservableList<String> list = FXCollections.observableArrayList();
        try {

            String sql =  "SELECT libelle from categorie ";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while(result.next()) {

             String label = result.getString("libelle");

                list.add(label);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void delete(int id) {
        try {
            String sql = "DELETE from categorie  where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(CategorieModel categorie) {
        try {
            String sql = "UPDATE categorie SET libelle = ? where id =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, categorie.getLibelle());
            statement.setInt(2, categorie.getId());
            statement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CategorieModel> getNombreProduitsParCategorie() throws SQLException {
        List<CategorieModel> categories = new ArrayList<>();

        // Créer la requête SQL pour obtenir les catégories avec leur nombre de produits
        String sql = "SELECT c.id as id, c.libelle as libelle, COUNT(p.id) AS nombre_produits " +
                "FROM categorie c " +
                "LEFT JOIN produit p ON c.id = p.idCategorie " +
                "GROUP BY c.id, c.libelle";

        // Préparer la requête
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Exécuter la requête
            try (ResultSet resultSet = statement.executeQuery()) {
                // Parcourir les résultats et créer des objets CategorieModel
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String libelle = resultSet.getString("libelle");
                    String nombreProduits = resultSet.getString("nombre_produits");

                    // Créer un objet CategorieModel avec les données récupérées
                    CategorieModel categorie = new CategorieModel(libelle,id);
                    categorie.setNombreProduits(String.valueOf(nombreProduits));

                    // Ajouter l'objet à la liste des catégories
                    categories.add(categorie);
                }
            }
        }

        return categories;
    }
}
