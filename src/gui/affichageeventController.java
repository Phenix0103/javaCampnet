/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import ressource.*;
import entities.evenement;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import services.evenementservice;

/**
 *
 * @author POSTE HP
 */
public class affichageeventController {
   @FXML
    private ListView<evenement> liste;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
         evenementservice as = new evenementservice();
        List<evenement> la = as.Readevent();
        ObservableList<evenement> data=FXCollections.observableArrayList(la);
    
    liste.setItems(data);
    
    }    

    @FXML
    private void AjouterCentreCamp(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("AddCentreCamp.fxml"));
            Parent root=loader.load();
            EventadController aac=loader.getController();
            liste.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(affichageeventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void modifier(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("ModifierCentreCamp.fxml"));
            Parent root=loader.load();
            EventadController aac=loader.getController();
            liste.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(affichageeventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimeCentreCamp(ActionEvent event) {
    }
    
}

