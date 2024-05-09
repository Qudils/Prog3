package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

	@FXML
	private Button confermaLogin;

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;
	
	@FXML
    private TextField NomeStudente;

    @FXML
    private PasswordField MatricolaStudente;
    
    private final Model modello = new Model(); 
	
	@FXML
	public void LoginDocenteConferma(ActionEvent event) throws Exception {
		Segreteria segreteria = new Segreteria();
		segreteria.setUsername(usernameField.getText());
		segreteria.setPassword(passwordField.getText());

		if (segreteria.getPassword().equals("1234") && segreteria.getUsername().equals("Ciaramella")) {
			Parent tableViewParent = FXMLLoader.load(getClass().getResource("/fxml/HomeDocente.fxml"));
			Scene tableViewScene = new Scene(tableViewParent);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setResizable(false);
			window.setScene(tableViewScene);
			window.show();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Errore");
			alert.setHeaderText(null);
			alert.setContentText("Credenziali non riconosciute");
			alert.show();
		}
	}	

	@FXML
	public void LoginConferma(ActionEvent event) throws Exception {
		Segreteria segreteria = new Segreteria();
		segreteria.setUsername(usernameField.getText());
		segreteria.setPassword(passwordField.getText());

		if (segreteria.getPassword().equals("admin") && segreteria.getUsername().equals("admin")) {
			Parent tableViewParent = FXMLLoader.load(getClass().getResource("/fxml/HomeSegreteria.fxml"));
			Scene tableViewScene = new Scene(tableViewParent);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setResizable(false);
			window.setScene(tableViewScene);
			window.show();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Errore");
			alert.setHeaderText(null);
			alert.setContentText("Credenziali non riconosciute");
			alert.show();
		}
	}

	@FXML
	public void LoginDelloStudente(ActionEvent event) throws Exception {
		// Ottieni i valori dai TextField
		String nome = NomeStudente.getText();
		String matricola = MatricolaStudente.getText();
		
		if (modello.loginStudente(nome, matricola)) {
			Parent tableViewParent = FXMLLoader.load(getClass().getResource("/fxml/HomeStudente.fxml"));
			Scene tableViewScene = new Scene(tableViewParent);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setResizable(false);
			window.setScene(tableViewScene);
			window.show();
		}
	}
}
