/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import org.controlsfx.control.Notifications;

import entities.evenement;
import entities.reservation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.mail.Transport;
import services.reservationservice;
import utils.DataSource;
import utils.JavaMail;
import utils.Mailling;


/**
 * FXML Controller class
 *
 * @author POSTE HP
 */
public class ReservationController implements Initializable {

    @FXML
    private TextField tfid;
    @FXML
    private TextField tfnbr;
    @FXML
    private DatePicker tfdate;
    @FXML
    private DatePicker tfdater;
    @FXML
    private ChoiceBox<String> tfevenementid;
    @FXML
    private Button reserverevent;
    @FXML
    private Button reserus;
 reservationservice cr = new reservationservice();
;    
    /**
     * Initializes the controller class.
     */
    
       ObservableList<reservation> resList = FXCollections.observableArrayList();
    ObservableList<reservation> resList2;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         
        
        
    }    
    /*
    
     public void showevent() {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            String query = "SELECT * FROM reservation";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            reservation reservation;
            while (rs.next()) {
                reservation = new reservation(rs.getInt("id"), rs.getInt("nbr_pers"),rs.getDate("date aller"), rs.getDate("date retour"), rs.getInt("evenement_id"));
              resList.add(reservation);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        tfnbr.setCellValueFactory(new PropertyValueFactory<>("id"));
        tfdate.setCellValueFactory(new PropertyValueFactory<>("nom"));
        photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));

        table.setItems(eventList);
    }
    public void showevent2() {
          eventList.removeAll(eventList);
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            String query = "SELECT * FROM evenement";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            evenement event;
            while (rs.next()) {
                 event = new evenement(rs.getInt("id"), rs.getString("nom"),rs.getString("photo"), rs.getString("description"), rs.getString("lieu"), rs.getDouble("prix"));
              eventList.add(event);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
       idevent.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));


        table.setItems(eventList);;
    }
      
*/
    /*
    
 @FXML
    private void reserverevent(ActionEvent event) throws IOException, SQLException {
        
        reservationservice aa = new reservationservice();
        Scanner sc = new Scanner(System.in);
        Connection cnx = DataSource.getInstance().getCnx();
        Statement st;
        ResultSet rs;
        st = cnx.createStatement();
        Statement stm = cnx.createStatement();
        
        
         reservation t = new reservation();
        
            t.setNbr_pers(Integer.parseInt(tfnbr.getText()));
            t.setDate(Date.valueOf(tfdate.getValue()));
                        t.setDate_r(Date.valueOf(tfdater.getValue()));

          //  t.setEvenement_id(Integer.parseInt(tfevenementid.getText()));
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
           DateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
           
            Calendar cal = Calendar.getInstance();
                        Calendar cal1 = Calendar.getInstance();
                        
           //  Date now = new Date();
            LocalDate ld = LocalDate.now(ZoneId.of("Europe/Paris"));
            Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(tfnbr.getText());
            boolean nom = m.find();
            if(
                    tfnbr.getText().isEmpty()||  tfdate.getValue() == null || tfdater.getValue() == null //||  tfevenementid.getText().isEmpty()
                    ) 
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez remplir le formulaire");
                    alert.showAndWait();
            }
            
           
               else if(
                       Integer.parseInt(tfnbr.getText()) == 0
                       ) { 
               Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("nombre de l'evenment ne peut pas etre nulle");
                    alert.showAndWait();
               }
               else if(
                       Integer.parseInt(tfnbr.getText()) < 0
                       ) { 
               Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("nombre de l'evenment ne peut pas inferieure à 0");
                    alert.showAndWait();
               }
               else if (
                        tfdate.getValue().isBefore(ld)
                       )
               {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez inserer une date valide");
                    alert.showAndWait();
               }
               
                else if (
                        tfdater.getValue().isBefore(ld)
                       )
               {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez inserer une date valide");
                    alert.showAndWait();
               }
               
               else {
                    aa.Createres(t); 
                     //  showevent2(); 
                       tfnbr.clear();
                       tfdate.getEditor().clear();
                       tfdater.getEditor().clear();
                   
               }
 }    */          
         @FXML
    private void reserverevent(ActionEvent event) throws SQLException, Exception {
        reservationservice aa = new reservationservice();

        Scanner sc = new Scanner(System.in);
        Connection cnx = DataSource.getInstance().getCnx();
        Statement st;
        ResultSet rs;
        ResultSet mail;
        st = cnx.createStatement();
        Statement stm = cnx.createStatement();
                 reservation t = new reservation();

            t.setNbr_pers(Integer.parseInt(tfnbr.getText()));
            t.setDate(Date.valueOf(tfdate.getValue()));
                        t.setDate_r(Date.valueOf(tfdater.getValue()));

          //  t.setEvenement_id(Integer.parseInt(tfevenementid.getText()));
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
           DateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
           
            Calendar cal = Calendar.getInstance();
                        Calendar cal1 = Calendar.getInstance();
                        
           //  Date now = new Date();
            LocalDate ld = LocalDate.now(ZoneId.of("Europe/Paris"));
            Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(tfnbr.getText());
            boolean nom = m.find();
            if(
                    tfnbr.getText().isEmpty()||  tfdate.getValue() == null || tfdater.getValue() == null //||  tfevenementid.getText().isEmpty()
                    ) 
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez remplir le formulaire");
                    alert.showAndWait();
            }
            
           
               else if(
                       Integer.parseInt(tfnbr.getText()) == 0
                       ) { 
               Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("nombre de l'evenment ne peut pas etre nulle");
                    alert.showAndWait();
               }
               else if(
                       Integer.parseInt(tfnbr.getText()) < 0
                       ) { 
               Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("nombre de l'evenment ne peut pas inferieure à 0");
                    alert.showAndWait();
               }
               else if (
                        tfdate.getValue().isBefore(ld)
                       )
               {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez inserer une date valide");
                    alert.showAndWait();
               }
               
                else if (
                        tfdater.getValue().isBefore(ld)
                       )
               {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez inserer une date valide");
                    alert.showAndWait();
               }
               
               else {
                    aa.Createres(t); 
                     //  showevent2(); 
                       tfnbr.clear();
                       tfdate.getEditor().clear();
                       tfdater.getEditor().clear();
                   
               }
        /*String SQL = "SELECT * FROM reservation WHERE evenement_id = " + tfevenementid.getValue() + ""; ///
        rs = stm.executeQuery(SQL);
       
                if (!rs.next()) {
                    String req = "INSERT INTO reservation (nbr_pers,date,date_r,evenement_id) values (?,?,?,?)";
                    try {

                        PreparedStatement stm1 = cnx.prepareStatement(req);
                        stm1.setInt(1, Integer.parseInt(tfevenementid.getValue()));
                        stm1.setInt(2, 1); // passage statique de id = 1 du user 
        
                String SQL = "SELECT * FROM reservation WHERE evenement_id = " + tfevenementid.getValue() + ""; ///

         rs = stm.executeQuery(SQL);

        /*if (!rs.next()) {
                    String req = "INSERT INTO reservation (nbr_pers,date,date_r,evenement_id) values (?,?,?,?)";
                    try {

                        PreparedStatement stm1 = cnx.prepareStatement(req);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Reservation effectuée avec succes\n Vous recevrez un email dans les plus bref delais.");
                        alert.showAndWait();
                        stm1.executeUpdate();
                        String mailquery = "SELECT email FROM user WHERE id = 1"; // remplacer 1 avec le user connecter
                        PreparedStatement stm1l = cnx.prepareStatement(mailquery);
                        mail = stm1l.executeQuery(mailquery);
                        if (mail.next()) {
                            String Email = mail.getString("email");
                          //   System.err.println(Email); // debug

                           JavaMail.sendMail(Email);
                        }

                        System.out.println("Reservation ajouté");
                    } catch (SQLException ex) {
                        System.out.println("probleme");
                        System.out.println(ex.getMessage());
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Vous avez deja reserver à cet evenement");
                    alert.showAndWait();
                }
*/
             System.out.println(t);
            JavaMail M = new JavaMail() ;
                           JavaMail.sendMail("cyrine.chouchane@esprit.tn");
                           
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Reservation effectuée avec succes\n Vous recevrez un email dans les plus bref delais.");
                        alert.showAndWait(); 
                        
                        
                        


                        
           /* Alert alertt = new Alert(Alert.AlertType.INFORMATION);
            alertt.setTitle("Success");
            alertt.setContentText(" un Email a été enoyer avec succes ");
            alertt.show();*/
           
        }
    
    @FXML
    private void reserus (ActionEvent event) throws Exception {
        Stage stage;
        Parent root;
       
       
            stage = (Stage) reserus.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("reseru.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
    

