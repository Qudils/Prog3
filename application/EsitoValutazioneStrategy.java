package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;

import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

// MediocreStrategy.java
public class EsitoValutazioneStrategy implements ValutazioneStrategy {

	ObservableList<TestValutazione> listaValutazioni = FXCollections.observableArrayList();

	@FXML
	protected TextField CercaCorso;

	@FXML
	protected TableView<TestValutazione> tableViewValutazione;

	@FXML
	protected TableColumn<String, String> matricolaCorsoTab;

	@FXML
	protected TableColumn<String, String> corsoTab;
	
	@FXML
	protected TableColumn<String, String> valutazioneTab;
	
	public EsitoValutazioneStrategy (TextField CercaCorso, TableView<TestValutazione> tableViewValutazione, TableColumn<String, String> matricolaCorsoTab, TableColumn<String, String> corsoTab,TableColumn<String, String> valutazioneTab ) {
		this.CercaCorso = CercaCorso;
		this.tableViewValutazione = tableViewValutazione;
		this.matricolaCorsoTab = matricolaCorsoTab;
		this.corsoTab = corsoTab;
		this.valutazioneTab = valutazioneTab;
	}
	
	@FXML
	public void mostraValutazione(String input) throws SQLException {
		String valutazione = CercaCorso.getText();

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studenti", "root", "admin")) {
			System.out.println("Connesso");

			// Prepara la query SQL per la ricerca degli studenti
			String query = "SELECT matricola, materia, valutazione FROM valutazionitest WHERE valutazione = ?";

			try (PreparedStatement pstmt = conn.prepareStatement(query)) {
				// Imposta i parametri della query con i dati di ricerca
				pstmt.setString(1, valutazione);

				// Esegui la query di selezione
				ResultSet resultSet = pstmt.executeQuery();

				// Puoi ora elaborare i risultati della query
				while (resultSet.next()) {
					// Recupera i dati dello studente trovato
					String resultMatricola = resultSet.getString("matricola");
					String resultCorso = resultSet.getString("materia");
					String resultValutazione = resultSet.getString("valutazione");
					// Crea un nuovo oggetto Studente con i dati trovati
					TestValutazione testValutazione = new TestValutazione(resultMatricola, resultCorso, resultValutazione);

					// Aggiungi lo valutazione alla lista
					listaValutazioni.add(testValutazione);
				}

				tableViewValutazione.setItems(listaValutazioni);

				// Assicurati che le colonne siano associate ai campi corretti della classe
				// TestValutazione
				matricolaCorsoTab.setCellValueFactory(new PropertyValueFactory<>("matricola"));
				corsoTab.setCellValueFactory(new PropertyValueFactory<>("materia"));
				valutazioneTab.setCellValueFactory(new PropertyValueFactory<>("valutazione"));
			}
		}
	}
}