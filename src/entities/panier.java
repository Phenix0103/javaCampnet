/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;



/**
 *
 * @author sarra
 */
public class panier {
    int id;
    String nom;
    int quantite;
    Double prix;
    produit prod;
    commande commande;

    public commande getCommande() {
        return commande;
    }

    public void setCommande(commande commande) {
        this.commande = commande;
    }
    public panier() {
    }

    public panier(int id, String nom, int quantite, Double prix) {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
        this.prix = prix;
    }
    


    public panier( produit prd) {
      
        this.prod = prd;
               
    }
    
    
    public panier(String nom) {
        this.nom = nom;
       
    }
    
       public panier( Double prix) {
        this.prix = prix;
       
    }

    public panier(String nom, int quantite, Double prix) {
        this.nom = nom;
        this.quantite = quantite;
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

   

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public produit getProd() {
        return prod;
    }

    public void setProd(produit prod) {
        this.prod = prod;
    }

 
    

    @Override
    public String toString() {
        return "panier{" + "id=" + id + ", nom=" + nom + ", quantite=" + quantite + ", prix=" + prix + '}';
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
        final panier other = (panier) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
