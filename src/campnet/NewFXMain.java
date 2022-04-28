/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campnet;

import java.io.File;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class NewFXMain extends Application{

    /**
     * @param args the command line arguments
     */
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("/gui/livreur.fxml"));
      /*   String path="C:\\xampp\\htdocs\\hotelchambre\\bienvenue.mp3";
            Media media =new Media(new File(path).toURI().toString());
            MediaPlayer mediaplayer = new MediaPlayer(media);
            mediaplayer.play();*/
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}