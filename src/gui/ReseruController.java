/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.evenement;
import entities.reservation;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.evenementservice;
import services.reservationservice;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author POSTE HP
 */
public class ReseruController implements Initializable {

    @FXML
    private TableView<reservation> eventTable;
    @FXML
    private TableColumn<?, ?> NomColonne;
    @FXML
    private TableColumn<reservation, Integer> tfnbr;
    @FXML
    private TableColumn<reservation, Date> tfdate;
    @FXML
    private TableColumn<reservation, Date> tfdater;
    @FXML
    private TableColumn<?, ?> DateColonnne;
    
       
 evenementservice cr = new evenementservice();
    ObservableList<evenement> data = FXCollections.observableArrayList(cr.getAll());;
    /**
     * Initializes the controller class.
     */
    
     ObservableList<evenement> eventList = FXCollections.observableArrayList();
    ObservableList<evenement> eventList2;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         showevent();
        eventTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                entities.reservation r = ( entities.reservation ) eventTable.getSelectionModel().getSelectedItem();
                if (eventTable.getSelectionModel().getSelectedItem() != null) {
                     entities.reservation e = (  entities.reservation) eventTable.getSelectionModel().getSelectedItem();
                     System.out.println();
                     tfnbr.setText(String.valueOf(e.getId()));
                               //   tfdate.setDate(e.getDate());
                   //   tfphoto.setImage((e.getPhoto()));
                  //                       tfphoto.setImage(toString().(e.getPhoto()));

                  //    tflieu.setText(e.getLieu());
                   //   tfprix.setText(String.valueOf(e.getPrix()));
                    // tfprix.setText(e.getPrix());

                }
            }
            
        });
        
        
        /*
        FilteredList<evenement> flevenement;//Pass the data to a filtered list
        flevenement = new FilteredList(data, e -> true);
        eventTable.setItems(flevenement);//Set the table's items using the filtered list
        choice.getItems().addAll();
       // choice.setValue();
        
        recherche.textProperty().addListener((obs, oldValue, newValue) -> {
            switch (choice.getValue())//Switch on choiceBox value
            {
                case "Nom":
                    flevenement.setPredicate(e -> e.getNom().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by first name
                    break;
                case "Lieu":
                    flevenement.setPredicate(e -> e.getLieu().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by last name
                    break;
                
            }
        });

        choice.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)
                -> {//reset table and textfield when new choice is selected
            if (newVal != null) {
                recherche.setText("");
            }
        });
  */      
    }  

     public void showevent() {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            String query = "SELECT * FROM reservation";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            reservation res;
            while (rs.next()) {
                res = new reservation (rs.getInt("nbr_pers"), rs.getInt("nbr_pers"), rs.getDate("description"), rs.getDate("lieu"), rs.getInt("event_id"));
              eventTable.add(res);
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
      

}