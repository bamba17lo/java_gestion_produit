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

}
