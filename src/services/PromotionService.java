/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entities.Promo;
import tools.Connexion;
import utils.DataSource;

/**
 *
 * @author ASUS
 */
public class PromotionService {
  Connexion instance = Connexion.getInstance();     
    Connection cnx;
    
    
    public void AjouterPromo(Promo pm) {
        try {
            String req = "INSERT INTO promo(Nom_promo,nv_prix,Description_promo) VALUES "
                    + "('" + pm.getNom_promo() + "'" + ",'" + pm.getNv_prix() + "','" + pm.getDescription_promo() +"')";
            Statement st = Connexion.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("promo ajouté avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
      public List<Promo> AfficherPromo() {
        List<Promo> plist = new ArrayList<>();
        try {
            String req = "select * from promo";
            Statement st = Connexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Promo pm = new Promo();
                pm.setId(rs.getInt("id"));
                pm.setNom_promo(rs.getString(2));
                pm.setNv_prix(rs.getString(3));
                pm.setDescription_promo(rs.getString(4));
                

                plist.add(pm);
            }
        } catch (Exception e) {
        }
        return plist;
    }     
      
      public void supprimeCentreCamp(Promo pm) {
        String req = "delete from promo where id=?";

        try {
            PreparedStatement stm = Connexion.getInstance().getCnx().prepareStatement(req);
            stm.setInt(1, (int) pm.getId());
            int i = stm.executeUpdate();
            System.out.println(i + " Promo suprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
      
           
          public void modifier(int id, Promo pm) {
        String updateStatement = "UPDATE evenement SET Nom_promo= ? ,Nv_prix=?, Description_promo=? WHERE id= ? ";

        try {
            PreparedStatement pre = Connexion.getInstance().getCnx().prepareStatement(updateStatement);
            pre.setString(1, pm.getNom_promo());
            pre.setString(2, pm.getNv_prix());
            pre.setString(3, pm.getDescription_promo());

            pre.setInt(4, id);
            pre.executeUpdate();
            System.out.println("Record Update successfully from database.:!!: ");
        } catch (SQLException m) {
            System.out.println(m.getMessage());
        }

    }
 public List<Promo> getAll() {
        ObservableList<Promo> myList = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM promo";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Promo pm = new Promo();
                pm.setId(rs.getInt(1));
                pm.setNom_promo(rs.getString("Nom_promo"));
                pm.setNv_prix(rs.getString("Nv_prix"));
                pm.setDescription_promo(rs.getString("Description_promo"));
              

                myList.add(pm);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;

    }
 
 
  public ObservableList<Promo> read() throws SQLException {
        ObservableList<Promo> L = FXCollections.observableArrayList();

        Statement st = cnx.createStatement();
        String req = "select * from cenrecamp";
        ResultSet rs = st.executeQuery(req);

        while (rs.next()) {
            int id = rs.getInt(1);
            String Nom_promo = rs.getString("Nom_promo");
            String Nv_prix = rs.getString("Nv_prix");
            String Description_promo = rs.getString("Description_promo");
          

            Promo pm = new Promo(id, Nom_promo, Nv_prix, Description_promo );

            L.add(pm);
        }

        return L;

    }

    
}
