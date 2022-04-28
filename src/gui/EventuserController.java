/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.evenement;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.evenementservice;
import services.reservationservice;
import utils.DataSource;


/**
 * FXML Controller class
 *
 * @author POSTE HP
 */
public class EventuserController implements Initializable {

    @FXML
    private Button eventadmin;
    @FXML
    private Button gestionreservation;
    @FXML
    private Pane pnlOverview;
    @FXML
    private TextField tfid;
    @FXML
    private TextField rechercheuser;
    @FXML
    private ChoiceBox<String> choiceuser;
    @FXML
    private Button reserver;
    @FXML
    private TextField tfidevent;
    @FXML
    private TableView<evenement> table;
      @FXML
    private TableColumn<evenement, Integer> idevent;
    @FXML
    private TableColumn<evenement, String> nom;
    @FXML
    private TableColumn<evenement, String> photo;
    @FXML
    private TableColumn<evenement, String> description;
    @FXML
    private TableColumn<evenement, String> lieu;
    @FXML
    private TableColumn<evenement, Double> prix;
    
    
 evenementservice cr = new evenementservice();
 
    ObservableList<evenement> data = FXCollections.observableArrayList(cr.getAll());
    ;
    
    
    /**
     * Initializes the controller class.
     */
    ObservableList<evenement> eventList = FXCollections.observableArrayList();
    ObservableList<evenement> eventList2;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showevent();
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (table.getSelectionModel().getSelectedItem() != null) {
                    entities.evenement e= ( entities.evenement) table.getSelectionModel().getSelectedItem();
                    System.out.println();
                    tfidevent.setText(String.valueOf(e.getId()));

                }
            }
        });

     /*   FilteredList<evenement> flevenement;//Pass the data to a filtered list
        flevenement = new FilteredList(data, e -> true);
        table.setItems(flevenement);//Set the table's items using the filtered list
        choiceuser.getItems().addAll("Nom","lieu");
        choiceuser.setValue("Nom");

        rechercheuser.textProperty().addListener((obs, oldValue, newValue) -> {
            switch (choiceuser.getValue())//Switch on choiceBox value
            {
                case "Nom":
                    flevenement.setPredicate(e -> e.getNom().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by first name
                    break;
                case "Lieu":
                    flevenement.setPredicate(e -> e.getLieu().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by last name
                    break;

            }
        });

        choiceuser.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)
                -> {//reset table and textfield when new choice is selected
            if (newVal != null) {
                rechercheuser.setText("");
            }
        });
*/
    }

    public void showevent() {
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

    @FXML
    private void BouttonAdmin(ActionEvent event) throws Exception {
        Stage stage;
        Parent root;

        stage = (Stage) eventadmin.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("eventuser.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

 
    @FXML
    private void Bouttonreservation(ActionEvent event) throws Exception {
        Stage stage;
        Parent root;

        stage = (Stage) gestionreservation.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("res.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
        @FXML

        private void reserver(ActionEvent event) throws Exception {

Stage stage;
        Parent root;

        stage = (Stage) eventadmin.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("reservation.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
