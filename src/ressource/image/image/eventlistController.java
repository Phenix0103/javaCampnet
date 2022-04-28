/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.evenement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import services.evenementservice;
import utils.DataSource;
//import tools.Connexion;

/**
 * FXML Controller class
 *
 * @author POSTE HP
 */
public class eventlistController implements Initializable {

    @FXML
    private Button simage;
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
    @FXML
    private Button supprimerevent;
    @FXML
    private Button ajouterevent;
    @FXML
    private TextField tfid;
    @FXML
    private TextField recherche;
    @FXML
    private ChoiceBox<String> choice;
    @FXML
    private ImageView tfphoto; // 
 final FileChooser fc=new FileChooser();

    @FXML
    private TextArea tfdescription;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tflieu;
    @FXML
    private TextField tfprix;
    @FXML
    private Button modifier;
    
    private String imageurl;
    
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
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                entities.evenement r = ( entities.evenement) table.getSelectionModel().getSelectedItem();
                if (table.getSelectionModel().getSelectedItem() != null) {
                     entities.evenement e = (  entities.evenement) table.getSelectionModel().getSelectedItem();
                     System.out.println();
                     tfid.setText(String.valueOf(e.getId()));
                                  tfnom.setText(e.getLieu());
                   //   tfphoto.setImage((e.getPhoto()));
                  //                       tfphoto.setImage(toString().(e.getPhoto()));

                      tflieu.setText(e.getLieu());
                      tfprix.setText(String.valueOf(e.getPrix()));
                    // tfprix.setText(e.getPrix());

                }
            }
            
        });
        
        
        
        FilteredList<evenement> flevenement;//Pass the data to a filtered list
        flevenement = new FilteredList(data, e -> true);
        table.setItems(flevenement);//Set the table's items using the filtered list
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
    private void supprimerevent(ActionEvent event) {
         eventList2=table.getSelectionModel().getSelectedItems();
         Connection cnx = DataSource.getInstance().getCnx();
            int id;
            id=eventList2.get(0).getId();
            System.out.println(id);
             
        try {
            
           String query = "delete from evenement where id = ?";
      PreparedStatement preparedStmt = cnx.prepareStatement(query);
      preparedStmt.setInt(1, id);

      // execute the preparedstatement
      preparedStmt.execute();
                       tfid.clear();
                       tfnom.clear();
                //    tfphoto.clear();
                       tfdescription.clear();
                       tflieu.clear();
                       tfprix.clear();
                 
      
     
        } catch (SQLException ex) {
            Logger.getLogger(EventaddController.class.getName()).log(Level.SEVERE, null, ex);
        }
         showevent2();
    }
    

    @FXML
    private void AjoutEvenement(ActionEvent event) throws IOException, SQLException {
        
        evenementservice aa = new evenementservice();
        Scanner sc = new Scanner(System.in);
        Connection cnx = DataSource.getInstance().getCnx();
        Statement st;
        ResultSet rs;
        st = cnx.createStatement();
        Statement stm = cnx.createStatement();

         evenement t = new evenement();
            t.setNom(tfnom.getText());
           t.setPhoto(imageurl);
            t.setDescription(tfdescription.getText());
                        t.setLieu(tflieu.getText());
t.setPrix(Double.parseDouble(tfprix.getText()));       
            Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(tfnom.getText());
            boolean nom = m.find();
          if(
                  tfnom.getText().isEmpty()|| tfdescription.getText().isEmpty() || tflieu.getText().isEmpty() || tfprix.getText().isEmpty()
                    ) 
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez remplir le formulaire");
                    alert.showAndWait();
            }
            
           else  if (nom){
                   Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("Le nom ne doit pas contenir des symboles");
                    alert.showAndWait();
               }
               else if(
                      Double.parseDouble(tfprix.getText()) == 0
                       ) { 
               Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("Le prix de l'evenement ne peut pas etre nulle");
                    alert.showAndWait();
               }
               else if(
                       Double.parseDouble(tfprix.getText()) < 0
                       ) { 
               Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("Le prix de l'evenement ne peut pas inferieure à 0");
                    alert.showAndWait();
               }
              
               
               
               else {
                    aa.AjoutEvenement(t); 
                       showevent2(); 
                       tfnom.clear();
                       tfdescription.clear();
                       tflieu.clear();
                       tfprix.clear();
                   
               }
 }                  
    private void modifier(ActionEvent event) throws SQLException {
        evenementservice sr = new evenementservice();
        evenement t = new evenement();
        int id;
        id = Integer.parseInt(tfid.getText());
              t.setNom(tfnom.getText());
           t.setPhoto(imageurl);
            t.setDescription(tfdescription.getText());
                        t.setLieu(tflieu.getText());
t.setPrix(Double.parseDouble(tfprix.getText()));     
            Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(tfnom.getText());
            boolean nom = m.find();
            if(
                  tfnom.getText().isEmpty()|| tfdescription.getText().isEmpty() || tflieu.getText().isEmpty() || tfprix.getText().isEmpty()
                    ) 
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez remplir le formulaire");
                    alert.showAndWait();
            }
            else if (nom){
                   Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("Le nom ne doit pas contenir des symboles");
                    alert.showAndWait();
               }
               else if(
                       Integer.parseInt(tfprix.getText()) == 0
                       ) { 
               Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("Le prix de l'evenment ne peut pas etre nulle");
                    alert.showAndWait();
               }
               else if(
                       Integer.parseInt(tfprix.getText()) < 0
                       ) { 
               Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("Le prix de l'evenment ne peut pas inferieure à 0");
                    alert.showAndWait();
               }
             
               else { sr.modifier(id, t); }
        showevent2();
        

    }
    
    
   
@FXML
    private void simage(ActionEvent event) {
                final FileChooser fc=new FileChooser();

        fc.setTitle("my file choser");
        fc.setInitialDirectory(new File(System.getProperty("user.home")) );
        fc.getExtensionFilters().clear() ;
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("image Files","*.png"));
        File file =fc.showOpenDialog(null);
        if (file!=null ){
           tfphoto.setImage(new Image(file.toURI().toString()));
           imageurl = file.toURI().toString();
        }  else {
           System.out.println("A file is invalid ");
        }
            
    }

}
