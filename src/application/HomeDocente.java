package application;

import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeDocente {

	PersonaFactory personaFactory = new ConcretePersonaFactory();

	AppelloFactory appelloFactory = new AppelloFactoryConcrete();

	AppelloOriginator appelloOriginator = new AppelloOriginator();

	AppelloMemento appelloMemento = appelloOriginator.salvaAppello();

	@FXML
	private Button AppelloEsame;
	
	@FXML
	private Button AggiungiAppello;

	@FXML
	private Button ConfermaAppello;

	@FXML
	private Button AnnullaAppello;

	@FXML
	private Button VotoEsame;

	@FXML
	private Button confermaVoto;

	@FXML
	private TextField dataAppello;

	@FXML
	private TextField nomeAppello;

	@FXML
	private TextField nomeDocente;

	@FXML
	private TextField cognomeDocente;

	@FXML
	private Button confermaAppello;

	@FXML
	private TextField nomeEsame;

	@FXML
	private TextField voto;

	@FXML
	private TextField matricolaVoto;

	@FXML
	private void InserimentoAppello(ActionEvent event) {
		try {
			// Carica il file FXML "HomeSegreteria.fxml"
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CreazioneAppelli.fxml"));
			Parent root = loader.load();

			// Crea una nuova scena con il contenuto del file FXML
			Scene scene = new Scene(root);

			// Crea un nuovo stage (finestra) e imposta la scena
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Inserimento nuovo appello esame");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void ConfermaVotiEsame(ActionEvent event) {
		try {
			// Carica il file FXML "HomeSegreteria.fxml"
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NuovoVoto.fxml"));
			Parent root = loader.load();

			// Crea una nuova scena con il contenuto del file FXML
			Scene scene = new Scene(root);

			// Crea un nuovo stage (finestra) e imposta la scena
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Inserisci voto");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void AggiungiAppello(ActionEvent event) {

		// Ottieni i valori dai TextField
		String nome = nomeDocente.getText();
		String cognome = cognomeDocente.getText();
		String materia = nomeAppello.getText();
		String data = dataAppello.getText();

//		AbstractDocente docente = personaFactory.creaDocente(nome, cognome, materia);
		AbstractAppello appello = appelloFactory.creaAppello(nome, cognome, materia, data);

		// controlla nella data se c'e' non uno stesso appello gia' presente
		if (appelloOriginator.getAppello().stream()
				.noneMatch(app -> app.getMateria().equals(materia) && app.getData().equals(data))) {
			appelloOriginator.getAppello().add(appello);
			nomeDocente.clear();
			cognomeDocente.clear();
			dataAppello.clear();
			nomeAppello.clear();
		}

	}
	@FXML
	private void ConfermaAppello() {

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studenti", "root", "admin")) {
			// Prepara la query SQL per l'inserimento dell'appello
			String query = "INSERT INTO appello (nome, cognome, materia, data) VALUES (?, ?, ?, ?)";

			try (PreparedStatement pstmt = conn.prepareStatement(query)) {
				for (AbstractAppello appello : appelloOriginator.getAppello()) {
					pstmt.setString(1, appello.getNome());
					pstmt.setString(2, appello.getCognome());
					pstmt.setString(3, appello.getMateria());
					pstmt.setString(4, appello.getData());

					// Esegui la query di inserimento
					pstmt.executeUpdate();
					appelloOriginator.salvaAppello();
				}

			}
		} catch (SQLException e) {
			System.out.println("Errore durante l'inserimento dell'appello nel database.");
			e.printStackTrace();
		}

	}
	
	@FXML
	private void AnnullaAppello() {
		appelloMemento.annullaAppello();
	}

	@FXML
	private void nuovoVotoConferma(ActionEvent event) throws SQLException {

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studenti", "root", "admin")) {

			String votoEsame = voto.getText();
			String materia = nomeEsame.getText();
			String matricola = matricolaVoto.getText();
			String conferma = "0";

			String query = "INSERT INTO esamesuperato (matricola, materia, voto, conferma) VALUES (?, ?, ?, ?)";

			try (PreparedStatement pstmt = conn.prepareStatement(query)) {
				pstmt.setString(1, matricola);
				pstmt.setString(2, materia);
				pstmt.setString(3, votoEsame);
				pstmt.setString(4, conferma);

				// Esegui la query di inserimento
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("Errore durante l'inserimento dell'appello nel database.");
			e.printStackTrace();
		}
	}
}
