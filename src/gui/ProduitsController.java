/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import Tools.Smsapi;
import entities.commande;
import gui.PanierController;
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
import entities.panier;
import java.io.FileOutputStream;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * FXML Controller class
 *
 * @author Sarra
 */
public class ProduitsController implements Initializable {

    @FXML
    private TextField nomTF;
    @FXML
    private TextField PhotoTF;
    @FXML
    private TextField DescTF;
    private TextField DispoTF;
    @FXML
    private TextField PrixTF;
    @FXML
    private Button btnajoutP;
    @FXML
    private TableView<produit> tabProd;
    @FXML
    private TableColumn<produit, Integer> col_idp;
    @FXML
    private TableColumn<produit, String> col_nomp;
    @FXML
    private TableColumn<produit, String> col_phop;
    @FXML
    private TableColumn<produit, String> col_des;
    @FXML
    private TableColumn<produit, String> col_dispop;
    @FXML
    private TableColumn<produit, Double> col_prixp;
    @FXML
    private ComboBox<String> dispocombo;
    
    
     ObservableList<String> data = FXCollections.observableArrayList("Disponible","en stock","Non Disponible");
ObservableList<produit> produitList = FXCollections.observableArrayList();
ObservableList<panier> panierList = FXCollections.observableArrayList();
    @FXML
    private AnchorPane filsP;
    @FXML
    private Button actualiseP;
    @FXML
    private TextField idmodif;
    @FXML
    private Button modif;
    @FXML
    private Button suppP;
     @FXML
    private Button btnajoutqtpn;
    @FXML
    private Button pdf;
    private Connection con ;
    private PreparedStatement pst;
     Statement stmt;
    private ResultSet rs;
        private ObservableList<panier>panier;
        private panier ol;
        
    private TextField tf_price;
    @FXML
    private Button btnquitterpn;
    
    public void showProduits() {
              
             try {
       //     Connexion cnx = Connexion.getInstance().getCnx();
                    Connection instance = Connexion.getInstance().getCnx();

            String query = "SELECT * FROM produit";
            Statement st;
            ResultSet rst;
            st = instance.createStatement();
            rst = st.executeQuery(query);
            produit produits;
        
            while (rst.next()) {

//rst.getInt(1),
 produits = new produit(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getDouble(6));
                produitList.add(produits);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
       
        col_idp.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nomp.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_phop.setCellValueFactory(new PropertyValueFactory<>("photo"));
        col_des.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_dispop.setCellValueFactory(new PropertyValueFactory<>("disponibilte"));
        col_prixp.setCellValueFactory(new PropertyValueFactory<>("prix"));
        

        

        tabProd.setItems(produitList);
    }
    
    
    
    
    
    
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showProduits();
         dispocombo.setItems(data);
        
    }    

    @FXML
    private void AjoutProduit(ActionEvent event) {
        produit p;
        Serviceproduit ps = new Serviceproduit();
         if (nomTF.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Vous navez pas saisie le nom");
            alert.show();

        }
         else if (PhotoTF.getText().length() == 0) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Vous navez pas saisie une photo");
            alert.show();
         }
         else if (PrixTF.getText().length() == 0) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Vous navez pas saisie le prix");
            alert.show();
         }
             else {
       // Chambre p = new Chambre(tf_idch.getText(),tf_nblits.getText(),typech.getValue(),Double.parseDouble(tf_prix.getText()));
    p = new produit(nomTF.getText(),PhotoTF.getText(),DescTF.getText(),dispocombo.getValue(),Double.parseDouble(PrixTF.getText()));
    ps.Ajoutproduit(p);} /* Notifications notificationBuilder = Notifications.create()
        .title("Chambre ajouté")
        .text("Votre Offre d'emploi a été ajouté avec succès")
        .graphic(new ImageView(img))
        .hideAfter(Duration.seconds(5))
        .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();*/
    Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Succes");
    alert.setContentText("PRODUIT ajoutee");
        Smsapi.sendSMS("Commande effectuée avec succes");

