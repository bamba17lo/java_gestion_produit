package com.example.examen_javafx.model;

public class ProduitModel {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String libelle;
    private String quantite;
    private String prixU;
    private String IdCategorie;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public ProduitModel() {

    }

    public String getPrixU() {
        return prixU;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public void setPrixU(String prixU) {
        this.prixU = prixU;
    }

    public String getIdCategorie() {
        return IdCategorie;
    }

    public void setIdCategorie(String IdCategorie) {
        this.IdCategorie = IdCategorie;
    }

    public ProduitModel(String libelle, String quantite, String prixU, String IdCategorie) {
        this.libelle = libelle;
        this.quantite = quantite;
        this.prixU = prixU;
        this.IdCategorie = IdCategorie;
    }


}
