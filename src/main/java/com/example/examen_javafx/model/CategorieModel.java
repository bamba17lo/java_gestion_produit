package com.example.examen_javafx.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CategorieModel {

    private int id;
    private String libelle;

    public String getNombreProduits() {
        return nombreProduits;
    }

    public void setNombreProduits(String nombreProduits) {
        this.nombreProduits = nombreProduits;
    }

    private String nombreProduits;

    public static Connection connection;


    public CategorieModel(String libelle, int id) {
        this.libelle = libelle;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CategorieModel() {
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public CategorieModel(String libelle) {
        this.libelle = libelle;
    }



}
