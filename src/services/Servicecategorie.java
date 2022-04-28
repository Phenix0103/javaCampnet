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
import entities.categorie;
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
public class Servicecategorie {

    Connection cnx;

    public Servicecategorie() {
        Connexion instance = Connexion.getInstance();

    }

    public void Createcategorie(categorie cat) {
        try {
            String req = "INSERT INTO categorie(theme,description) VALUES "
                    + "('" + cat.gettheme() + "'" + cat.getdescription() + "')";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("categorie ajoutée avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void Ajoutcategorie(categorie cat) {
        try {
            String req = "INSERT INTO categorie(id,theme,description) VALUES "
                    + "('" + cat.getid() + "','" + cat.gettheme() + "','" + cat.getdescription() + "')";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("categorie ajoutée avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<categorie> Readcategorie() {
        List<categorie> plist = new ArrayList<>();
        try {
            String req = "select * from categorie";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                categorie cat = new categorie();
                cat.setid(rs.getInt("id"));
                cat.settheme(rs.getString(2));
               
                cat.setdescription(rs.getString(3));
               
                plist.add(cat);
            }
        } catch (Exception cat) {
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
    public List<categorie> affichercategorie() throws SQLException {
        List<categorie> resulta = new ArrayList<>();

        Statement stm = cnx.createStatement();
        String query = "select * from categorie ";

        ResultSet resultat = stm.executeQuery(query);
        categorie cat = new categorie();
        while (resultat.next()) {
            cat.setid(resultat.getInt("id"));
            cat.settheme(resultat.getString("theme"));
            
            cat.setdescription(resultat.getString("description"));
            

            resulta.add(cat);
        }

        return resulta;
    }

    public void supprimercategorie(categorie cat) {
        String req = "delete from categorie where id=?";

        try {
            PreparedStatement stm = DataSource.getInstance().getCnx().prepareStatement(req);
            stm.setInt(1, cat.getid());
            int i = stm.executeUpdate();
            System.out.println(i + " categorie supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(int id, categorie cat) {
        String updateStatement = "UPDATE categorie SET theme= ? ,description=? WHERE id= ? ";

        try {
            PreparedStatement pre = DataSource.getInstance().getCnx().prepareStatement(updateStatement);
            pre.setString(1, cat.gettheme());
            pre.setString(2, cat.getdescription());
            
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

    public List<categorie> getAll() {
        ObservableList<categorie> myList = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM reclamation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                categorie cat = new categorie();
                cat.setid(rs.getInt(1));
               cat.settheme(rs.getString("theme"));
                cat.setdescription(rs.getString("description"));
              
               

                myList.add(cat);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;

    }

    public ObservableList<categorie> read() throws SQLException {
        ObservableList<categorie> L = FXCollections.observableArrayList();

        Statement st = cnx.createStatement();
        String req = "select * from categorie";
        ResultSet rs = st.executeQuery(req);

        while (rs.next()) {
            int id = rs.getInt(1);
            String theme = rs.getString("theme");
            String description = rs.getString("description");
            

           categorie cat = new categorie(id, theme, description);

            L.add(cat);
        }

        return L;

    }

}
