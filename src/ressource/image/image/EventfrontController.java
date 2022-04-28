/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ressource.image.image;

import entities.evenement;
import javafx.geometry.Insets;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import services.evenementservice;

/**
 * FXML Controller class
 *
 * @author user
 */
public class EventfrontController implements Initializable {
    private List<evenement> event =new ArrayList<>();

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Button bouttontest;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            showevent();
        } catch (SQLException ex) {
            Logger.getLogger(EventfrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
    
    public void showevent() throws SQLException{
       
          grid.getChildren().clear();
        evenement.clear();
        EventService sp = new EventService();

        produits = sp.displayAll();
;
        int column = 0;
        int row = 1;
        //local produits
        int i = 0;
        try {
            for (i = 0; i < produits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/samipi/singleevent.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                SingleeventController SingleeventController = fxmlLoader.getController();
                SingleeventController.setData(produits.get(i));
                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
       
    @FXML
    private void Bouttontest (ActionEvent event) throws Exception {
        Stage stage;
        Parent root;
       
       
            stage = (Stage) bouttontest.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Rfront.fxml"));
        
         Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    
}
