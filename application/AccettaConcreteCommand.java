package application;

import java.sql.SQLException;
import javafx.fxml.FXML;

public class AccettaConcreteCommand implements SceltaCommand {
	

	private HomeStudente studente;
	
	public AccettaConcreteCommand(HomeStudente studente) {
		this.studente = studente;
	}
	
	@Override
	@FXML
	public void esecuzione() throws SQLException {
		studente.AccettaEsameSuperato();
	}
}