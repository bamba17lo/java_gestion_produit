package com.example.examen_javafx.model;

public class CategorieModel {

    private int id;
    private String libelle;

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
