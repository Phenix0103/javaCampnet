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
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;
import tools.Connexion;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter; 
import entities.commande;
import entities.livreur;
import entities.panier;
import services.Serviceproduit;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.DriverManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import services.Serviceadresse;
import services.Servicelivreur;
import services.Servicepanier;

/**
 * FXML Controller class
 *
 * @author Sarra
 */
public class PanierController implements Initializable {
ObservableList<panier> panierList = FXCollections.observableArrayList();

    @FXML
    private TableColumn<panier, Integer> col_idPn;
    @FXML
    private TableColumn<panier, String> col_nompn;
    @FXML
    private TableColumn<panier, Integer> col_qtpn;
   
    @FXML
    private TableView<panier> tabPn;
    @FXML
    private Button suppPn;
    @FXML
    private Button actualisePn;
    @FXML
    private AnchorPane filsPn;
    @FXML
    private Button btnasuppqtpn;
    @FXML
    private Button btnquitterpn;
    @FXML
    private TextField tf_qte;
    
    private Label lbl_total;
    private TextField tf_price;
    @FXML
    private Button btntotal;
    @FXML
    private TableView<commande> tabtotal;
    @FXML
    private TableColumn<?, ?> coltotal;
           private ObservableList<commande>commandes;
           
           panier o;
    public void showPaniers() {
              
             try {
       //     Connexion cnx = Connexion.getInstance().getCnx();
                    Connection instance = Connexion.getInstance().getCnx();

            String query = "SELECT * FROM panier";
            Statement st;
            ResultSet rst;
            st = instance.createStatement();
            rst = st.executeQuery(query);
            panier Paniers;
        
            while (rst.next()) {

//rst.getInt(1),
 Paniers = new panier(rst.getInt(1),rst.getString(2),rst.getInt(3),rst.getDouble(4));
                panierList.add(Paniers);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
       
        col_idPn.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nompn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_qtpn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        
        
        
        

        

        tabPn.setItems(panierList);
    }
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showPaniers();
        
    } 
    
      private void setCelluleTableCommande(){
   
        coltotal.setCellValueFactory(new PropertyValueFactory<>("total"));
    }
    private void LoadDataFromCommande()throws SQLException{
            PreparedStatement pst;
            Connection instance = Connexion.getInstance().getCnx();
            ResultSet rs;
      
            pst = instance.prepareStatement("SELECT * from `commande` ");
                     rs = pst.executeQuery();
      while (rs.next()){
                         commandes.add(new commande( rs.getFloat("total")));
                         
                     }
        tabtotal.setItems(commandes);
    }

    @FXML
    private void btn_SupprPn(ActionEvent event) throws SQLException {
        panierList=tabPn.getSelectionModel().getSelectedItems();
          Connection instance = Connexion.getInstance().getCnx();
            int id;
            id=panierList.get(0).getId();
            System.out.println(id);
             
        
            
           String query = "delete from panier WHERE id = ?";
      PreparedStatement preparedStmt = instance.prepareStatement(query);
      preparedStmt.setInt(1, id);

      // execute the preparedstatement
      preparedStmt.execute();
       
             showPaniers();
    }


    
    @FXML
    private void refreshPn(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/panier.fxml"));
                     filsPn.getChildren().setAll(pane);
    }

    
    

    @FXML
    private void SuppqtPanier(ActionEvent event) {
    }

    @FXML
    private void Quitterpanier(ActionEvent event) {
        System.exit(0);
    }


   /* @FXML
    public boolean Update_Quantity_Product_Order_Line(ActionEvent event) throws SQLException{
         Connection instance = Connexion.getInstance().getCnx();
       
       double total=o.getProd().getPrix()*o.getQuantite();
        Statement stmt;
       String sql="UPDATE  `campnet`.`commande`, `panier` SET `quantite`="+o.getQuantite()+",`prix`="+total+" WHERE  panier.id ="+o.getId()+"=order.idcommande="+o.getCommande().getidcommande()+" AND idcommande="+o.getCommande().getidcommande()+";";
   
       stmt = instance.createStatement();
        
       // stmt.executeUpdate(sql);
        System.out.println("UPDATED");
        if(stmt.executeUpdate(sql)==-1)
           
            return false;       
        return true;       
    }
//
    
    private void handleCalculTotaleCommande(ActionEvent event) throws SQLException {
            Statement stmt=null;
 double totale=Double.valueOf(tf_price.getText());
                  Integer quantite=Integer.valueOf(tf_qte.getText());
                  double tt= quantite*totale;
                
                  lbl_total.setText(String.valueOf(tt));
                  
         
    }

   
*/

    @FXML
    private void Update_Quantity_Product_Order_Line(ActionEvent event) {
        
    }
}
