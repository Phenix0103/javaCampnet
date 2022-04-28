/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.livreur;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import tools.Connexion;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter; 
import entities.adresse;

import java.io.FileOutputStream;
import java.sql.DriverManager;
import services.Serviceadresse;
import services.Servicelivreur;

/**
 * FXML Controller class
 *
 * @author Sarra
 */
public class LivreurController implements Initializable {

    @FXML
    private TextField NomTF;
    @FXML
    private TextField PrenomTF;
    @FXML
    private TextField TelTF;
    @FXML
    private TextField EmailTF;
    @FXML
    private Button btnajoutL;
     ObservableList<livreur> livreurList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<livreur, Integer> col_idl;
    @FXML
    private TableColumn<livreur, String> col_noml;
    @FXML
    private TableColumn<livreur, String> col_prenoml;
    @FXML
    private TableColumn<livreur, Integer> col_telL;
    @FXML
    private TableColumn<livreur, String> col_emailL;
    @FXML
    private AnchorPane filsL;
    @FXML
    private TableView<livreur> tabLiv;
    @FXML
    private Button modifL;
    @FXML
    private TextField idmodifL;
    @FXML
    private Button actualiseL;
    @FXML
    private Button suppL;
    @FXML
    private Button btnquitterpn;
    
    
    public void showLivreurs() {
              
             try {
       //     Connexion cnx = Connexion.getInstance().getCnx();
                    Connection instance = Connexion.getInstance().getCnx();

            String query = "SELECT * FROM livreur";
            Statement st;
            ResultSet rst;
            st = instance.createStatement();
            rst = st.executeQuery(query);
            livreur Livreurs;
        
            while (rst.next()) {

//rst.getInt(1),
 Livreurs = new livreur(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getInt(4),rst.getString(5));
                livreurList.add(Livreurs);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
       
        col_idl.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_noml.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        col_prenoml.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        col_telL.setCellValueFactory(new PropertyValueFactory<>("Tel"));
        col_emailL.setCellValueFactory(new PropertyValueFactory<>("Email"));
        
        

        

        tabLiv.setItems(livreurList);
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         showLivreurs();
    }    

    @FXML
    private void AjoutLivreur(ActionEvent event) {
         livreur liv;
        Servicelivreur ps = new Servicelivreur();
         if (NomTF.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Vous navez pas saisie le nom");
            alert.show();

        }
         else if (PrenomTF.getText().length() == 0) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Vous navez pas saisie le prenom");
            alert.show();
         }
         
          else if (TelTF.getText().length() > 8) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("enter 8 caractere pour le Numero");
            alert.show();
         }
             else {
       // Chambre p = new Chambre(tf_idch.getText(),tf_nblits.getText(),typech.getValue(),Double.parseDouble(tf_prix.getText()));
    liv = new livreur(NomTF.getText(),PrenomTF.getText(),Integer.parseInt(TelTF.getText()),EmailTF.getText());
    ps.Ajoutlivreur(liv);} /* Notifications notificationBuilder = Notifications.create()
        .title("Chambre ajouté")
        .text("Votre Offre d'emploi a été ajouté avec succès")
        .graphic(new ImageView(img))
        .hideAfter(Duration.seconds(5))
        .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();*/
    Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Succes");
    alert.setContentText("LIVREUR ajoutee");
    alert.show();
    NomTF.setText("");
    PrenomTF.setText("");
    TelTF.setText("");
    EmailTF.setText("");
    
    }

    @FXML
    private void btn_modifierL(ActionEvent event) {
        Servicelivreur sr = new Servicelivreur();
        livreur e = new livreur();
        int id;
        id = Integer.parseInt(idmodifL.getText());
        e.setId(Integer.parseInt(idmodifL.getText()));
            
            e.setNom(NomTF.getText());
            e.setPrenom(PrenomTF.getText());
            e.setTel(Integer.parseInt(TelTF.getText()));
            e.setEmail(EmailTF.getText());
            
            
            

        sr.modifier(id, e);
        
           idmodifL.clear();
           NomTF.clear();
           PrenomTF.clear();
            TelTF.clear();
           EmailTF.clear();
          
    }

    @FXML
    private void refreshL(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/livreur.fxml"));
                     filsL.getChildren().setAll(pane);
    }

    @FXML
    private void btn_SupprL(ActionEvent event) throws SQLException {
         livreurList=tabLiv.getSelectionModel().getSelectedItems();
          Connection instance = Connexion.getInstance().getCnx();
            int id;
            id=livreurList.get(0).getId();
            System.out.println(id);
             
        
            
           String query = "delete from livreur WHERE id = ?";
      PreparedStatement preparedStmt = instance.prepareStatement(query);
      preparedStmt.setInt(1, id);

      // execute the preparedstatement
      preparedStmt.execute();
       
             showLivreurs();
    }

    @FXML
    private void Quitterpanier(ActionEvent event) {
        System.exit(0);
    }
    }
    

