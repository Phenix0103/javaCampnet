/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import java.sql.Date;
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
import entities.commande;
import entities.livreur;
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
public class Servicecommande {

    Connection cnx;

    public Servicecommande() {
        Connexion instance = Connexion.getInstance();

    }

    public void Createcommande(commande com) {
        try {
            String req = "INSERT INTO commande(idlivreur_id,adresse_id,Produit,Quantite,Total,date) VALUES "
                    + "('" + com.getLiv().getId() + "'" + ",'" + com.getAdr().getId() + "','" + com.getProduit() + "'" + ",'" + com.getQuantite() + "','" + com.getTotal() + "','" + com.getdate() + "')";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("commande ajoutée avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void Ajoutcommande(commande com) {
        try {
            String req = "INSERT INTO commande(idcommande,idlivreur_id,adresse_id,Produit,Quantite,Total,date) VALUES "
                    + "('" + com.getidcommande() +  "','"  + com.getLiv().getId() +  "','" + com.getAdr().getId() + "','" + com.getProduit() + "','" + com.getQuantite() + "','" + com.getTotal() + "','" + com.getdate() + "')";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("commande ajoutée avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<commande> Readcommande() {
        List<commande> plist = new ArrayList<>();
        try {
            String req = "select * from commande";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                commande com = new commande();
                com.setidcommande(rs.getInt("idcommande"));
              //  com.setLiv(getId()(rs.getInt("idlivreur_id"));          
               // com.setadresse_id(rs.getInt("adresse_id"));
                com.setProduit(rs.getString(4));
                com.setQuantite(rs.getInt("Quantite"));
                com.setTotal(rs.getDouble(6));
                com.setdate(rs.getDate(7));

                plist.add(com);
            }
        } catch (Exception com) {
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
    public List<commande> affichercommande() throws SQLException {
        List<commande> resulta = new ArrayList<>();

        Statement stm = cnx.createStatement();
        String query = "select * from commande ";

        ResultSet resultat = stm.executeQuery(query);
        commande com = new commande();
        while (resultat.next()) {
           com.setidcommande(resultat.getInt("idcommande"));
               // com.setidlivreur_id(resultat.getInt("idlivreur_id"));
               // com.setadresse_id(resultat.getInt("adresse_id"));
                com.setProduit(resultat.getString(4));
                com.setQuantite(resultat.getInt("Quantite"));
                com.setTotal(resultat.getDouble(6));
                com.setdate(resultat.getDate(7));

            resulta.add(com);
        }

        return resulta;
    }

    public void supprimercommande(commande com) {
        String req = "delete from commande where idcommande=?";

        try {
            PreparedStatement stm = DataSource.getInstance().getCnx().prepareStatement(req);
            stm.setInt(1, com.getidcommande());
            int i = stm.executeUpdate();
            System.out.println(i + " commande supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(int idcommande, commande com) {
        String updateStatement = "UPDATE commande SET idlivreur_id= ? ,adresse_id=?, Produit=?, Quantite=?, Total=? WHERE idcommande= ? ";

        try {
            PreparedStatement pre = DataSource.getInstance().getCnx().prepareStatement(updateStatement);
            pre.setInt(1, com.getLiv().getId());
            pre.setInt(2, com.getAdr().getId());
            pre.setString(3, com.getProduit());
            pre.setInt(4, com.getQuantite());
            pre.setDouble(5, com.getTotal());
            pre.setDate(5, com.getdate());
            pre.setInt(6, idcommande);
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

    public List<commande> getAll() {
        ObservableList<commande> myList = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM commande";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                commande com = new commande();
                
                 com.setidcommande(rs.getInt(1));
               // com.setidlivreur_id(rs.getInt("idlivreur_id"));
               // com.setadresse_id(rs.getInt("adresse_id"));
                com.setProduit(rs.getString(4));
                com.setQuantite(rs.getInt("Quantite"));
                com.setTotal(rs.getDouble("Total"));
                com.setdate(rs.getDate("date"));

                
               
                
                myList.add(com);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;

    }

    
    /*
    public ObservableList<commande> read() throws SQLException {
        ObservableList<commande> L = FXCollections.observableArrayList();
 livreur liv ;
        Statement st = cnx.createStatement();
        String req = "select * from commande";
        ResultSet rs = st.executeQuery(req);

        while (rs.next()) {
            int idcommande = rs.getInt(1);
           // liv.getId() = rs.getInt(2);
            int adresse_id = rs.getInt(3);
            String Produit = rs.getString("Produit");
            int Quantite = rs.getInt(5);
            Double Total = rs.getDouble("Total");
            Date date = rs.getDate("date");
            

            commande com = new commande(idcommande, adresse_id, Produit, Quantite, Total,date);

            L.add(com);
        }

        return L;

    }
*/
}
