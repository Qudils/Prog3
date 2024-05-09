package application;

import java.sql.SQLException;
import javafx.fxml.FXML;

public class RifiutaConcreteCommand implements SceltaCommand {

	private HomeStudente studente;

	public RifiutaConcreteCommand(HomeStudente studente) {
		this.studente = studente;
	}

	@Override
	@FXML
	public void esecuzione() throws SQLException {
		studente.RifiutaEsameSuperato();
	}
}