package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class HomeController {
	 @FXML
	    private Button segreteria;
	 
	 @FXML
	 	private Button Docente;
   
	 @FXML
	 	private Button studente;
	 
    @FXML
    private void showHomeSegreteria(ActionEvent event) {
        try {
            // Carica il file FXML "HomeSegreteria.fxml"
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginSegreteria.fxml"));
            Parent root = loader.load();

            // Crea una nuova scena con il contenuto del file FXML
            Scene scene = new Scene(root);

            // Crea un nuovo stage (finestra) e imposta la scena
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Home della segreteria");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void showHomeDocente(ActionEvent event) {
    	 try {
             // Carica il file FXML "HomeSegreteria.fxml"
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginDocente.fxml"));
             Parent root = loader.load();

             // Crea una nuova scena con il contenuto del file FXML
             Scene scene = new Scene(root);

             // Crea un nuovo stage (finestra) e imposta la scena
             Stage stage = new Stage();
             stage.setScene(scene);
             stage.setTitle("Home del docente");
             stage.show();
         } catch (IOException e) {
             e.printStackTrace();
         }    
   }

    @FXML
    private void showHomeStudente(ActionEvent event) {
    	try {
            // Carica il file FXML "HomeSegreteria.fxml"
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginStudente.fxml"));
            Parent root = loader.load();

            // Crea una nuova scena con il contenuto del file FXML
            Scene scene = new Scene(root);

            // Crea un nuovo stage (finestra) e imposta la scena
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Home dello studente");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }
}