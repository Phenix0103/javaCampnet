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
import entities.CentreCamp;
import tools.Connexion;
import utils.DataSource;

/**
 *
 * @author ASUS
 */
public class CentreCampService {
    
    
         Connexion instance = Connexion.getInstance();
     
    Connection cnx;

 /**
  * public void CreateCentreCamp(CentreCamp c) {
        try {
            String req = "INSERT INTO centre_camp(nom_centre,Description_centre,img_centre,lieux,tlf_centre) VALUES "
                    + "('" + c.getNom_centre()+ "'" + ",'" + c.getDescription_centre() + "','" + c.getImg_centre()+ "'" + ",'" + c.getLieux()+ "','" + c.getTlf_centre() + "')";
            Statement st = MaConnexion.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Centre ajouté avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     * @param c
    **/
 
 
  public void AjoutCentreCamp(CentreCamp c) {
        try {
            String req = "INSERT INTO centrecamp(nom_centre,Description_centre,img_centre,lieux,tlf_centre,mail_centre) VALUES "
                    + "('" + c.getNom_centre()+ "'" + ",'" + c.getDescription_centre() + "','" + c.getImg_centre()+ "'" + ",'" + c.getLieux()+ "','" + c.getTlf_centre() + "','" + c.getMail_centre() + "')";
            Statement st = Connexion.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("evenement ajouté avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
  
  
  public List<CentreCamp> AfficherCentre() {
        List<CentreCamp> plist = new ArrayList<>();
        try {
            String req = "select * from centrecamp";
            Statement st = Connexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                CentreCamp c = new CentreCamp();
                c.setId(rs.getInt("id"));
                c.setNom_centre(rs.getString(2));
                c.setDescription_centre(rs.getString(3));
                c.setImg_centre(rs.getString(4));
                c.setLieux(rs.getString(5));
                c.setTlf_centre(rs.getString(6));
                c.setMail_centre(rs.getString(7));

                plist.add(c);
            }
        } catch (Exception e) {
        }
        return plist;
    }
  
   public void supprimeCentreCamp(CentreCamp c) {
        String req = "delete from centrecamp where id=?";

        try {
            PreparedStatement stm = Connexion.getInstance().getCnx().prepareStatement(req);
            stm.setInt(1, (int) c.getId());
            int i = stm.executeUpdate();
            System.out.println(i + " centrecamp suprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
   
    public void modifier(int id, CentreCamp c) {
        String updateStatement = "UPDATE evenement SET nom_centre= ? ,Description_centre=?, img_centre=?, lieux=?, tlf_centre=?, mail_centre=? WHERE id= ? ";

        try {
            PreparedStatement pre = Connexion.getInstance().getCnx().prepareStatement(updateStatement);
            pre.setString(1, c.getNom_centre());
            pre.setString(2, c.getDescription_centre());
            pre.setString(3, c.getImg_centre());
            pre.setString(4, c.getLieux());
            pre.setString(5, c.getTlf_centre());
            pre.setString(5, c.getMail_centre());
            pre.setInt(6, id);
            pre.executeUpdate();
            System.out.println("Record Update successfully from database.:!!: ");
        } catch (SQLException m) {
            System.out.println(m.getMessage());
        }

    }
  
    
      public List<CentreCamp> getAll() {
        ObservableList<CentreCamp> myList = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM centrecamp";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                CentreCamp c = new CentreCamp();
                c.setId(rs.getInt(1));
                c.setNom_centre(rs.getString("Nom_centre"));
                c.setDescription_centre(rs.getString("Description_centre"));
                c.setImg_centre(rs.getString("Img_centre"));
                c.setLieux(rs.getString("Lieux"));
                c.setTlf_centre(rs.getString("Tlf_centre"));
                c.setTlf_centre(rs.getString("Mail_centre"));

                myList.add(c);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;

    }
 
 
  public ObservableList<CentreCamp> read() throws SQLException {
        ObservableList<CentreCamp> L = FXCollections.observableArrayList();

        Statement st = cnx.createStatement();
        String req = "select * from cenrecamp";
        ResultSet rs = st.executeQuery(req);

        while (rs.next()) {
            int id = rs.getInt(1);
            String Nom_centre = rs.getString("Nom_centre");
            String Description_centre = rs.getString("Description_centre");
            String Img_centre = rs.getString("Img_centre");
            String Lieux = rs.getString("Lieux");
            String Tlf_centre = rs.getString("Tlf_centre");
            String Mail_centre = rs.getString("Mail_centre");

            CentreCamp e = new CentreCamp(id, Nom_centre, Description_centre, Img_centre, Lieux, Tlf_centre, Mail_centre);

            L.add(e);
        }

        return L;

    }

 
 
 
 
 
 
 }


