/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import entities.livreur;
import java.sql.Date;
import entities.adresse;
/**
 *
 * @author sarra
 */
public class commande {
   int idcommande;
   livreur liv;
   adresse Adr;
    String Produit;
    int Quantite;
    double Total;
    Date date;
    

    public commande() {
    }
    
    
       public commande( double Total) {
             this.Total = Total;
    }

    public commande(int idcommande, livreur liv, adresse Adr, String Produit, int Quantite, double Total,Date date) {
        this.idcommande = idcommande;
        
        this.Produit = Produit;
        this.Quantite = Quantite;
        this.Total = Total;
        this.date = date;
        this.liv = liv;
        this.Adr = Adr;
        
    }

   

    



    public int getidcommande() {
        return idcommande;
    }

    public void setidcommande(int idcommande) {
        this.idcommande = idcommande;
    }

    public livreur getLiv() {
        return liv;
    }

    public void setLiv(livreur liv) {
        this.liv = liv;
    }
                      
                            

    public adresse getAdr() {
        return Adr;
    }

    public void setadr_id(adresse Adr) {
        this.Adr = Adr;
    }

    public String getProduit() {
        return Produit;
    }

    public void setProduit(String Produit) {
        this.Produit = Produit;
    }

    public int getQuantite() {
        return Quantite;
    }

    public void setQuantite(int Quantite) {
        this.Quantite = Quantite;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double Total) {
        this.Total = Total;
    }
    
    public Date getdate() {
        return date;
    }

    public void setdate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "commande{" + "idcommande=" + idcommande + ", liv=" + liv + ", Adr=" + Adr + ", Produit=" + Produit + ", Quantite=" + Quantite + ", Total=" + Total + ", date=" + date + '}';
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
        final commande other = (commande) obj;
        if (this.idcommande != other.idcommande) {
            return false;
        }
        return true;
    }
    
}
