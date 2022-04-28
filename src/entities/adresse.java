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
public class adresse {
    int id;
    String Nom;
    String Prenom;
    String Adress;
    String City;
    String Email;
    int Tel;
    
    

    public adresse() {
    }

    public adresse(int id, String Nom, String Prenom,String Adress,String City,String Email,int Tel) {
        this.id = id;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Adress = Adress;
        this.City = City;
        this.Email = Email;
        this.Tel = Tel;
        
        
    }
    

    

    public adresse(String Nom, String Prenom,String Adress,String City,String Email,int Tel) {
          this.Nom = Nom;
        this.Prenom = Prenom;
        this.Adress = Adress;
        this.City = City;
        this.Email = Email;
        this.Tel = Tel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }
    
    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }
    
     public String getAdress() {
        return Adress;
    }

    public void setAdress(String Adress) {
        this.Adress = Adress;
    }
    
     public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }
    
    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
     public int getTel() {
        return Tel;
    }

    public void setTel(int Tel) {
        this.Tel = Tel;
    }

    

    
    @Override
    public String toString() {
        return "adresse{" + "id=" + id + ", Nom=" + Nom + ", Prenom=" + Prenom + ", Adress=" + Adress +", City=" + City +", Email=" + Email + ", Tel=" + Tel +  '}';
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
        final adresse other = (adresse) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
