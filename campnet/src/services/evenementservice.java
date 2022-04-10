/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.evenement;

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
import entities.evenement;
import utils.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author POSTE HP
 */
public class evenementservice {

    Connection cnx;

    public evenementservice() {
        Connexion instance = Connexion.getInstance();

    }

    public void CreateEvent(evenement e) {
        try {
            String req = "INSERT INTO evenement(nom,photo,description,lieu,prix) VALUES "
                    + "('" + e.getNom() + "'" + ",'" + e.getPhoto() + "','" + e.getDescription() + "'" + ",'" + e.getLieu() + "','" + e.getPrix() + "')";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("evenement ajouté avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void AjoutEvenement(evenement e) {
        try {
            String req = "INSERT INTO evenement(id,nom,photo,description,lieu,prix) VALUES "
                    + "('" + e.getId() + "','" + e.getNom() + "','" + e.getPhoto() + "','" + e.getDescription() + "','" + e.getLieu() + "','" + e.getPrix() + "')";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("evenement ajouté avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<evenement> Readevent() {
        List<evenement> plist = new ArrayList<>();
        try {
            String req = "select * from evenement";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                evenement e = new evenement();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString(2));
                e.setPhoto(rs.getString(3));
                e.setDescription(rs.getString(4));
                e.setLieu(rs.getString(5));
                e.setPrix(rs.getDouble(6));

                plist.add(e);
            }
        } catch (Exception e) {
        }
        return plist;
    }


    /*
   public void Addevent(evenement e) throws SQLException  {
  
        String req ="INSERT INTO event (nom,photo,description,lieu,prix) values (?,?,?,?,?,?)";
        
        try {
            
            PreparedStatement stm = cnx.prepareStatement(req);
             stm.setString(1, e.getNom());
             stm.setString(2, e.getPhoto());
             stm.setString(3,e.getDescription());
             stm.setString(4,e.getLieu());
             stm.setDouble(5, e.getPrix());
             
             stm.executeUpdate();
             System.out.println("Evenement ajouté");
                     
        } catch (SQLException ex) {
            System.out.println("probleme");
            System.out.println(ex.getMessage());
        }       
    }
     */
    public List<evenement> afficherEvenement() throws SQLException {
        List<evenement> resulta = new ArrayList<>();

        Statement stm = cnx.createStatement();
        String query = "select * from evenement ";

        ResultSet resultat = stm.executeQuery(query);
        evenement e = new evenement();
        while (resultat.next()) {
            e.setId(resultat.getInt("id"));
            e.setNom(resultat.getString("nom"));
            e.setLieu(resultat.getString("photo"));
            e.setDescription(resultat.getString("description"));
            e.setLieu(resultat.getString("lieu"));
            e.setPrix(resultat.getDouble("prix"));

            resulta.add(e);
        }

        return resulta;
    }

    public void supprimerevent(evenement e) {
        String req = "delete from evenement where id=?";

        try {
            PreparedStatement stm = DataSource.getInstance().getCnx().prepareStatement(req);
            stm.setInt(1, e.getId());
            int i = stm.executeUpdate();
            System.out.println(i + " Evenement suprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(int id, evenement e) {
        String updateStatement = "UPDATE evenement SET Nom= ? ,photo=?, description=?, lieu=?, prix=? WHERE id= ? ";

        try {
            PreparedStatement pre = DataSource.getInstance().getCnx().prepareStatement(updateStatement);
            pre.setString(1, e.getNom());
            pre.setString(2, e.getPhoto());
            pre.setString(3, e.getDescription());
            pre.setString(4, e.getLieu());
            pre.setDouble(5, e.getPrix());
            pre.setInt(6, id);
            pre.executeUpdate();
            System.out.println("Record Update successfully from database.:!!: ");
        } catch (SQLException m) {
            System.out.println(m.getMessage());
        }

    }

    /*
      public int Modifierevent(int id,evenement e, PreparedStatement pre) throws SQLException {
        if(chercher(id)){
        
        pre=cnx.prepareStatement("UPDATE event SET nom = ? , photo = ? , description = ? , lieu = ? , prix = ? WHERE id = "+id+"");
    try{     
             pre.setString(1, e.getNom());
             pre.setString(2, e.getPhoto());
             pre.setString(3,e.getDescription());
             pre.setString(4,e.getLieu());
             pre.setDouble(5, e.getPrix());
    
    
    pre.executeUpdate();
    }
    catch (SQLException m){
      System.out.println(m.getMessage());  
    }
    return 1;}
        return 0;
    } 
      /*
      public boolean chercher(int id) throws SQLException {
        String req="select * from event";
        List<Integer> list = new ArrayList<>();
        
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(evenementservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        list.forEach(System.out::println);
        return list.contains(id);
    }
      
       /*
      public ObservableList<evenement> FindEvent() {

        ObservableList<evenement> list = FXCollections.observableArrayList();
        try {
            String requete4 = "SELECT * FROM event";
            Statement st5 = Connexion.getInstance().getConnection.createStatement();
            ResultSet rs = st5.executeQuery(requete4);
            while (rs.next()) {
                evenement e = new evenement();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setLieu(rs.getString("lieu"));
                e.setDescription(rs.getString("description"));
          
              
                list.add(e);
            }

        } catch (SQLException ex) {
            System.out.println("error");
        }
        return list;

    }*/

    public List<evenement> getAll() {
        ObservableList<evenement> myList = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM event";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                evenement e = new evenement();
                e.setId(rs.getInt(1));
                e.setNom(rs.getString("nom"));
                e.setPhoto(rs.getString("photo"));
                e.setDescription(rs.getString("description"));
                e.setLieu(rs.getString("lieu"));
                e.setPrix(rs.getDouble("prix"));

                myList.add(e);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;

    }

    public ObservableList<evenement> read() throws SQLException {
        ObservableList<evenement> L = FXCollections.observableArrayList();

        Statement st = cnx.createStatement();
        String req = "select * from event";
        ResultSet rs = st.executeQuery(req);

        while (rs.next()) {
            int id = rs.getInt(1);
            String nom = rs.getString("nom");
            String photo = rs.getString("photo");
            String description = rs.getString("description");
            String lieu = rs.getString("lieu");
            Double prix = rs.getDouble("prix");

            evenement e = new evenement(id, nom, photo, description, lieu, prix);

            L.add(e);
        }

        return L;

    }

}
