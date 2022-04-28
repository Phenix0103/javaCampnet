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
import entities.adresse;
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
 * @author sarra
 */
public class Serviceadresse {

    Connection cnx;

    public Serviceadresse() {
        Connexion instance = Connexion.getInstance();

    }

    public void Createadresse( adresse Ad) {
        try {
            String req = "INSERT INTO adresse(Nom,Prenom,Adress,City,Email,Tel) VALUES "
                    + "('" + Ad.getNom() + "','" + Ad.getPrenom() + "','" + Ad.getAdress() + "','" + Ad.getCity() + "','" + Ad.getEmail() + "','" + Ad.getTel() + "')";
            Statement st = DataSource.getInstance().getCnx().createStatement();  
            st.executeUpdate(req);
            System.out.println("Adresse ajoutée avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void Ajoutadresse(adresse Ad) {
        try {
            String req = "INSERT INTO adresse(Nom,Prenom,Adress,City,Email,Tel) VALUES "
                     + "('" + Ad.getNom() + "','" + Ad.getPrenom() + "','" + Ad.getAdress() + "','" + Ad.getCity() + "','" + Ad.getEmail() + "','" + Ad.getTel() + "')";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Adresse ajoutée avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<adresse> Readadresse() {
        List<adresse> plist = new ArrayList<>();
        try {
            String req = "select * from adresse";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                adresse Ad = new adresse();
                Ad.setId(rs.getInt("id"));
                Ad.setNom(rs.getString(2));
                Ad.setPrenom(rs.getString(2));
                Ad.setAdress(rs.getString(3));
                Ad.setCity(rs.getString(4));
                Ad.setEmail(rs.getString(5));
                Ad.setTel(rs.getInt("Tel"));
                
               
                plist.add(Ad);
            }
        } catch (Exception Ad) {
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
    public List<adresse> afficheradresse() throws SQLException {
        List<adresse> resulta = new ArrayList<>();

        Statement stm = cnx.createStatement();
        String query = "select * from adresse ";

        ResultSet resultat = stm.executeQuery(query);
        adresse Ad = new adresse();
        while (resultat.next()) {
            Ad.setId(resultat.getInt("id"));
            Ad.setNom(resultat.getString("Nom"));
            Ad.setPrenom(resultat.getString("Prenom"));
            Ad.setAdress(resultat.getString("Adress"));
            Ad.setCity(resultat.getString("City"));
            Ad.setEmail(resultat.getString("Email"));
            Ad.setTel(resultat.getInt("Tel"));
            

            resulta.add(Ad);
        }

        return resulta;
    }

    public void supprimeradresse(adresse Ad) {
        String req = "delete from adresse where id=?";

        try {
            PreparedStatement stm = DataSource.getInstance().getCnx().prepareStatement(req);
            stm.setInt(1, Ad.getId());
            int i = stm.executeUpdate();
            System.out.println(i + " adresse supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(int id, adresse Ad) {
        String updateStatement = "UPDATE adresse SET Nom= ? ,Prenom=? ,Adress=? ,City=? , Email=? , Tel=? WHERE id= ? ";

        try {
            PreparedStatement pre = DataSource.getInstance().getCnx().prepareStatement(updateStatement);
            pre.setString(1, Ad.getNom());
            pre.setString(2, Ad.getPrenom());
            pre.setString(3, Ad.getAdress());
            pre.setString(4, Ad.getCity());
            pre.setString(5, Ad.getEmail());
            pre.setInt(6, Ad.getTel());
            pre.setInt(7, id);
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

    public List<adresse> getAll() {
        ObservableList<adresse> myList = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM adresse";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                adresse Ad = new adresse();
                Ad.setId(rs.getInt(1));
               Ad.setNom(rs.getString("Nom"));
                Ad.setPrenom(rs.getString("Prenom"));
                Ad.setAdress(rs.getString("Adress"));
                Ad.setCity(rs.getString("City"));
                 Ad.setEmail(rs.getString("Email"));
                Ad.setTel(rs.getInt("Tel"));
               
               

                myList.add(Ad);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;

    }

    public ObservableList<adresse> read() throws SQLException {
        ObservableList<adresse> L = FXCollections.observableArrayList();

        Statement st = cnx.createStatement();
        String req = "select * from adresse";
        ResultSet rs = st.executeQuery(req);

        while (rs.next()) {
            int id = rs.getInt(1);
            String Nom = rs.getString("Nom");
            String Prenom = rs.getString("Prenom");
            String Adress = rs.getString("Adress");
            String City = rs.getString("City");
            String Email = rs.getString("Email");
            int Tel = rs.getInt("Tel");
            
            

            adresse Ad = new adresse(id, Nom, Prenom, Adress, City, Email, Tel);

            L.add(Ad);
        }

        return L;

    }

}
