/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.Connexion;
import utils.DataSource;
import java.sql.Date;

/**
 *
 * @author POSTE HP
 */public class reservationservice {

    Connection cnx;

    public reservationservice() {
        Connexion instance = Connexion.getInstance();

    }

    public void Createres(reservation r) {
        try {
            String req = "INSERT INTO reservation(nbr_pers,date,date_r,evenement_id) VALUES "
                    + "('" + r.getNbr_pers() + "'" + ",'" + r.getDate() + "','" + r.getDate_r() + "'" + ",'" + r.getEvenement_id() + "')";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("reservation ajoutée avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void Ajoutres(reservation r) {
        try {
            String req = "INSERT INTO reservation(id,nbr_pers,date,date_r,evenement_id) VALUES "
                    + "('" + r.getId() + "','" + r.getNbr_pers() + "'" + ",'" + r.getDate() + "','" + r.getDate_r() + "'" + ",'" + r.getEvenement_id() + "')";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("reservation ajoutée avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<reservation> Readres() {
        List<reservation> plist = new ArrayList<>();
        try {
            String req = "select * from reservation";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                reservation r = new reservation();
                r.setId(rs.getInt("id"));
                r.setNbr_pers(rs.getInt(2));
                r.setDate(rs.getDate(3));
                r.setDate_r(rs.getDate(4));
                r.setEvenement_id(rs.getInt(5));

                plist.add(r);
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
    public List<reservation> afficherreser() throws SQLException {
        List<reservation> resulta = new ArrayList<>();

        Statement stm = cnx.createStatement();
        String query = "select * from reservation ";

        ResultSet resultat = stm.executeQuery(query);
        reservation r = new reservation();
        while (resultat.next()) {
            r.setId(resultat.getInt("id"));
            r.setNbr_pers(resultat.getInt("nbr_pers"));
            r.setDate(resultat.getDate("date"));
            r.setDate_r(resultat.getDate("date_r"));
            r.setEvenement_id(resultat.getInt("evenement_id"));

            resulta.add(r);
        }

        return resulta;
    }

    public void supprimerrese(reservation r) {
        String req = "delete from reservation where id=?";

        try {
            PreparedStatement stm = DataSource.getInstance().getCnx().prepareStatement(req);
            stm.setInt(1, r.getId());
            int i = stm.executeUpdate();
            System.out.println(i + " reservation suprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(int id, reservation r) {
        String updateStatement = "UPDATE reservation SET Nbr_pers= ? ,date=?, date_r=?, evenement_id=? WHERE id= ? ";

        try {
            PreparedStatement pre = DataSource.getInstance().getCnx().prepareStatement(updateStatement);
            pre.setInt(1, r.getNbr_pers());
            pre.setDate(2, r.getDate());
            pre.setDate(3, r.getDate_r());
            pre.setInt(4, r.getEvenement_id());
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

    public List<reservation> getAll() {
        ObservableList<reservation> myList = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM evenement";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                reservation r = new reservation();
                r.setId(rs.getInt(1));
                r.setNbr_pers(rs.getInt("nbr_pers"));
                r.setDate(rs.getDate("date"));
                r.setDate_r(rs.getDate("date_r"));
                r.setEvenement_id(rs.getInt("evenement_id"));

                myList.add(r);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;

    }

    public ObservableList<reservation> read() throws SQLException {
        ObservableList<reservation> L = FXCollections.observableArrayList();

        Statement st = cnx.createStatement();
        String req = "select * from reservation";
        ResultSet rs = st.executeQuery(req);

        while (rs.next()) {
            int id = rs.getInt(1);
            int nbr_pers = rs.getInt("nbr_pers");
            Date date = rs.getDate("date");
            Date date_r = rs.getDate("date_r");
            int evenement_id = rs.getInt("evenement_id");

            reservation r = new reservation(id, nbr_pers, date, date_r, evenement_id);

            L.add(r);
        }

        return L;

    }

}
