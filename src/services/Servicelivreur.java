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
import entities.livreur;
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
public class Servicelivreur {

    Connection cnx;

    public Servicelivreur() {
        Connexion instance = Connexion.getInstance();

    }

    public void Createlivreur( livreur Li) {
        try {
            String req = "INSERT INTO livreur(Nom,Prenom,Tel,Email) VALUES "
                    + "('" + Li.getNom() + "'" + ",'" + Li.getPrenom() + "','" + Li.getTel() + "'" + ",'" + Li.getEmail() + "')";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("livreur ajouté avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void Ajoutlivreur(livreur Li) {
        try {
            String req = "INSERT INTO livreur(id,Nom,Prenom,Tel,Email) VALUES "
                    + "('" + Li.getId() + "','" + Li.getNom() + "','" + Li.getPrenom() +"','"+ Li.getTel() + "','" + Li.getEmail() + "')";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("livreur ajouté avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<livreur> Readlivreur() {
        List<livreur> plist = new ArrayList<>();
        try {
            String req = "select * from livreur";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                livreur Li = new livreur();
                Li.setId(rs.getInt("id"));
                Li.setNom(rs.getString(2));
                Li.setPrenom(rs.getString(3));
                Li.setTel(rs.getInt("Tel"));
                Li.setEmail(rs.getString(4));
               
                plist.add(Li);
            }
        } catch (Exception Li) {
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
    public List<livreur> afficherlivreur() throws SQLException {
        List<livreur> resulta = new ArrayList<>();

        Statement stm = cnx.createStatement();
        String query = "select * from livreur ";

        ResultSet resultat = stm.executeQuery(query);
        livreur Li = new livreur();
        while (resultat.next()) {
            Li.setId(resultat.getInt("id"));
            Li.setNom(resultat.getString("Nom"));
            Li.setPrenom(resultat.getString("Prenom"));
            Li.setTel(resultat.getInt("Tel"));
            Li.setEmail(resultat.getString("Email"));
            

            resulta.add(Li);
        }

        return resulta;
    }

    public void supprimerlivreur(livreur Li) {
        String req = "delete from livreur where id=?";

        try {
            PreparedStatement stm = DataSource.getInstance().getCnx().prepareStatement(req);
            stm.setInt(1, Li.getId());
            int i = stm.executeUpdate();
            System.out.println(i + " livreur supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(int id, livreur Li) {
        String updateStatement = "UPDATE livreur SET Nom= ? ,Prenom=?, Tel=?, Email=? WHERE id= ? ";

        try {
            PreparedStatement pre = DataSource.getInstance().getCnx().prepareStatement(updateStatement);
            pre.setString(1, Li.getNom());
            pre.setString(2, Li.getPrenom());
            pre.setInt(3, Li.getTel());
            pre.setString(4, Li.getEmail());
            pre.setInt(5, id);
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

    public List<livreur> getAll() {
        ObservableList<livreur> myList = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM livreur";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                livreur Li = new livreur();
                Li.setId(rs.getInt(1));
               Li.setNom(rs.getString("Nom"));
                Li.setPrenom(rs.getString("Prenom"));
                Li.setTel(rs.getInt("Tel"));
                Li.setEmail(rs.getString("Email"));
               

                myList.add(Li);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;

    }

    public ObservableList<livreur> read() throws SQLException {
        ObservableList<livreur> L = FXCollections.observableArrayList();

        Statement st = cnx.createStatement();
        String req = "select * from livreur";
        ResultSet rs = st.executeQuery(req);

        while (rs.next()) {
            int id = rs.getInt(1);
            String Nom = rs.getString("Nom");
            String Prenom = rs.getString("Prenom");
            int Tel = rs.getInt("Tel");
            String Email = rs.getString("Email");
            

            livreur Li = new livreur(id, Nom, Prenom, Tel, Email);

            L.add(Li);
        }

        return L;

    }

}
