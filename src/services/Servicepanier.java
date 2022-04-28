/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;



import tools.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entities.panier;
import utils.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;
import entities.produit;
/**
 *
 * @author sarra
 */
public class Servicepanier {

    Connection cnx;
    Statement st;

    public Servicepanier() {
        Connexion instance = Connexion.getInstance();

    }

   public void AjouterAuPanier(panier Panier) {
        try {
            st = cnx.createStatement();
            String Query = " INSERT INTO `panier`( `id`, `nom`, `quantite`,  `prix`) VALUES "
                    + "('" + Panier.getId() + "','" + Panier.getNom() + "','" + Panier.getQuantite() + "','" + Panier.getPrix() + "')";
            st.executeUpdate(Query);
            System.out.println("element ajoute au panier");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
   public void SupprimerArticleDuPanier(String id) {
        try {
            st = cnx.createStatement();
            String Query = "DELETE  FROM `panier` WHERE `id` = '" + id + "'";
            st.executeUpdate(Query);

            System.out.println("article retirer de la panier");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public List<String> consulterPanierParClient(int id) {
        Serviceproduit sa = new Serviceproduit();
        produit P = new produit();
       List<String> monPanier= new ArrayList<String>();
        try {
             st=cnx.createStatement();
             
             String Query="SELECT * FROM `panier` WHERE `idt`="+id;
             
             ResultSet rs =st.executeQuery(Query);
             
             while(rs.next()){
                 panier Panier = new panier();
                 Panier.setId(rs.getInt("id"));
                 Panier.setNom(rs.getString("nom"));
                 P=sa.searchByReference(Panier.getId());
                 Panier.setquantite(rs.getInt("quantite"));
                 Panier.setPrix(rs.getDouble("prix"));
                 monPanier.add("id : "+P.getId()+" , quantite : "+Panier.getquantite() +" , Prix : "+ P.getPrix()*Panier.getquantite());
             }
             
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
      
       
       
        
        return monPanier;
    }

}