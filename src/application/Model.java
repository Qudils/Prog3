package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Model {
	
	public Model() {
	}
	
	public boolean loginStudente (String nome, String matricola) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studenti", "root", "admin")) {
			// Prepara la query SQL per il login dello studente
			String query = "SELECT * FROM studenti WHERE nome = ? AND matricola = ?";

			try (PreparedStatement pstmt = conn.prepareStatement(query)) {
				pstmt.setString(1, nome);
				pstmt.setString(2, matricola);

				// Esegui la query di login
				try (ResultSet rs = pstmt.executeQuery()) {
					// Verifica se ci sono risultati
					if (rs.next()) {
						// I dati di login sono corretti, puoi eseguire le azioni desiderate (ad
						// esempio, aprire una nuova finestra)
						System.out.println("Login riuscito");
						return true;
					} else {
						// I dati di login non sono corretti, gestisci il caso di fallimento (ad
						// esempio, mostra un messaggio di errore)
						System.out.println("Nome o matricola non validi");
						return false;
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Errore durante il login dello studente");
			e.printStackTrace();
		}
		return false;
	}
}
