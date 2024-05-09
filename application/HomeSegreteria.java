package application;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;

public class HomeSegreteria {
	@FXML
	private Button registrazioneButton;

	@FXML
	private Button RimuoviStudente;

	@FXML
	private Button cercaStudente;

	@FXML
	private Button esame;

	@FXML
	private Button CercaValutazione;

	@FXML
	private TextField nome;

	@FXML
	private TextField cognome;

	@FXML
	private TextField matricola;

	@FXML
	private TextField dataDiNascita;

	@FXML
	private TextField residenza;

	@FXML
	private TextField pianoDiStudi;

	@FXML
	private TextField CercaMatricola;

	@FXML
	private TextField CercaNome;

	@FXML
	private TextField CercaCognome;

	@FXML
	private TextField CercaCorso;

	@FXML
	private TableView<AbstractStudente> tableViewStudente;

	@FXML
	private TableColumn<AbstractStudente, String> matricolaTab;

	@FXML
	private TableColumn<AbstractStudente, String> nomeTab;

	@FXML
	private TableColumn<AbstractStudente, String> cognomeTab;

	@FXML
	private TableColumn<AbstractStudente, String> datanascitaTab;

	@FXML
	private TableColumn<AbstractStudente, String> residenzaTab;

	@FXML
	private TableColumn<AbstractStudente, String> pianoDiStudiTab;

	@FXML
	private TableView<TestValutazione> tableViewValutazione;

	@FXML
	private TableColumn<String, String> matricolaCorsoTab;

	@FXML
	private TableColumn<String, String> corsoTab;
	@FXML
	TableColumn<String, String> valutazioneTab;

	Connection conn = null;

