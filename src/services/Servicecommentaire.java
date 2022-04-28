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
import entities.commentaire;
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
public class Servicecommentaire {

    Connection cnx;

    public Servicecommentaire() {
        Connexion instance = Connexion.getInstance();

    }

    public void Createcommentaire(commentaire cmt) {
        try {
            String req = "INSERT INTO commentaire(Description) VALUES "
                    + "('" + cmt.getDescription() + "')";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("commentaire ajouté avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void Ajoutcommentaire(commentaire cmt) {
        try {
            String req = "INSERT INTO commentaire(id,Description) VALUES "
                    + "('" + cmt.getid() + "','" + cmt.getDescription() + "')";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("commentaire ajoutée avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<commentaire> Readcommentaire() {
        List<commentaire> plist = new ArrayList<>();
        try {
            String req = "select * from commentaire";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                commentaire cmt = new commentaire();
                cmt.setid(rs.getInt("id"));
                
               
                cmt.setDescription(rs.getString(2));
               
                plist.add(cmt);
            }
        } catch (Exception cmt) {
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
    public List<commentaire> affichercommentaire() throws SQLException {
        List<commentaire> resulta = new ArrayList<>();

        Statement stm = cnx.createStatement();
        String query = "select * from commentaire ";

        ResultSet resultat = stm.executeQuery(query);
        commentaire cmt = new commentaire();
        while (resultat.next()) {
            cmt.setid(resultat.getInt("id"));
            
            
            cmt.setDescription(resultat.getString("description"));
            

            resulta.add(cmt);
        }

        return resulta;
    }

    public void supprimercommentaire(commentaire cmt) {
        String req = "delete from commentaire where id=?";

        try {
            PreparedStatement stm = DataSource.getInstance().getCnx().prepareStatement(req);
            stm.setInt(1, cmt.getid());
            int i = stm.executeUpdate();
            System.out.println(i + " commentaire supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifiercommentaire(int id, commentaire cmt) {
        String updateStatement = "UPDATE commentaire SET description=? WHERE id= ? ";

        try {
            PreparedStatement pre = DataSource.getInstance().getCnx().prepareStatement(updateStatement);
        
            pre.setString(1, cmt.getDescription());
            
            pre.setInt(2, id);
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

    public List<commentaire> getAll() {
        ObservableList<commentaire> myList = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM commentaire";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                commentaire cmt = new commentaire();
                cmt.setid(rs.getInt(1));
               
                cmt.setDescription(rs.getString("Description"));
              
               

                myList.add(cmt);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;

    }

    public ObservableList<commentaire> readcommentaire() throws SQLException {
        ObservableList<commentaire> L = FXCollections.observableArrayList();

        Statement st = cnx.createStatement();
        String req = "select * from commentaire";
        ResultSet rs = st.executeQuery(req);

        while (rs.next()) {
            int id = rs.getInt(1);
            
            String Description = rs.getString("Description");
            

           commentaire cmt = new commentaire(id, Description);

            L.add(cmt);
        }

        return L;

    }

}
