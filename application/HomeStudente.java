package application;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;

public class HomeStudente {

	AppelloFactory appelloFactory = new AppelloFactoryConcrete();

	@FXML
	private Button PrenotaEsame;

	@FXML
	private Button cercaAppello;

	@FXML
	private Button EsitoEsame;

	@FXML
	private Button Valutazione;

	@FXML
	private Button PrenotaButton;

	@FXML
	private Button CercaEsamiSuperati;

	@FXML
	private Button ConfermaDati;

	@FXML
	private Button AccettaEsame;

	@FXML
	private Button RifiutaEsame;

	@FXML
	private TextField NomeDocente;

	@FXML
	private TextField CognomeDocente;

	@FXML
	private TextField DataEsame;

	@FXML
	private TextField MateriaEsame;

	@FXML
	private TextField matricolaStudente;

	@FXML
	private TextField MatricolaValutazione;

	@FXML
	private TextField CorsoValutazione;

	@FXML
	private TextField ValutazioneText;

	@FXML
	private TextField SearchMatricolaSuperati;

	@FXML
	private TableView<ObservableList<String>> TableViewStudente;

	@FXML
	private TableColumn<ObservableList<String>, String> NomeEsameTab;

	@FXML
	private TableColumn<ObservableList<String>, String> VotoTab;

	@FXML
	private TableView<Appello> TableViewAppelli;

	@FXML
	private TableColumn<Appello, String> NomeDocenteTab;

	@FXML
	private TableColumn<Appello, String> MateriaTab;

	@FXML
	private TableColumn<Appello, String> DataTab;

	@FXML
	private TableColumn<Appello, String> CognomeTab;

