/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.produit;
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
import services.Serviceproduit;
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

/**
 * FXML Controller class
 *
 * @author Sarra
 */
public class AdresseController implements Initializable {

    @FXML
    private TextField NomTF;
    @FXML
    private TextField PrenomTF;
    @FXML
    private TextField AdresseTF;
    @FXML
    private TextField CityTF;
    @FXML
    private TextField EmailTF;
    @FXML
    private TextField TelTF;
    @FXML
    private Button btnajoutA;
    ObservableList<adresse> adresseList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<adresse, Integer> col_ida;
    @FXML
    private TableColumn<adresse, String> col_noma;
    @FXML
    private TableColumn<adresse, String> col_prenoma;
    @FXML
    private TableColumn<adresse, String> col_adressea;
    @FXML
    private TableColumn<adresse, String> col_citya;
    @FXML
    private TableColumn<adresse, String> col_emaila;
    @FXML
    private TableColumn<adresse, Integer> col_tela;
    @FXML
    private TableView<adresse> tabAdr;
    @FXML
    private Button actualiseA;
    @FXML
    private AnchorPane filsA;
    @FXML
    private TextField idmodifA;
    @FXML
    private Button modifA;
    @FXML
    private Button suppA;
    @FXML
    private Button btnquitterpn;
    
    
    public void showAdresses() {
              
             try {
       //     Connexion cnx = Connexion.getInstance().getCnx();
                    Connection instance = Connexion.getInstance().getCnx();

            String query = "SELECT * FROM adresse";
            Statement st;
            ResultSet rst;
            st = instance.createStatement();
            rst = st.executeQuery(query);
            adresse Adresses;
        
            while (rst.next()) {

//rst.getInt(1),
 Adresses = new adresse(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6),rst.getInt(7));
                adresseList.add(Adresses);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
       
        col_ida.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_noma.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        col_prenoma.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        col_adressea.setCellValueFactory(new PropertyValueFactory<>("Adress"));
        col_citya.setCellValueFactory(new PropertyValueFactory<>("City"));
        col_emaila.setCellValueFactory(new PropertyValueFactory<>("Email"));
        col_tela.setCellValueFactory(new PropertyValueFactory<>("Tel"));
        

        

        tabAdr.setItems(adresseList);
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showAdresses();
    }    

    @FXML
    private void AjoutAdresse(ActionEvent event) {
         adresse ad;
        Serviceadresse ps = new Serviceadresse();
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
         else if (AdresseTF.getText().length() == 0) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Vous navez pas saisie le prix");
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
    ad = new adresse(NomTF.getText(),PrenomTF.getText(),AdresseTF.getText(),CityTF.getText(),EmailTF.getText(),Integer.parseInt(TelTF.getText()));
    ps.Ajoutadresse(ad);} /* Notifications notificationBuilder = Notifications.create()
        .title("Chambre ajouté")
        .text("Votre Offre d'emploi a été ajouté avec succès")
        .graphic(new ImageView(img))
        .hideAfter(Duration.seconds(5))
        .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();*/
    Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Succes");
    alert.setContentText("ADRESSE ajoutee");
    alert.show();
    NomTF.setText("");
    PrenomTF.setText("");
    AdresseTF.setText("");
    CityTF.setText("");
    EmailTF.setText("");
    TelTF.setText("");
    }

    @FXML
    private void refreshA(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/adresse.fxml"));
                     filsA.getChildren().setAll(pane);
    }

    @FXML
    private void btn_modifierA(ActionEvent event) {
          Serviceadresse sr = new Serviceadresse();
        adresse e = new adresse();
        int id;
        id = Integer.parseInt(idmodifA.getText());
        e.setId(Integer.parseInt(idmodifA.getText()));
            
            e.setNom(NomTF.getText());
            e.setPrenom(PrenomTF.getText());
            e.setAdress(AdresseTF.getText());
            e.setCity(CityTF.getText());
             e.setEmail(EmailTF.getText());
             e.setTel(Integer.parseInt(TelTF.getText()));
            

        sr.modifier(id, e);
        
           idmodifA.clear();
           NomTF.clear();
           PrenomTF.clear();
           AdresseTF.clear();
           CityTF.clear();
           EmailTF.clear();
           TelTF.clear();
    }

    @FXML
    private void btn_SupprA(ActionEvent event) throws SQLException {
         adresseList=tabAdr.getSelectionModel().getSelectedItems();
          Connection instance = Connexion.getInstance().getCnx();
            int id;
            id=adresseList.get(0).getId();
            System.out.println(id);
             
        
            
           String query = "delete from adresse WHERE id = ?";
      PreparedStatement preparedStmt = instance.prepareStatement(query);
      preparedStmt.setInt(1, id);

      // execute the preparedstatement
      preparedStmt.execute();
       
             showAdresses();
    }

    @FXML
    private void Quitterpanier(ActionEvent event) {
        System.exit(0);
    }

    
    
    
}
