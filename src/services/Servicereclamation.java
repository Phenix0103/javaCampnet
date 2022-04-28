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
import entities.reclamation;
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
public class Servicereclamation {

    Connection cnx;

    public Servicereclamation() {
        Connexion instance = Connexion.getInstance();

    }

    public void Createreclamation(reclamation rec) {
        try {
            String req = "INSERT INTO reclamation(description,email) VALUES "
                    + "('" + rec.getdescription() + "'" + rec.getemail() + "')";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("reclamation ajoutée avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void Ajoutreclamation(reclamation rec) {
        try {
            String req = "INSERT INTO reclamation(id,description,email) VALUES "
                    + "('" + rec.getid() + "','" + rec.getdescription() + "','" + rec.getemail() + "')";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("reclamation ajoutée avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<reclamation> Readreclamation() {
        List<reclamation> plist = new ArrayList<>();
        try {
            String req = "select * from reclamation";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                reclamation rec = new reclamation();
                rec.setid(rs.getInt("id"));
                rec.setdescription(rs.getString(2));
               
                rec.setemail(rs.getString(3));
               
                plist.add(rec);
            }
        } catch (Exception rec) {
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
    public List<reclamation> afficherreclamation() throws SQLException {
        List<reclamation> resulta = new ArrayList<>();

        Statement stm = cnx.createStatement();
        String query = "select * from reclamation ";

        ResultSet resultat = stm.executeQuery(query);
        reclamation rec = new reclamation();
        while (resultat.next()) {
            rec.setid(resultat.getInt("id"));
            rec.setdescription(resultat.getString("description"));
            
            rec.setemail(resultat.getString("email"));
            

            resulta.add(rec);
        }

        return resulta;
    }

    public void supprimerreclamation(reclamation rec) {
        String req = "delete from reclamation where id=?";

        try {
            PreparedStatement stm = DataSource.getInstance().getCnx().prepareStatement(req);
            stm.setInt(1, rec.getid());
            int i = stm.executeUpdate();
            System.out.println(i + " reclamation supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(int id, reclamation rec) {
        String updateStatement = "UPDATE reclamation SET description= ? ,email=? WHERE id= ? ";

        try {
            PreparedStatement pre = DataSource.getInstance().getCnx().prepareStatement(updateStatement);
            pre.setString(1, rec.getdescription());
            pre.setString(2, rec.getemail());
            
            pre.setInt(3, id);
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

    public List<reclamation> getAll() {
        ObservableList<reclamation> myList = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM reclamation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                reclamation rec = new reclamation();
                rec.setid(rs.getInt(1));
               rec.setdescription(rs.getString("description"));
                rec.setemail(rs.getString("email"));
              
               

                myList.add(rec);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;

    }

    public ObservableList<reclamation> read() throws SQLException {
        ObservableList<reclamation> L = FXCollections.observableArrayList();

        Statement st = cnx.createStatement();
        String req = "select * from reclamation";
        ResultSet rs = st.executeQuery(req);

        while (rs.next()) {
            int id = rs.getInt(1);
            String description = rs.getString("description");
            String email = rs.getString("email");
            

           reclamation rec = new reclamation(id, description, email);

            L.add(rec);
        }

        return L;

    }

}