    alert.show();
    nomTF.setText("");
    PhotoTF.setText("");
    DescTF.setText("");
    DispoTF.setText("");
    PrixTF.setText("");
    }

    @FXML
    private void refreshP(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/produits.fxml"));
                     filsP.getChildren().setAll(pane);
    }

    @FXML
    private void btn_modifier(ActionEvent event) {
         Serviceproduit sr = new Serviceproduit();
        produit e = new produit();
        int id;
        id = Integer.parseInt(idmodif.getText());
        e.setId(Integer.parseInt(idmodif.getText()));
            
            e.setNom(nomTF.getText());
            e.setPhoto(PhotoTF.getText());
            e.setDescription(DescTF.getText());
            e.setDisponibilte(dispocombo.getValue());
            e.setPrix(Double.parseDouble(PrixTF.getText()));
          
            

        sr.modifier(id, e);
        
           idmodif.clear();
           nomTF.clear();
           PhotoTF.clear();
           DescTF.clear();
           PrixTF.clear();
    }

    @FXML
    private void btn_SupprP(ActionEvent event) throws SQLException {
        produitList=tabProd.getSelectionModel().getSelectedItems();
          Connection instance = Connexion.getInstance().getCnx();
            int id;
            id=produitList.get(0).getId();
            System.out.println(id);
             
        
            
           String query = "delete from produit WHERE id = ?";
      PreparedStatement preparedStmt = instance.prepareStatement(query);
      preparedStmt.setInt(1, id);

      // execute the preparedstatement
      preparedStmt.execute();
       
             showProduits();      
      
         }

    @FXML
    private void extPDF(ActionEvent event) throws Exception {
             Document document=new Document();
  PdfWriter.getInstance(document,new FileOutputStream("E:\\pidev\\campnet\\facture\\facture3.pdf"));
       document.open();
       Paragraph ph1 = new Paragraph("Bienvenue à Campnet!");
       Paragraph ph2 = new Paragraph(".");
       PdfPTable table=new PdfPTable(6);
       table.addCell("ID");
       table.addCell("Nom");
       table.addCell("Photo");
       table.addCell("Description");
       table.addCell("Disponibilité");
       table.addCell("Prix");
      


       
       
       Class.forName("com.mysql.jdbc.Driver");
       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/produit", "root", "");
       Statement st=con.createStatement();
       ResultSet rs=st.executeQuery("Select * from produit order by prix");
       while(rs.next()){
       table.addCell(rs.getString("id"));
       table.addCell(rs.getString("nom"));
       table.addCell(rs.getString("photo"));
       table.addCell(rs.getString("description"));
       table.addCell(rs.getString("disponibilte"));
       table.addCell(rs.getString("prix"));
      

       }
       document.add(table);
       document.close();
   }
    
    
       private void Recuperer(){
        tabProd.setOnMouseClicked(e->{
       
         produit prod = tabProd.getItems().get(tabProd.getSelectionModel().getSelectedIndex());
           
            tf_price.setText(Double.toString(prod.getPrix()));
         
          
               
                
    }
  );
 }
     
    /*private void AjoutqtPanier(ActionEvent event) throws SQLException {
    
            try {
            pst=con.prepareStatement("SELECT nom as proname, prix as pp,prix as tt from `panier` ,  `produit`  where panier.id=panier.id");
             //     pst=con.prepareStatement("SELECT productName as proname, productPrice as pp,total as tt from `order_line` , `product`, `order`  where order_line.idProduct= product.idProduct  AND order.idOrder=order_line.idOrder");

            pst.executeQuery();
              while (rs.next()){
                     
                   panier.add(new panier(new produit(rs.getString("nom")), new produit (rs.getDouble("prix")), rs.getInt("quantite"), new panier (rs.getDouble("prix"))));
              //SetCellluleTable();
        }  } catch (SQLException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
     //   tabPn.setItems(panier);
          
    }*/
     

    @FXML
    private void Create_An_Order_Line(ActionEvent event) throws SQLException {
          

 Connection instance = Connexion.getInstance().getCnx();
         stmt = instance.createStatement();
            
            ol= new panier();
           /*   rs=stmt.executeQuery("SELECT * FROM `panier`  ");
            if (rs.next()){
               System.out.println("Ligne de commande existante ; ajoutez vos produits ");
 
        }
        else{*/
            
              
                  String Query = " INSERT INTO `campnet`.`panier`(`id`, `nom`, `quantite`,  `prix`) VALUES " 
                          + "('" + ol.getId() + "','"
                          + ol.getNom() + "','" 
                          + ol.getQuantite() +
                          "','" + 
                        0
                          + "')";         
                  
  stmt = instance.createStatement(); 
                 stmt.executeUpdate(Query);
            
                System.out.println(" ajouté, veuillez consulter votre BD"); 
        //}     
               
    }

    @FXML
    private void Quitterpanier(ActionEvent event) {
        System.exit(0);
    }

    }
    
    
    