	@FXML
	private void showHomeSegreteria(ActionEvent event) {
		try {
			// Carica il file FXML "HomeSegreteria.fxml"
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HomeSegreteria.fxml"));
			Parent root = loader.load();

			// Crea una nuova scena con il contenuto del file FXML
			Scene scene = new Scene(root);

			// Crea un nuovo stage (finestra) e imposta la scena
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Home Segreteria");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void MostraRegistrazione(ActionEvent event) {
		try {
			// Carica il file FXML "HomeSegreteria.fxml"
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegistrazioneStudente.fxml"));
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
	private void RicercaStudente(ActionEvent event) {
		try {
			// Carica il file FXML "HomeSegreteria.fxml"
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RicercaStudente.fxml"));
			Parent root = loader.load();

			// Crea una nuova scena con il contenuto del file FXML
			Scene scene = new Scene(root);

			// Crea un nuovo stage (finestra) e imposta la scena
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Ricerca uno studente");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void MostraEsami(ActionEvent event) {
		try {
			// Carica il file FXML "HomeSegreteria.fxml"
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RicercaValutazione.fxml"));
			Parent root = loader.load();

			// Crea una nuova scena con il contenuto del file FXML
			Scene scene = new Scene(root);

			// Crea un nuovo stage (finestra) e imposta la scena
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Ricerca test valutazione");
			CercaCorso = (TextField) scene.lookup("#CercaCorso");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	@FXML
	private void ConfermaDatiStudente(ActionEvent event) throws IOException {
		// Ottieni i valori dai TextField
		String nomeStudente = nome.getText();
		String cognomeStudente = cognome.getText();
		String matricolaStudente = matricola.getText();
		String dataDiNascitaStudente = dataDiNascita.getText();
		String residenzaStudente = residenza.getText();
		String pianoDiStudiStudente = pianoDiStudi.getText();

//		// Crea un oggetto Studente con i valori ottenuti

		PersonaFactory nuovoStudente = new ConcretePersonaFactory();

		AbstractStudente nuovoStudente2 = nuovoStudente.creaStudente(nomeStudente, cognomeStudente, matricolaStudente,
				dataDiNascitaStudente, residenzaStudente, pianoDiStudiStudente);

		// Salva lo studente nel database MySQL
		salvaStudenteInMySQL(nuovoStudente2);
	}

	private void salvaStudenteInMySQL(AbstractStudente studente) {

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studenti", "root", "admin")) {

			if (conn != null) {
				System.out.println("Connesso");
			}
			// Prepara la query SQL per l'inserimento dello studente
			String query = "INSERT INTO studenti (nome, cognome, matricola, dataDiNascita, residenza, pianoDiStudi) VALUES (?, ?, ?, ?, ?, ?)";
			try (PreparedStatement pstmt = conn.prepareStatement(query)) {
				// Imposta i parametri della query con i dati dello studente
				pstmt.setString(1, studente.getNome());
				pstmt.setString(2, studente.getCognome());
				pstmt.setString(3, studente.getMatricola());
				pstmt.setString(4, studente.getDataDiNascita());
				pstmt.setString(5, studente.getResidenza());
				pstmt.setString(6, studente.getPianoDiStudi());

				// Esegui la query di inserimento
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {

			System.out.println("Non connesso");
			e.printStackTrace();
		}
	}

	@FXML
	private void RicercaValutazione() throws IOException, SQLException {

		String valutazione = CercaCorso.getText();

		ValutazioneStrategy valutazioneStrategy;

		if (valutazione.equalsIgnoreCase("mediocre") ||
				valutazione.equalsIgnoreCase("ottimo") ||
				valutazione.equalsIgnoreCase("eccellente")) {
			valutazioneStrategy = new EsitoValutazioneStrategy(CercaCorso, tableViewValutazione, matricolaCorsoTab, corsoTab, valutazioneTab);
		} else {
			valutazioneStrategy = new EsameStrategy(CercaCorso, tableViewValutazione, matricolaCorsoTab, corsoTab, valutazioneTab);
		}
		valutazioneStrategy.mostraValutazione(valutazione);
	}


	@FXML
	private void RicercaDatiStudente(ActionEvent event) throws IOException {
		String matricola = CercaMatricola.getText();
		String nome = CercaNome.getText();
		String cognome = CercaCognome.getText();

		matricolaTab.setCellValueFactory(new PropertyValueFactory<>("matricola"));
		datanascitaTab.setCellValueFactory(new PropertyValueFactory<>("dataDiNascita"));
		nomeTab.setCellValueFactory(new PropertyValueFactory<>("nome"));
		cognomeTab.setCellValueFactory(new PropertyValueFactory<>("cognome"));
		residenzaTab.setCellValueFactory(new PropertyValueFactory<>("residenza"));
		pianoDiStudiTab.setCellValueFactory(new PropertyValueFactory<>("pianoDiStudi"));

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studenti", "root", "admin")) {
			if (conn != null) {
				System.out.println("Connesso");

				// Prepara la query SQL per la ricerca degli studenti
				String query = "SELECT * FROM studenti WHERE nome LIKE ? AND cognome LIKE ? AND matricola = ?";

				try (PreparedStatement pstmt = conn.prepareStatement(query)) {
					// Imposta i parametri della query con i dati di ricerca
					pstmt.setString(1, "%" + nome + "%");
					pstmt.setString(2, "%" + cognome + "%");
					pstmt.setString(3, matricola);

					// Esegui la query di selezione
					ResultSet resultSet = pstmt.executeQuery();

					// Creare una lista per memorizzare gli studenti trovati
					List<AbstractStudente> studenti = new ArrayList<>();

					// Puoi ora elaborare i risultati della query
					while (resultSet.next()) {
						// Recupera i dati dello studente trovato
						String resultMatricola = resultSet.getString("matricola");
						String resultNome = resultSet.getString("nome");
						String resultCognome = resultSet.getString("cognome");
						String resultResidenza = resultSet.getString("residenza");
						String resultDataDiNascita = resultSet.getString("dataDiNascita");
						String resultPianoDiStudi = resultSet.getString("pianoDiStudi");
						// Recupera altri campi secondo la tua struttura dati

						PersonaFactory nuovoStudente = new ConcretePersonaFactory();

						AbstractStudente nuovoStudente2 = nuovoStudente.creaStudente(resultNome, resultCognome,
								resultMatricola, resultDataDiNascita, resultResidenza, resultPianoDiStudi);

						// Aggiungi lo studente alla lista
						studenti.add(nuovoStudente2);
					}

					// Assicurati di ottenere il riferimento corretto alla tua TableView
					tableViewStudente.setItems(FXCollections.observableList(studenti));
				}
			}
		} catch (SQLException e) {
			System.out.println("Non connesso");
			e.printStackTrace();
		}
	}

	@FXML
	private void RimuoviStudente(ActionEvent event) {

	}
}
