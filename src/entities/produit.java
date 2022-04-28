/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author sarra
 */
public class produit {
    int id;
    String nom;
    String photo;
    String description;
    String disponibilte;
    Double prix;

    public produit() {
    }

    public produit(int id, String nom, String photo, String description, String disponibilte, Double prix) {
        this.id = id;
        this.nom = nom;
        this.photo = photo;
        this.description = description;
        this.disponibilte = disponibilte;
        this.prix = prix;
    }
    

      public produit(String nom) {
        this.nom = nom;
       
    }
          public produit(Double prix) {
        this.prix = prix;
       
    }

    public produit(String nom, String photo, String description, String disponibilte, Double prix) {
        this.nom = nom;
        this.photo = photo;
        this.description = description;
        this.disponibilte = disponibilte;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisponibilte() {
        return disponibilte;
    }

    public void setDisponibilte(String disponibilte) {
        this.disponibilte = disponibilte;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "produit{" + "id=" + id + ", nom=" + nom + ", photo=" + photo + ", description=" + description + ", disponibilte=" + disponibilte + ", prix=" + prix + '}';
    }
    
     @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final produit other = (produit) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