	@FXML
	private void RicercaEsame(ActionEvent event) throws IOException {

		String materia = MateriaEsame.getText();
		String nome = NomeDocente.getText();
		String data = DataEsame.getText();
		String cognome = CognomeDocente.getText();

		// Connessione al database
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studenti", "root", "admin")) {
			if (conn != null) {
				System.out.println("Connesso");

				NomeDocenteTab.setCellValueFactory(new PropertyValueFactory<>("nome"));
				CognomeTab.setCellValueFactory(new PropertyValueFactory<>("cognome"));
				MateriaTab.setCellValueFactory(new PropertyValueFactory<>("materia"));
				DataTab.setCellValueFactory(new PropertyValueFactory<>("data"));

				// Query per la ricerca degli appelli disponibili
				String query = "SELECT nome, cognome, materia, data FROM appello WHERE nome = ? AND cognome = ? AND materia = ? AND data = ?";

				try (PreparedStatement pstmt = conn.prepareStatement(query)) {
					// Imposta i parametri della query con i dati di ricerca
					pstmt.setString(1, nome);
					pstmt.setString(2, cognome);
					pstmt.setString(3, materia);
					pstmt.setString(4, data);

					// Esegui la query di selezione
					ResultSet resultSet = pstmt.executeQuery();

					// Creare una lista per memorizzare gli appelli trovati
					List<Appello> listaAppello = new ArrayList<>();

					while (resultSet.next()) {
						// Recupera i dati dell'appello
						String resultNome = resultSet.getString("nome");
						String resultCognome = resultSet.getString("cognome");
						String resultMateria = resultSet.getString("materia");
						String resultData = resultSet.getString("data");

						// Crea un nuovo oggetto Docente con i dati trovati
//						AbstractDocente docenteAppello = new DocenteFactory(resultNome, resultCognome, resultMateria);
//						AbstractAppello appelloFactory = new AppelloFactory(resultData, resultMateria, docenteAppello);
						Appello appello = appelloFactory.creaAppello(resultNome, resultCognome, resultMateria,
								resultData);
						// Aggiungi il docente alla lista
						listaAppello.add(appello);
					}

					// Assicurati di ottenere il riferimento corretto alla tua TableView
					TableViewAppelli.setItems(FXCollections.observableList(listaAppello));

				}
			}
		} catch (SQLException e) {
			System.out.println("Errore durante il recupero degli appelli.");
			e.printStackTrace();
		}
	}

	@FXML
	private void PrenotaEsame(ActionEvent event) throws IOException {

		// Ora carica la finestra dopo aver ottenuto i dati
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PrenotazioneAppello.fxml"));
		Parent root = loader.load();

		// Crea una nuova scena con il contenuto del file FXML
		Scene scene = new Scene(root);

		// Crea un nuovo stage (finestra) e imposta la scena
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Scegli un'esame");
		stage.show();

	}

	@FXML
	private void MostraEsitiEsami(ActionEvent event) {
		try {
			// Carica il file FXML "HomeSegreteria.fxml"
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AccettaRifiutaVoto.fxml"));

			Parent root = loader.load();

			// Crea una nuova scena con il contenuto del file FXML
			Scene scene = new Scene(root);

			// Crea un nuovo stage (finestra) e imposta la scena
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Inserisci uno studente");
			SearchMatricolaSuperati = (TextField) scene.lookup("#SearchMatricolaSuperati");
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void RicercaVotiSuperati(ActionEvent event) {
		String matricola = SearchMatricolaSuperati.getText();

		// Creare una lista per memorizzare i dati delle righe
		ObservableList<ObservableList<String>> rowDataList = FXCollections.observableArrayList();

		// Connessione al database
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studenti", "root", "admin")) {
			if (conn != null) {
				System.out.println("Connesso");
			}

			// Query per la ricerca degli appelli disponibili
			String query = "SELECT materia, voto FROM esamesuperato WHERE matricola = ?";

			try (PreparedStatement pstmt = conn.prepareStatement(query)) {
				// Imposta i parametri della query con i dati di ricerca
				pstmt.setString(1, matricola);

				// Esegui la query di selezione
				ResultSet resultSet = pstmt.executeQuery();

				while (resultSet.next()) {
					// Recupera i dati dell'appello
					String materia = resultSet.getString("materia");
					String voto = resultSet.getString("voto");

					// Creare una lista temporanea per memorizzare i dati di questa riga
					ObservableList<String> rowData = FXCollections.observableArrayList();
					rowData.add(materia);
					rowData.add(voto);

					// Aggiungi i dati della riga alla lista delle righe
					rowDataList.add(rowData);
				}
			}

			// Assicurati di ottenere il riferimento corretto alla tua TableView
			TableViewStudente.setItems(rowDataList);

			// Imposta PropertyValueFactory per associare le colonne ai dati
			NomeEsameTab.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(0)));
			VotoTab.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(1)));

		} catch (SQLException e) {
			System.out.println("Errore durante il recupero degli appelli.");
			e.printStackTrace();
		}
	}

	@FXML
	private void PrenotaEsameScelto(ActionEvent event) {
		// Ottieni l'appello selezionato
		Appello appelloSelezionato = TableViewAppelli.getSelectionModel().getSelectedItem();

		if (appelloSelezionato != null) {
			// Ottieni i dati dell'appello
			String materia = appelloSelezionato.getMateria();
			String data = appelloSelezionato.getData();
			try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studenti", "root",
					"admin")) {
				if (conn != null) {

					String matricola = matricolaStudente.getText();
					// Query per la prenotazione dell'esame
					String query = "INSERT INTO prenotazione (matricola, materia, data) VALUES (?, ?, ?)";

					try (PreparedStatement pstmt = conn.prepareStatement(query)) {
						// Imposta i parametri della query con i dati dell'appello selezionato
						pstmt.setString(1, matricola);
						pstmt.setString(2, materia);
						pstmt.setString(3, data);

						// Esegui la query di inserimento
						pstmt.executeUpdate();

						System.out.println("Esame prenotato con successo!");
					}
				}
			} catch (SQLException e) {
				System.out.println("Errore durante la prenotazione dell'esame.");
				e.printStackTrace();
			}
		}
	}

	@FXML
	private void TestValutazioneEsame(ActionEvent event) {
		try {
			// Carica il file FXML "HomeSegreteria.fxml"
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TestValutazione.fxml"));
			Parent root = loader.load();

			// Crea una nuova scena con il contenuto del file FXML
			Scene scene = new Scene(root);

			// Crea un nuovo stage (finestra) e imposta la scena
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Inserisci uno studente");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void VotoValutazioneFinale(ActionEvent event) throws SQLException {

		// Ottieni i valori dai TextField
		String matricola = MatricolaValutazione.getText();
		String valutazione = ValutazioneText.getText();
		String materia = CorsoValutazione.getText();

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studenti", "root", "admin")) {
			if (conn != null) {
				System.out.println("Connesso");
			}

			// Prepara la query SQL per l'inserimento dello studente
			String query = "INSERT INTO valutazionitest (matricola, valutazione, materia) VALUES (?, ?, ?)";

			try (PreparedStatement pstmt = conn.prepareStatement(query)) {

				if ("Ottimo".equals(valutazione) || "Mediocre".equals(valutazione)
						|| "Eccellente".equals(valutazione)) {
					pstmt.setString(1, matricola);
					pstmt.setString(2, valutazione); // Usa setDouble per la media
					pstmt.setString(3, materia);

					// Esegui la query di inserimento
					pstmt.executeUpdate();
				} else {
					System.out.println("Valutazione non valida");
				}
			}
		} catch (SQLException e) {

			System.out.println("Non connesso");
			e.printStackTrace();
		}
	}

	// reciver
	public void AccettaEsameSuperato() throws SQLException {

		ObservableList<String> appelloSelezionato = TableViewStudente.getSelectionModel().getSelectedItem();

		if (appelloSelezionato != null) {
			// Ottieni i dati dell'appello
			String materia = appelloSelezionato.get(0);
			String voto = appelloSelezionato.get(1);
			String matricola = SearchMatricolaSuperati.getText();
			
			try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studenti", "root",
					"admin")) {
				if (conn != null) {
					System.out.println("Connesso");
				}
				// Query per l'aggiornamento dell'esame accettato
				String query = "UPDATE esamesuperato SET conferma = 1 WHERE materia = ? AND voto = ? AND matricola = ?";

				try (PreparedStatement pstmt = conn.prepareStatement(query)) {
					// Imposta i parametri della query con i dati dell'appello selezionato
					pstmt.setString(1, materia);
					pstmt.setString(2, voto);
					pstmt.setString(3, matricola);

					// Esegui la query di aggiornamento
					int rowsUpdated = pstmt.executeUpdate();

					if (rowsUpdated > 0) {
						System.out.println("Esame accettato con successo!");
					} else {
						System.out.println("Nessuna riga aggiornata. Esame non trovato.");
					}
				}
			} catch (SQLException e) {
				System.out.println("Errore durante l'aggiornamento dell'esame.");
				e.printStackTrace();
			}
		}
	}

	@FXML
	public void AccettaEsameSuperatoCommand() throws SQLException {
		AccettaConcreteCommand accettaCommand = new AccettaConcreteCommand(this);
		accettaCommand.esecuzione();
	}

	@FXML
	public void RifiutaEsameSuperato() throws SQLException {
		ObservableList<String> appelloSelezionato = TableViewStudente.getSelectionModel().getSelectedItem();

		if (appelloSelezionato != null) {
			// Ottieni i dati dell'appello
			String materia = appelloSelezionato.get(0);
			String voto = appelloSelezionato.get(1);
			String matricola = SearchMatricolaSuperati.getText();

			try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studenti", "root",
					"admin")) {
				if (conn != null) {
					System.out.println("Connesso");
				}
				// Query per l'aggiornamento dell'esame accettato
				String query = "DELETE FROM esamesuperato WHERE materia = ? AND voto = ? AND matricola = ?";

				try (PreparedStatement pstmt = conn.prepareStatement(query)) {
					// Imposta i parametri della query con i dati dell'appello selezionato
					pstmt.setString(1, materia);
					pstmt.setString(2, voto);
					pstmt.setString(3, matricola);

					// Esegui la query di aggiornamento
					int rowsUpdated = pstmt.executeUpdate();

					if (rowsUpdated > 0) {
						System.out.println("Esame rifiutato con successo!");
					} else {
						System.out.println("Nessuna riga aggiornata. Esame non trovato.");
					}
				}
			} catch (SQLException e) {
				System.out.println("Errore durante l'aggiornamento dell'esame.");
				e.printStackTrace();
			}
		}
	}

	@FXML
	public void RifiutaEsameSuperatoCommand() throws SQLException {
		RifiutaConcreteCommand rifiutaCommand = new RifiutaConcreteCommand(this);
		rifiutaCommand.esecuzione();
	}
}
