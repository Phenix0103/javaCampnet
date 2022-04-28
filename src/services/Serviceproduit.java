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
import entities.produit;
import utils.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import iservice.iserviceproduit;

/**
 *
 * @author sarra
 */
public class Serviceproduit {

    Connection cnx;
  
 

    public Serviceproduit() {
        Connexion instance = Connexion.getInstance();

    }

    public void CreateProduct( produit p) {
        try {
            String req = "INSERT INTO produit(nom,photo,description,disponibilte,prix) VALUES "
                    + "('" + p.getNom() + "'" + ",'" + p.getPhoto() + "','" + p.getDescription() + "'" + ",'" + p.getDisponibilte() + "','" + p.getPrix() + "')";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("produit ajouté avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void Ajoutproduit(produit p) {
        try {
            String req = "INSERT INTO produit(id,nom,photo,description,disponibilte,prix) VALUES "
                    + "('" + p.getId() + "','" + p.getNom() + "','" + p.getPhoto() + "','" + p.getDescription() + "','" + p.getDisponibilte() + "','" + p.getPrix() + "')";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("produit ajouté avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<produit> Readproduit() {
        List<produit> plist = new ArrayList<>();
        try {
            String req = "select * from produit";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                produit p = new produit();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString(2));
                p.setPhoto(rs.getString(3));
                p.setDescription(rs.getString(4));
                p.setDisponibilte(rs.getString(5));
                p.setPrix(rs.getDouble(6));

                plist.add(p);
            }
        } catch (Exception p) {
        }
        return plist;
    }


    
  /* public void Addproduct(produit p) throws SQLException  {
  try {
        String req ="INSERT INTO produit (nom,photo,description,disponibilte,prix) values (?,?,?,?,?)";
        
        
            
            PreparedStatement stm = cnx.prepareStatement(req);
             stm.setString(1, p.getNom());
             stm.setString(2, p.getPhoto());
             stm.setString(3,p.getDescription());
             stm.setString(4,p.getDisponibilte());
             stm.setDouble(5, p.getPrix());
             
             stm.executeUpdate();
             System.out.println("Produit ajouté");
                     
        } catch (SQLException ex) {
            System.out.println("probleme");
            System.out.println(ex.getMessage());
        }       
    } */
   
  
   public void addproduct(produit p) {
         try {
            String requete= "insert into produit (nom,photo,description,disponibilte,prix)"
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement pst = DataSource.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, p.getNom());
            pst.setString(2, p.getPhoto());
            pst.setString(3, p.getDescription());
            pst.setString(4, p.getDisponibilte());
            pst.setDouble(5, p.getPrix());



            pst.executeUpdate();
            System.out.println("Produit inserée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
   
   
   
   
   
   
     
    public List<produit> afficherproduit() throws SQLException {
        List<produit> resulta = new ArrayList<>();

        Statement stm = cnx.createStatement();
        String query = "select * from produit ";

        ResultSet resultat = stm.executeQuery(query);
        produit p = new produit();
        while (resultat.next()) {
            p.setId(resultat.getInt("id"));
            p.setNom(resultat.getString("nom"));
            p.setPhoto(resultat.getString("photo"));
            p.setDescription(resultat.getString("description"));
            p.setDisponibilte(resultat.getString("disponibilte"));
            p.setPrix(resultat.getDouble("prix"));

            resulta.add(p);
        }

        return resulta;
    }

    public void supprimerproduit(produit p) {
        String req = "delete from produit where id=?";

        try {
            PreparedStatement stm = DataSource.getInstance().getCnx().prepareStatement(req);
            stm.setInt(1, p.getId());
            int i = stm.executeUpdate();
            System.out.println(i + " produit supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(int id, produit p) {
        String updateStatement = "UPDATE produit SET Nom= ? ,photo=?, description=?, disponibilte=?, prix=? WHERE id= ? ";

        try {
            PreparedStatement pre = DataSource.getInstance().getCnx().prepareStatement(updateStatement);
            pre.setString(1, p.getNom());
            pre.setString(2, p.getPhoto());
            pre.setString(3, p.getDescription());
            pre.setString(4, p.getDisponibilte());
            pre.setDouble(5, p.getPrix());
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

    public List<produit> getAll() {
        ObservableList<produit> myList = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM produit";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                produit p = new produit();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom"));
                p.setPhoto(rs.getString("photo"));
                p.setDescription(rs.getString("description"));
                p.setDisponibilte(rs.getString("disponibilte"));
                p.setPrix(rs.getDouble("prix"));

                myList.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;

    }

    public ObservableList<produit> read() throws SQLException {
        ObservableList<produit> L = FXCollections.observableArrayList();

        Statement st = cnx.createStatement();
        String req = "select * from produit";
        ResultSet rs = st.executeQuery(req);

        while (rs.next()) {
            int id = rs.getInt(1);
            String nom = rs.getString("nom");
            String photo = rs.getString("photo");
            String description = rs.getString("description");
            String disponibilte = rs.getString("disponibilte");
            Double prix = rs.getDouble("prix");

            produit p = new produit(id, nom, photo, description, disponibilte, prix);

            L.add(p);
        }

        return L;

    }

    produit searchByReference(int id) throws SQLException {
        
          //To change body of generated methods, choose Tools | Templates.
        Statement stm = cnx.createStatement();
        produit P =new produit();
        String query = "SELECT * FROM produit WHERE `id`='"+id+"'";
         
        ResultSet rs= stm.executeQuery(query);
          
        while (rs.next()){
            
            P.setId(rs.getInt("id"));
            P.setNom(rs.getString("nom"));
            P.setPhoto(rs.getString("photo"));
            P.setDescription(rs.getString("description"));
            P.setDisponibilte(rs.getString("disponibilte"));
            
            P.setPrix(rs.getDouble("prix"));
            
            
        }
        return P;
    }
    
    public produit SearchById(int id) throws SQLException{
        Statement stm = cnx.createStatement();
        produit P =new produit();
        String query = "SELECT * FROM produit WHERE id = "+id;
        ResultSet rs= stm.executeQuery(query);
          
        while (rs.next()){
            
            P.setId(rs.getInt("id"));
            P.setNom(rs.getString("nom"));
            P.setPhoto(rs.getString("photo"));
            P.setDescription(rs.getString("description"));
            P.setDisponibilte(rs.getString("disponibilte"));
            
            P.setPrix(rs.getDouble("prix"));
            
            
        }
        return P;
    }
      

}
